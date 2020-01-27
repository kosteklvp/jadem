package auto;

import jade.core.Agent;

public class SprzedajacyAgent extends Agent {
//
//	private HashMap<RodzajAuta, Auto> autaSprzedajacego;
//	private SprzedajacyGui myGui;
//
//	protected void setup() {
//		autaSprzedajacego = new HashMap();
//
//		myGui = new SprzedajacyGui(this);
//		myGui.showGui();
//
//		DFAgentDescription dfd = new DFAgentDescription();
//		dfd.setName(getAID());
//		ServiceDescription sd = new ServiceDescription();
//		sd.setType("auto-selling");
//		sd.setName("JADE-auto-trading");
//		dfd.addServices(sd);
//		try {
//			DFService.register(this, dfd);
//		} catch (FIPAException fe) {
//			fe.printStackTrace();
//		}
//
//		addBehaviour(new PytanieOAuto());
//
//		addBehaviour(new PropozycjaKupna());
//	}
//
//	protected void takeDown() {
//		try {
//			DFService.deregister(this);
//		} catch (FIPAException fe) {
//			fe.printStackTrace();
//		}
//
//		myGui.dispose();
//	}
//
//	public void dodajAuto(String marka, String model, String typNadwozia,
//			String typSilnika, int pojemnoscSilnika, int rokProdukcji, int cena, int kosztyDodatkowe) {
//		addBehaviour(new OneShotBehaviour() {
//
//			RodzajAuta rodzajAuta = new RodzajAuta(marka, model);
//			Auto auto = new Auto(marka, model, typNadwozia, typSilnika,
//					pojemnoscSilnika, rokProdukcji, cena, kosztyDodatkowe);
//
//			public void action() {
//				autaSprzedajacego.put(new RodzajAuta(marka, model),
//						new Auto(marka, model, typNadwozia, typSilnika,
//								pojemnoscSilnika, rokProdukcji, cena, kosztyDodatkowe));
//				System.out.println("Dodano auto: " + auto.toString());
//			}
//		});
//	}
//
//	private class PytanieOAuto extends CyclicBehaviour {
//
//		public void action() {
//			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
//			ACLMessage msg = myAgent.receive(mt);
//
//			if (msg != null) {
//				String rodzajAuta = msg.getContent();
//
//				Auto proponowaneAuto = autaSprzedajacego.get(RodzajAuta.fromString(rodzajAuta));
//
//				ACLMessage reply = msg.createReply();
//				reply.setPerformative(ACLMessage.PROPOSE);
//				if (proponowaneAuto != null) {
//					reply.setContent(proponowaneAuto.toString());
//				} else {
//					reply.setContent("Ten agent nie posiada takiego auta");
//				}
//
//				myAgent.send(reply);
//			} else {
//				block();
//			}
//
//		}
//	}
//
//	private class PropozycjaKupna extends CyclicBehaviour {
//
//		public void action() {
//			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
//			ACLMessage msg = myAgent.receive(mt);
//			if (msg != null) {
//				String autoString = msg.getContent();
//				Auto auto = Auto.fromString(autoString);
//				ACLMessage reply = msg.createReply();
//
//				if (autaSprzedajacego.containsValue(auto)) {
//					reply.setPerformative(ACLMessage.INFORM);
//					autaSprzedajacego.remove(new RodzajAuta(auto.marka, auto.model));
//					reply.setContent("Sprzedane");
//				} else {
//					reply.setPerformative(ACLMessage.FAILURE);
//					reply.setContent("Niedostepne");
//				}
//				myAgent.send(reply);
//			} else {
//				block();
//			}
//		}
//	}
}
