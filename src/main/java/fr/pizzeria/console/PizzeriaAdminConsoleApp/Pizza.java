package fr.pizzeria.console.PizzeriaAdminConsoleApp;

public class Pizza {
	String nom;
	String code;
	double prix;
	public Pizza(String pnom, String pcode, double d ){
		nom = pnom;
		code = pcode;
		prix = d;
	}
	public void setPrix(float pprix){
		prix = pprix;
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
	@Override
	public String toString() {
		return "Pizza [nom=" + nom + ", code=" + code + ", prix=" + prix + "]";
	}
	
}
