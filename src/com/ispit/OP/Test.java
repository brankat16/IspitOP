package com.ispit.OP;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Test {

	public static Scanner sc = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	
	public static void main(String[] args) {
		
		Radnja radnja = new Radnja();
		radnja.load("fajlVina.txt");
		String opcija;
		
		do {
			System.out.println(">>>>>>>>>MENI<<<<<<<<<<<<");
			System.out.println("1. Unos podataka o novoj radnji");
			System.out.println("2. Unos novog vina");
			System.out.println("3. Ispis podataka o svim vinima");
			System.out.println("4. Izmena podataka o vinu");
			System.out.println("5. Brisanje podataka o vinu");
			System.out.println("6. Pretraga po proizvodjacima vina");
			System.out.println("7. Pretraga po periodu nabavke");
			System.out.println("8. Prosecna cena vina odredjenog tipa");
			System.out.println("9. Najskuplje vino za zadatu godinu proizvodnje");
			System.out.println("10. Ispis podataka o radnji");
			System.out.println("x. Izlaz");
			System.out.println("Odaberite opciju:");
			
			opcija = sc.nextLine();
			
		switch (opcija) {
		case "1":
			unesiRadnju (radnja);
			break;
		case "2":
			unesiVino(radnja);
			radnja.save("fajlVina.txt");
			break;
		case "3":
			ispisiSvaVina(radnja);
			break;
		case "4":
			izmeniVino(radnja);
			radnja.save("fajlVina.txt");
			break;
		case "5":
			brisiVino(radnja);
			radnja.save("fajlVina.txt");
			break;
		case "9":
			pronadjiNajskupljevino (radnja);
			break;
		case "10":
			ispisiPodatkeORadnji(radnja);
		break;
		
		default:
			System.out.println("Opcija ne postoji.");
			break;
		}
		} while (!opcija.equals("x"));
		sc.close();
		radnja.save("fajlVina");
	

	
}
	private static void pronadjiNajskupljevino(Radnja radnja) {
		
			String godinaMinString;
			int godinaMin;
			do {
			System.out.println("Unesite pocetnu godinu");
			godinaMinString = sc.nextLine();
			} while(!proveriDatum(godinaMinString));
			godinaMin = Integer.parseInt(godinaMinString);
			String godinaMaxString;
			int godinaMax;
			do {
			System.out.println("Unesite krajnju godinu");
			godinaMaxString = sc.nextLine();
			} while(!proveriDatum(godinaMaxString));
			godinaMax = Integer.parseInt(godinaMaxString);
			radnja.nadjiNajskupljeVino(godinaMin, godinaMax); 
			} 

	private static void ispisiPodatkeORadnji(Radnja radnja) {
		System.out.println(radnja);
	}
	
	private static void brisiVino(Radnja radnja) {

		String idString;
		int id;
		do {
			System.out.println("Unesite identifikator");
			idString = sc.nextLine();
		} while (!proveriId(idString));
		id = Integer.parseInt(idString);
		radnja.obrisiRacun(id); // prosledjuje int id
	}
	
	private static void izmeniVino(Radnja radnja) {
		String idString;
	    int id;
	    do {
	    System.out.println("Unesite identifikator");
	    idString = sc.nextLine();
	    } while (!proveriId(idString));
	    id = Integer.parseInt(idString);
	    
	    System.out.println("Unesite naziv proizvodjaca");
	    String naziv = sc.nextLine();
	    
	    String kolicinaString;
	    int kolicina;
	    do {
	    System.out.println("Unesite raspolozivu kolicinu");
	    kolicinaString = sc.nextLine();
	    } while (!isInteger(kolicinaString));
	    kolicina = Integer.parseInt(kolicinaString);
	    
	    String cenaString;
	    int cena;
	    do {
	    System.out.println("Unesite cenu");
	    cenaString = sc.nextLine();
	    } while (!proveriCena(cenaString));
	    cena = Integer.parseInt(cenaString);
	    
	    String vrsta;
	    do {
			System.out.print("Unesite vrstu: ");
			vrsta = sc.nextLine();
	    } while (!proveriVrstu(vrsta));
		
	    String datumString;
	    LocalDate datum;
	    do {
	    System.out.println("Unesite datum nabavke");
	    datumString = sc.nextLine();
	    } while(!proveriDatum(datumString));
	    datum = LocalDate.parse(datumString, dtf);
	    
	    String godinaString;
	    int godina;
	    do {
	    System.out.println("Unesite godinu proizvodnje");
	    godinaString = sc.nextLine();
	    } while (!isInteger(godinaString));
	    godina = Integer.parseInt(godinaString);
	    
	    Vino izmenjenoVino = new Vino(id, naziv, kolicina, cena, vrsta, datum, godina);
		boolean flag = radnja.izmeniVino(izmenjenoVino);

		if (flag) {
			System.out.println("Uspesno je izmenjeno vino");
		} else {
			System.out.println("Vino sa unetim id-om nije pronadjeno");
		}
		
	}
	private static void ispisiSvaVina(Radnja radnja) {
		radnja.ispisiVina();
	}
	//unosi se id (provera), nazivProizvodjaca, kolicina, cena (provera), vrsta (provera), datum, godina
	private static void unesiVino(Radnja radnja) {
	
	    String idString;
	    int id;
	    do {
	    System.out.println("Unesite identifikator");
	    idString = sc.nextLine();
	    } while (!proveriId(idString));
	    id = Integer.parseInt(idString);
	    
	    System.out.println("Unesite naziv proizvodjaca");
	    String naziv = sc.nextLine();
	    
	    String kolicinaString;
	    int kolicina;
	    do {
	    System.out.println("Unesite raspolozivu kolicinu");
	    kolicinaString = sc.nextLine();
	    } while (!isInteger(kolicinaString));
	    kolicina = Integer.parseInt(kolicinaString);
	    
	    String cenaString;
	    int cena;
	    do {
	    System.out.println("Unesite cenu");
	    cenaString = sc.nextLine();
	    } while (!proveriCena(cenaString));
	    cena = Integer.parseInt(cenaString);
	    
	    String vrsta;
	    do {
			System.out.print("Unesite vrstu: ");
			vrsta = sc.nextLine();
	    } while (!proveriVrstu(vrsta));
		
	    String datumString;
	    LocalDate datum;
	    do {
	    System.out.println("Unesite datum nabavke");
	    datumString = sc.nextLine();
	    } while(!proveriDatum(datumString));
	    datum = LocalDate.parse(datumString, dtf);
	    
	    String godinaString;
	    int godina;
	    do {
	    System.out.println("Unesite godinu proizvodnje");
	    godinaString = sc.nextLine();
	    } while (!isInteger(godinaString));
	    godina = Integer.parseInt(godinaString);
	    
	    Vino novoVino = new Vino(id, naziv, kolicina, cena, vrsta, datum, godina);
		boolean flag = radnja.dodajNovoVino(novoVino);

		if (flag) {
			System.out.println("Uspesno je dodato novo vino");
		} else {
			System.out.println("Vino sa unetim id-om vec postojii. Pokusajte ponovo");
		}
	}
	
	private static boolean proveriVrstu(String vrsta) {
		try {
			if (vrsta.equals("caberne") || vrsta.equals("merlot") || vrsta.equals("chardonnay")
					|| vrsta.equals("rose")) {
				return true;
			}
			System.out.println("Vrsta mora biti caberne, merlot, chardonnay ili rose");
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean proveriDatum(String datumString) {
		try {
			LocalDate datum = LocalDate.parse(datumString, dtf);
			return true;

		} catch (Exception e) {
			System.out.println("Datum mora imati format dd.MM.yyyy.");
			return false;
		}
	}
	
	private static boolean proveriCena(String cenaString) {
		try {
			double cena = Double.parseDouble(cenaString);
			if (cena >= 100) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			System.out.println("Cena mora biti broj i ne sme imati vrednost manju od 100");
			return false;
		}
	}
	
	private static boolean isInteger(String string) {
		
		try {
			int broj = Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			System.out.println("Uneta vrednost mora biti broj");
			return false;
		}
	}
	private static boolean proveriId(String idString) {

		try {
			int id = Integer.parseInt(idString);
			if (id >= 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("Id mora biti broj i ne sme imati manju vrednost od 1");
			return false;
		}
	}
	
	//unose se naziv, adresa i email radnje
	private static void unesiRadnju(Radnja radnja) {
	
		System.out.println("Naziv:");
		String naziv = sc.nextLine();
		System.out.println("Adresa:");
		String adresa = sc.nextLine();
		System.out.println("Email:");
		String email = sc.nextLine();
		
		radnja.setNazivRadnje(naziv);
		radnja.setAdresaRadnje(adresa);
		radnja.setEmailRadnje(email);
		System.out.println("Radnja je uspesno uneta");
	}
}
