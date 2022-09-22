package Items;

public class Zbroj extends Predmet{
	private boolean odolnostOhen;
	private boolean odolnostJed;
	private int ochrana;
	private int stav;
	
	public boolean getOdolnostOhen() {
		return this.odolnostOhen;
	}
	public boolean getOdolnostJed() {
		return this.odolnostJed;
	}
	public int getOchrana() {
		return this.ochrana;
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
	
	public Zbroj(String nazev, double vaha, String popis, boolean odolnostOhen, boolean odolnostJed, int ochrana, int stav){
		super(nazev, vaha, popis);
		this.odolnostOhen = odolnostOhen;
		this.odolnostJed = odolnostJed;
		this.ochrana = ochrana;
		setStav(stav);
	}
	
	public int Use(int sila, Typ typ) {
		int utok = 0;
		if(!this.getZniceno()) {
			System.out.println("Zbroj byla použit");
			this.setStav(this.getStav() - (sila/5));
			
			if(typ == Typ.Ohen && this.getOdolnostOhen())
				utok = sila/(sila*3);
			else if(typ == Typ.Ohen && !this.getOdolnostOhen())
				utok = sila*2;
			else if(typ == Typ.Jed && this.getOdolnostJed())
				utok = sila/(sila*3);
			else if(typ == Typ.Jed && !this.getOdolnostJed())
				utok = sila*2;
		}		
		return utok;
	}
	
	public String Info() {
		return "Název: " + this.getNazev() + "\nVáha: " + this.getVaha() + "\nPopis: " + this.getPopis() + 
				"\nOdolnost Ohně: " + this.getOdolnostOhen() + "\nOdolnost jedu: " + this.getOdolnostJed() + 
				"\nOchrana: " + this.getOchrana() + "\nStav: " + this.getStav() + "\nZničeno: " + this.getZniceno();
	}
	
	@Override
	public int Use() {
		return 0;
	}
	
}
