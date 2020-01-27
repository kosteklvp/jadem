package gielda;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComboBox;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentSprzedajacy extends Agent {

	private HashMap<String, Auto> autaSprzedajacego = new HashMap<>();
	private SprzedajacyGui myGui;

	@Override
	protected void setup() {
		super.setup();
		autaSprzedajacego = new HashMap();

		myGui = new SprzedajacyGui(this);
		myGui.showGui();

		addBehaviour(new ProbaKupna());

	}

	private class ProbaKupna extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage wiadomosc = receive();
			if (wiadomosc != null) {
				AID wysylajacy = wiadomosc.getSender();
				ACLMessage odpowiedz = new ACLMessage(ACLMessage.CFP);
				HashMap<String, Auto> v = autaSprzedajacego;

				if (autaSprzedajacego.containsKey(wiadomosc.getContent())) {
					System.out.println("Agent " + getAID().getName() + " sprzedaje auto.");
					odpowiedz.addReceiver(wysylajacy);

					odpowiedz.setContent(autaSprzedajacego.get(wiadomosc.getContent()).toString());
					odpowiedz.setConversationId("1000");
					odpowiedz.setReplyWith("cfp" + System.currentTimeMillis());
					myAgent.send(odpowiedz);
					autaSprzedajacego.remove(wiadomosc.getContent());
					myGui.jComboBox = new JComboBox<String>();
				} else {
					System.out.println("Agent " + getAID().getName() + " nie sprzeda tego auta.");
					odpowiedz.addReceiver(wysylajacy);

					odpowiedz.setContent("");
					odpowiedz.setConversationId("1000");
					odpowiedz.setReplyWith("cfp" + System.currentTimeMillis());
					myAgent.send(odpowiedz);
				}
			}
		}
	}

	public void dodajAuto(Auto auto) {
		autaSprzedajacego.put(auto.marka + "-" + auto.model, auto);
	}

	public Vector<String> getListeAut() {
		Vector<String> v = new Vector<String>();
		Auto auto;
		for (String key : autaSprzedajacego.keySet()) {
			v.add(autaSprzedajacego.get(key).toString());
		}
		return v;
	}

	public Vector<Auto> getAutaSprzedajacego() {
		Vector<Auto> v = new Vector<Auto>();
		autaSprzedajacego.values().stream().forEach(a -> v.add(a));
		return v;
	}
}
