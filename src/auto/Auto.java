package auto;

public class Auto {

	public String marka;
	public String model;
	public String typNadwozia;
	public String typSilnika;
	public int pojemnoscSilnika;
	public int rokProdukcji;
	public int cena;
	public int kosztyDodatkowe;

	public Auto(String marka, String model, String typNadwozia, String typSilnika, int pojemnoscSilnika, int rokProdukcji,
			int cena, int kosztyDodatkowe) {
		super();
		this.marka = marka;
		this.model = model;
		this.typNadwozia = typNadwozia;
		this.typSilnika = typSilnika;
		this.pojemnoscSilnika = pojemnoscSilnika;
		this.rokProdukcji = rokProdukcji;
		this.cena = cena;
		this.kosztyDodatkowe = kosztyDodatkowe;
	}

}
