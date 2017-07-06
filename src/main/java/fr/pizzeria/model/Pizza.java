package fr.pizzeria.model;

public class Pizza {
	private String nom;
	private String code;
	private double prix;
	private int id;
	static int nbPizza = 0;
	public static int getNbPizza() {
		return nbPizza;
	}
	public static void setNbPizza(int nbPizza) {
		Pizza.nbPizza = nbPizza;
	}
	public Pizza(String nom, String code, double prix ){
		this.nom = nom;
		this.code = code;
		this.prix = prix;
	}
	public Pizza() {
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return  nom + "  " + code + "  " + prix +"\n";
	}
	
}
