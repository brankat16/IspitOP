package com.ispit.OP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Radnja {
	
	private String nazivRadnje = "";
	private String adresaRadnje = "";
	private String emailRadnje = "";
	private ArrayList <Vino> listaVina = new ArrayList<>();
	
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	
	public Radnja() {
		super();
	}
	
	public Radnja(String nazivRadnje, String adresaRadnje, String emailRadnje, ArrayList<Vino> listaVina) {
		super();
		this.nazivRadnje = nazivRadnje;
		this.adresaRadnje = adresaRadnje;
		this.emailRadnje = emailRadnje;
		this.listaVina = listaVina;
	}

	public String getNazivRadnje() {
		return nazivRadnje;
	}

	public void setNazivRadnje(String nazivRadnje) {
		this.nazivRadnje = nazivRadnje;
	}

	public String getAdresaRadnje() {
		return adresaRadnje;
	}

	public void setAdresaRadnje(String adresaRadnje) {
		this.adresaRadnje = adresaRadnje;
	}

	public String getEmailRadnje() {
		return emailRadnje;
	}

	public void setEmailRadnje(String emailRadnje) {
		this.emailRadnje = emailRadnje;
	}

	public ArrayList<Vino> getListaVina() {
		return listaVina;
	}

	public void setListaVina(ArrayList<Vino> listaVina) {
		this.listaVina = listaVina;
	}

	@Override
	public String toString() {
		//return "Radnja [nazivRadnje=" + nazivRadnje + ", adresaRadnje=" + adresaRadnje + ", emailRadnje=" + emailRadnje
			//	+ ", listaVina=" + listaVina + "]";
		String podaci = "";
		podaci += "Naziv: " + nazivRadnje + "\n";
		podaci += "Adresa: " + adresaRadnje + "\n";
		podaci += "Telefon: " + emailRadnje + "\n";
		podaci += "Vina: " + listaVina.size() + "\n";
		double suma = 0;
		for (int i = 0; i < listaVina.size(); i++) {
		suma += listaVina.get(i).getCena();
		}
		podaci += "Ukupna cena: " + suma;
		podaci += "Broj chardonnay vina" + brojVinaPoVrsti("chardonnay");
		return podaci;
		
		}
	private double prosecnaCenaVinaPoVrsti(String vrsta, int unesenaKlicina) {
		int kolicina = 0;
		double ukupnaCena = 0;
		for (int i =0; i<listaVina.size(); i++) {
			if (listaVina.get(i).getVrsta().equals(vrsta)) {
				kolicina ++;
				ukupnaCena = ukupnaCena + listaVina.get(i).getCena();
			}
		}
		if (kolicina > unesenaKlicina) {
			return ukupnaCena / kolicina; 
		}
		else {
			System.out.println ("Nije pronadjeno ni jedno vino ");
			return 0.0;
		}
	}
	
	private int brojVinaPoVrsti(String vrsta) {
		int kolicina = 0;
		for (int i =0; i<listaVina.size(); i++) {
			if (listaVina.get(i).getVrsta().equals(vrsta)) {
				kolicina ++;
			}
		}
		return kolicina;
	}
	

	public void load(String path) {
		try {
			List<String> podaci = Files.readAllLines(Paths.get(path));
			nazivRadnje = podaci.get(0);
			adresaRadnje = podaci.get(1);
			emailRadnje = podaci.get(2);

			listaVina.clear();

			for (int i = 3; i < podaci.size(); i++) {
				String linija = podaci.get(i);
				String[] podaciVino = linija.split(";");
				Vino vino = new Vino(Integer.parseInt(podaciVino[0]), podaciVino[1], Integer.parseInt(podaciVino[2]),
						Double.parseDouble(podaciVino[3]), podaciVino[4], LocalDate.parse(podaciVino[5], dtf),
						Integer.parseInt(podaciVino[6]));
				listaVina.add(vino);
			}
		} catch (IOException e) {
			System.out.println("Doslo je do greske prilikom ucitavanja podataka.");
		}
	}

		public void save(String path) {
			ArrayList<String> podaci = new ArrayList<>();
			podaci.add(nazivRadnje);
			podaci.add(adresaRadnje);
			podaci.add(emailRadnje);
			for (int i = 0; i < listaVina.size(); i++) {
				Vino v = listaVina.get(i);
				String linija = v.getId() + ";" + v.getNazivProizvodjaca() + ";" + v.getKolicina() + ";" + v.getCena()
						+ ";" + v.getVrsta() + ";" + v.getDatumNabavke().format(dtf) + ";" + v.getGodProizvodnje();
				podaci.add(linija);
			}
			try {
				Files.write(Paths.get(path), podaci);
			} catch (IOException e) {
				System.out.println("Doslo je do greske prilikom upisa podataka.");
			}
		}

		public boolean dodajNovoVino(Vino novoVino) {
			
			for (int i = 0; i < this.listaVina.size(); i++) {
				if (this.listaVina.get(i).getId() == novoVino.getId()) {
					return false;
				}
			}
			this.listaVina.add(novoVino);
			return true;
		}

		public void ispisiVina() {
			System.out.println(">>>>>>>>>>>Vina<<<<<<<<<<<<<<");
			for (int i = 0; i < this.listaVina.size(); i++) {
				System.out.println(this.listaVina.get(i));	
			}
		}

		public boolean izmeniVino(Vino izmenjenoVino) {
			try {
				for (int i = 0; i < this.listaVina.size(); i++) {
					if (this.listaVina.get(i).getId() == izmenjenoVino.getId()) {
						listaVina.set(i, izmenjenoVino);
						System.out.println("Uspesna izmena!");
						return true;
					}
				}
				System.out.println("Vino ne postoji");
				return false;
			} catch (Exception e) {
				return false;
			}
		}

		public boolean obrisiRacun(int id) {
			
			try {
				for (int i = 0; i < this.listaVina.size(); i++) {
					if (this.listaVina.get(i).getId() == id) {
						this.listaVina.remove(i);
						System.out.println("Vino je uspesno izbrisano!");
						return true;
					}
				}
				System.out.println("Vino sa unetim id-om ne postoji");
				return false;
			} catch (Exception e) {
				return false;
			}
		}

		public void nadjiNajskupljeVino(int godinaMin, int godinaMax) {
			ArrayList<Vino>pronadjenaVina = new ArrayList<>();
			
			
		}
		
	}

	


