
package auto;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class KupujacyAgent extends Agent {
	private RodzajAuta szukaneAuto;
	private AID[] agenciSprzedajacy;

	protected void setup() {
		Object[] args = getArguments();
		szukaneAuto = RodzajAuta.fromString((String) args[0]);

		addBehaviour(new TickerBehavior2(this, 60000));
	}

	private class TickerBehavior2 extends TickerBehaviour {
		// szukanie agentow?
		public TickerBehavior2(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("auto-selling");
			template.addServices(sd);
			try {
				DFAgentDescription[] result = DFService.search(myAgent, template);
				agenciSprzedajacy = new AID[result.length];
				for (int i = 0; i < result.length; ++i) {
					agenciSprzedajacy[i] = result[i].getName();
				}
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}

			myAgent.addBehaviour(new RequestPerformer());
		}

	}

	public class RequestPerformer extends Behaviour {

		private AID bestSeller;
		private int bestPrice;
		private int repliesCnt = 0;
		private MessageTemplate mt;
		private int step = 0;

		public void action() {

			switch (step) {
			case 0:
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < agenciSprzedajacy.length; i++) {
					cfp.addReceiver(agenciSprzedajacy[i]);
				}
				cfp.setContent(szukaneAuto.toString());
				cfp.setConversationId("auto-trade");
				cfp.setReplyWith("cfp" + System.currentTimeMillis());
				myAgent.send(cfp);
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("auto-trade"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				step = 1;
				break;

			case 1:
				ACLMessage reply = myAgent.receive(mt);
				if (reply != null) {
					if (reply.getPerformative() == ACLMessage.PROPOSE) {
						int price = Integer.parseInt(reply.getContent());
						if (bestSeller == null || price < bestPrice) {
							bestPrice = price;
							bestSeller = reply.getSender();
						}
					}
					repliesCnt++;
					if (repliesCnt >= agenciSprzedajacy.length) {
						step = 2;
					}
				} else {
					block();
				}
				break;
			case 2:
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(bestSeller);
				order.setContent(szukaneAuto.toString());
				order.setConversationId("auto-trade");
				order.setReplyWith("order" + System.currentTimeMillis());
				myAgent.send(order);

				mt = MessageTemplate.and(
						MessageTemplate.MatchConversationId("auto-trade"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				step = 3;
				break;
			case 3:
				reply = myAgent.receive(mt);
				if (reply != null) {
					if (reply.getPerformative() == ACLMessage.INFORM) {
						myAgent.doDelete();
					}

					step = 4;
				} else {
					block();
				}
				break;
			}
		}

		public boolean done() {
			return ((step == 2 && bestSeller == null) || step == 4);
		}
	}

}
