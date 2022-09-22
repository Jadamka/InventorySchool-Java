package Items;

public class Zbran extends Predmet {
	private final int niceni = 10;
	private int damage;
	private Typ typ;
	private int stav;
	
	public int getDamage() {
		return this.damage;
	}
	public Typ getTyp() {
		return this.typ;
	}
	public int getStav() {
		return this.stav;
	}
	private void setStav(int stav) {
		this.stav = stav;
		if(this.stav < 0)
			this.stav = 0;
		if(this.getStav() <= 10)
			this.setZniceno(true);
	}
	
	public Zbran(String nazev, double vaha, String popis, int damage, Typ typ, int stav){
		super(nazev, vaha, popis);
		this.damage = damage;
		this.typ = typ;
		setStav(stav);
	}
	
	public int Use() {
		if(!this.getZniceno()) {
			System.out.println("Zbraň byla použita");
			setStav(this.getStav() - niceni);
			return this.damage;
		}
		else
			System.out.println("Tento předmět je zničenej");
		return 0;
	}
	
	public String Info() {
		return "Název: " + this.getNazev() + "\nVáha: " + this.getVaha() + "\nPopis: " + this.getPopis() + 
				"\nDamage: " + this.getDamage() + "\nTyp utoku: " + this.getTyp() + "\nStav: " + this.getStav() + 
				"\nZničeno: " + this.getZniceno();
	}
}
