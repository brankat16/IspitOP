package com.ispit.OP;

import java.time.LocalDate;

public class Vino {
	
	private int id;
	private String nazivProizvodjaca = "";
	private int kolicina;
	private double cena;
	private String vrsta = "";
	private LocalDate datumNabavke;
	private int godProizvodnje;
	
	public Vino() {
		super();
	}

	public Vino(int id, String nazivProizvodjaca, int kolicina, double cena, String vrsta, LocalDate datumNabavke,
			int godProizvodnje) {
		super();
		this.id = id;
		this.nazivProizvodjaca = nazivProizvodjaca;
		this.kolicina = kolicina;
		this.cena = cena;
		this.vrsta = vrsta;
		this.datumNabavke = datumNabavke;
		this.godProizvodnje = godProizvodnje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazivProizvodjaca() {
		return nazivProizvodjaca;
	}

	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public LocalDate getDatumNabavke() {
		return datumNabavke;
	}

	public void setDatumNabavke(LocalDate datumNabavke) {
		this.datumNabavke = datumNabavke;
	}

	public int getGodProizvodnje() {
		return godProizvodnje;
	}

	public void setGodProizvodnje(int godProizvodnje) {
		this.godProizvodnje = godProizvodnje;
	}

	@Override
	public String toString() {
		return "Vino [id=" + id + ", nazivProizvodjaca=" + nazivProizvodjaca + ", kolicina=" + kolicina + ", cena="
				+ cena + ", vrsta=" + vrsta + ", datumNabavke=" + datumNabavke + ", godProizvodnje=" + godProizvodnje
				+ "]";
	}

	
	

}
