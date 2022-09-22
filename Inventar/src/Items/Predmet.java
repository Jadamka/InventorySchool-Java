package Items;

public abstract class Predmet {
	private String nazev;
	private double vaha;
	private String popis;
	private boolean zniceno;

	
	public String getNazev() {
		return this.nazev;
	}
	public double getVaha() {
		return this.vaha;
	}
	public String getPopis() {
		return this.popis;
	}
	public boolean getZniceno() {
		return this.zniceno;
	}
	protected void setZniceno(boolean stav) {
		this.zniceno = stav;
	}
	
	Predmet(String nazev, double vaha, String popis){
		this.nazev = nazev;
		this.vaha = vaha;
		this.popis = popis;
	}
	
	abstract public int Use();
	abstract public String Info();
}
