package gielda;

import java.util.HashMap;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentKupujacy extends Agent {

	private String hajs;
	private HashMap<String, Auto> autaKupujacego = new HashMap<>();
	private KupujacyGui myGui;

	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new ProbaKupna());
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			hajs = (String) args[0];
		}

		myGui = new KupujacyGui(this);
		myGui.showGui();

	}

	public String getHajs() {
		return hajs;
	}

	public Vector<Auto> getAutaKupujacego() {
		Vector<Auto> v = new Vector<Auto>();
		autaKupujacego.values().stream().forEach(a -> v.add(a));
		return v;
	}

	private class ProbaKupna extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage wiadomosc = receive();
			if (wiadomosc != null) {
				AID wysylajacy = wiadomosc.getSender();
				ACLMessage odpowiedz = new ACLMessage(ACLMessage.CFP);

				if (wiadomosc.getContent().equals("")) {
					System.out.println("Agent " + getAID().getName() + " nie kupi³ tego auta.");
				} else {
					System.out.println("Agent " + getAID().getName() + " kupi³ auto.");
					Auto auto = Auto.fromString(wiadomosc.getContent());
					autaKupujacego.put(auto.marka + "-" + auto.model, auto);
					myGui.jComboBox.addItem(wiadomosc.getContent());
					myGui.jLabelHajs.setText(Integer.valueOf(myGui.jLabelHajs.getText()) - (auto.cena + auto.kosztyDodatkowe)
							+ "");
				}
			}
		}

	}
}
