package Items;

public class Lektvar extends Predmet{
	private int ucinnost;
	
	public int getUcinnost() {
		return this.ucinnost;
	}
	
	public Lektvar(String nazev, double vaha, String popis, int ucinnost) {
		super(nazev, vaha, popis);
		this.ucinnost = ucinnost;
	}
	
	public Lektvar(String nazev, double vaha, String popis, int ucinnost, boolean zniceni) {
		super(nazev, vaha, popis);
		this.ucinnost = ucinnost;
		this.setZniceno(zniceni);
	}
	
	public int Use() {
		if(!this.getZniceno()) {
			System.out.println("Lektvar byl použit");
			this.setZniceno(true);
			return ucinnost;
		}
		System.out.println("Tento lektvar byl již použit");
		return 0;
	}
	
	public String Info() {
		return "Nazev: " + this.getNazev() + "\nVáha: " + this.getVaha() + "\nPopis: " + this.getPopis() + 
				"\nÚčinnost: " + this.getUcinnost() + "\nZničeno: " + this.getZniceno();
	}
}
