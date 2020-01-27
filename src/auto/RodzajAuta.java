package auto;

public class RodzajAuta {

	public String marka;
	public String model;

	public RodzajAuta(String marka, String model) {
		super();
		this.marka = marka;
		this.model = model;
	}

	@Override
	public String toString() {
		return marka + "|" + model;
	}

	public static RodzajAuta fromString(String string) {
		String[] strings = string.split("|");
		return new RodzajAuta(strings[0], strings[1]);
	}

}
