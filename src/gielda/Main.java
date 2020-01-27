package gielda;

import java.util.HashMap;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {

	private static ContainerController containerController;
	private static AgentController agentController;

	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.instance();
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		profile.setParameter(Profile.GUI, "true");
		containerController = runtime.createMainContainer(profile);
	}

	private static HashMap<String, Auto> getAutaAgentaSprzedajacego1() {
		HashMap<String, Auto> auta = new HashMap<String, Auto>();
		Auto auto;
		auto = new Auto("mercedes", "190", "sedan", "benzyna", 2000, 1991, 8000, 1000);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("peugeot", "308", "kombi", "benzyna", 1600, 2009, 16900, 200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("volkswagen", "polo", "coupe", "diesel", 1600, 2009, 17500, 0);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("audi", "a3", "kombi", "diesel", 2000, 2005, 10800, 1200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("citroen", "saxo", "male", "benzyna", 1224, 1997, 1500, 200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("mini", "countryman", "male", "diesel", 2000, 2012, 55000, 2000);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("citroen", "c4", "sedan", "diesel", 1560, 2018, 23000, 1200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("mitsubishi", "grandis", "coupe", "diesel", 1400, 2009, 8000, 2000);
		auta.put(auto.marka + "|" + auto.model, auto);
		return auta;
	}

	private void getAutaAgentaSprzedajacego2() {
		HashMap<String, Auto> auta = new HashMap<String, Auto>();
		Auto auto;
		auto = new Auto("mercedes", "w213", "sedan", "diesel", 2000, 2019, 250000, 0);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("bmw", "f10", "kombi", "benzyna", 1600, 2016, 120000, 0);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("volkswagen", "touran", "duze", "diesel", 1900, 2007, 10000, 1200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("audi", "a4", "kombi", "diesel", 2000, 2013, 47800, 3000);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("volkswagen", "golf", "", "benzyna", 1224, 1997, 1500, 200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("renault", "modus", "male", "diesel", 2000, 2012, 55000, 2000);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("bmw", "x5", "sedan", "diesel", 1560, 2018, 23000, 1200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("opel", "vectra", "coupe", "diesel", 1400, 2009, 8000, 2000);
		auta.put(auto.marka + "|" + auto.model, auto);
	}

	private void getAutaAgentaSprzedajacego3() {
		HashMap<String, Auto> auta = new HashMap<String, Auto>();
		Auto auto;
		auto = new Auto("bmw", "s5", "sedan", "benzyna", 2000, 1991, 8000, 1000);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("seat", "exeo", "kombi", "benzyna", 1600, 2009, 16900, 200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("skoda", "yeti", "coupe", "diesel", 1600, 2009, 17500, 0);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("volkswagen", "passat", "kombi", "diesel", 2000, 2005, 10800, 1200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("ford", "fiesta", "male", "benzyna", 1224, 1997, 1500, 200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("volkswagen", "golf", "male", "diesel", 2000, 2012, 55000, 2000);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("bmw", "s4", "sedan", "diesel", 1560, 2018, 23000, 1200);
		auta.put(auto.marka + "|" + auto.model, auto);
		auto = new Auto("ford", "kuga", "coupe", "diesel", 1400, 2009, 8000, 2000);
		auta.put(auto.marka + "|" + auto.model, auto);
	}

}
