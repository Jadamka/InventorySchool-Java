package InventoryDB;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import Items.*;

public class Inventar {
	private Scanner myVar = new Scanner(System.in);
	private List<Predmet> inventar;
	private double kapacita;
	private String path = "db.txt"; // File
	private File file = new File(path);
	
	public double getKapacita() {
		return this.kapacita;
	}
	public void setKapacita(double kapacita) {
		this.kapacita = kapacita;
	}
	
	public Inventar(int kapacita) {
		this.setKapacita(kapacita);
		
		inventar = new ArrayList<Predmet>();
	}
	
	public void Vlozit(Predmet predmet) {
		if(this.getKapacita() - predmet.getVaha() >= 0) {
			this.setKapacita(this.getKapacita() - predmet.getVaha());
			inventar.add(predmet);
			System.out.println("Předmět byl úspěšně přidán do inventáře");
		}
		else {
			System.out.println("V inventáři není místo");
		}
	}
	
	public void Info() {
		for(Predmet predmet: inventar)
			System.out.println(predmet.Info() + "\n");
	}
	
	public void UseLektvar() {
		int index = 1;
		
		for(Predmet predmet: inventar) {
			if(predmet instanceof Lektvar) {
				System.out.println(index + ".\n" + predmet.Info());
				index++;
			}
		}
		
		int volba = myVar.nextInt();
		
		if(volba < 0 || volba >= index) {
			System.out.println("Špatný číslo");
			return;
		}
		
		if(volba != 0) {
			index = 1;
			for(Predmet predmet: inventar) {
				if(predmet instanceof Lektvar) {
					if(volba == index) {
						((Lektvar)predmet).Use();
					}
					index++;
				}
			}
		}
	}
	
	public void UseZbran() {
		int index = 1;
		
		for(Predmet predmet: inventar) {
			if(predmet instanceof Zbran) {
				System.out.println(index + ".\n" + predmet.Info());
				index++;
			}
		}
		
		int volba = myVar.nextInt();
		
		if(volba < 0 || volba >= index) {
			System.out.println("Špatný číslo");
			return;
		}
		
		if(volba != 0) {
			index = 1;
			for(Predmet predmet: inventar) {
				if(predmet instanceof Zbran) {
					if(volba == index) {
						((Zbran)predmet).Use();
					}
					index++;
				}
			}
		}
	}
	
	public void UseZbroj(int sila, Typ typ) {
		int index = 1;
		
		for(Predmet predmet: inventar) {
			if(predmet instanceof Zbroj) {
				System.out.println(index + ".\n" + predmet.Info());
				index++;
			}
		}
		
		int volba = myVar.nextInt();
		
		if(volba < 0 || volba >= index) {
			System.out.println("Špatný číslo");
			return;
		}
		
		if(volba != 0) {
			index = 1;
			for(Predmet predmet: inventar) {
				if(predmet instanceof Zbroj) {
					if(volba == index) {
						((Zbroj)predmet).Use(sila, typ);
					}
					index++;
				}
			}
		}
	}
	
	public void Vyhodit() {
		int index = 1;
		for(Predmet predmet: inventar) {
			System.out.println((index++) + "\n" + predmet.Info() + "\n");

		}
		
		int volba = myVar.nextInt() - 1;
		
		if(volba < 0 || volba > inventar.size() - 1) {
			System.out.println("Špatný číslo");
			return;
		}
		
		inventar.remove(volba);
		System.out.println("Předmět byl úspěšně vymazán");
	}
	
	public void VyhoditZnicene() {
		for(Predmet predmet: inventar) {
			if(predmet.getZniceno()) {
				System.out.println(predmet.Info());
			}
		}
		
		System.out.print("Přejete si vymazat zničené předměty (a/n): ");
		char volba = myVar.next().charAt(0);
		
		switch(volba) {
			case 'a':
				for(int i = inventar.size() - 1; i >= 0; i--) {
					if(inventar.get(i).getZniceno()) {
						inventar.remove(i);
					}
				}
				break;
			case 'n':
				break;
			default:
				System.out.println("Špatná hodnota");
				break;
		}
	}
	
	public void Export() {
		try {
			if(!file.exists()) {
				Formatter f = new Formatter(file);
			}
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(Predmet pr: inventar) {
				bw.write(pr.Info().replace("\n", ";"));
				bw.newLine();
			}
			bw.close();
			System.out.println("Export proběhl úspěšně");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Import() {
		try {
			Scanner read = new Scanner(file);
			
			String[] words;
			
			while(read.hasNext()) {
				words = read.nextLine().split(";");
				for(int i = 0; i < words.length; i++)
					words[i] = words[i].substring(words[i].indexOf(":") + 2);
				
				// Leaktvar
				if(words.length == 5) {
					Vlozit(new Lektvar(words[0], Double.parseDouble(words[1]), words[2], Integer.parseInt(words[3]), Boolean.parseBoolean(words[4])));
				}
				// Zbran
				else if(words.length == 7) {
					Vlozit(new Zbran(words[0], Double.parseDouble(words[1]), words[2], Integer.parseInt(words[3]), Typ.valueOf(words[4]), Integer.parseInt(words[5])));
				}
				// Zbroj
				else if(words.length == 8) {
					for(int i = 0; i < 7; i++) {
						System.out.println(words[i]);
					}
					Vlozit(new Zbroj(words[0], Double.parseDouble(words[1]), words[2], Boolean.parseBoolean(words[3]), Boolean.parseBoolean(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6])));
				}
			}
			
			read.close();
			System.out.println("Export proběhl úspěšně");
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
