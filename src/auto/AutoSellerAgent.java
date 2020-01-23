/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package auto;

import java.util.HashMap;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AutoSellerAgent extends Agent {

	private HashMap<String, Auto> autaSprzedajacego;
	private AutoSellerGui myGui;

	protected void setup() {
		autaSprzedajacego = new HashMap<String, Auto>();

		myGui = new AutoSellerGui(this);
		myGui.showGui();

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("auto-selling");
		sd.setName("JADE-auto-trading");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		addBehaviour(new OfferRequestsServer());

		addBehaviour(new PurchaseOrdersServer());
	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		myGui.dispose();
	}

	public void dodajAuto(String nazwa, String marka, String model, String typNadwozia,
			String typSilnika, int pojemnoscSilnika, int rokProdukcji, int cena, int kosztyDodatkowe) {
		addBehaviour(new OneShotBehaviour() {
			private static final long serialVersionUID = 1L;

			public void action() {
				autaSprzedajacego.put(nazwa, new Auto(marka, model, typNadwozia, typSilnika, pojemnoscSilnika, rokProdukcji,
						cena,
						kosztyDodatkowe));
				System.out.println(nazwa + " dodany do katalogu.");
				System.out.println("  - marka = " + marka);
				System.out.println("  - model = " + model);
				System.out.println("  - typNadwozia = " + typNadwozia);
				System.out.println("  - typSilnika = " + typSilnika);
				System.out.println("  - pojemnoscSilnika = " + pojemnoscSilnika);
				System.out.println("  - rokProdukcji = " + rokProdukcji);
				System.out.println("  - cena = " + cena);
				System.out.println("  - kosztyDodatkowe = " + kosztyDodatkowe);
			}
		});
	}

	private class OfferRequestsServer extends CyclicBehaviour {

		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {

				String nazwa = msg.getContent();
				ACLMessage reply = msg.createReply();

				Integer cena = autaSprzedajacego.get(nazwa).cena;

				reply.setPerformative(ACLMessage.PROPOSE);
				reply.setContent(String.valueOf(cena.intValue()));

				myAgent.send(reply);
			} else {
				block();
			}
		}
	}

	private class PurchaseOrdersServer extends CyclicBehaviour {

		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				String title = msg.getContent();
				ACLMessage reply = msg.createReply();

				Integer cena = (Integer) autaSprzedajacego.remove(title).cena;
				if (cena != null) {
					reply.setPerformative(ACLMessage.INFORM);
					System.out.println(title + " sold to agent " + msg.getSender().getName());
				} else {
					reply.setPerformative(ACLMessage.FAILURE);
					reply.setContent("not-available");
				}
				myAgent.send(reply);
			} else {
				block();
			}
		}
	}
}
