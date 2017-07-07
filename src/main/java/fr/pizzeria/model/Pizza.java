package fr.pizzeria.model;

import java.lang.reflect.Field;

public class Pizza {
	@ToString(toUpperCase = false)
	private String nom;
//	@ToString(toUpperCase = true)
	private String code;

	private double prix;
	private int id;
	private CategoriePizza categorie;

	public Pizza(String nom, String code, double prix, CategoriePizza categorie) {
		this.nom = nom.trim();
		this.code = code.trim();
		this.prix = prix;
		this.categorie = categorie;
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
			StringBuilder retour = new StringBuilder();
			boolean toUpper = false;
			retour.append("");
			for(Field field : this.getClass().getDeclaredFields()){
					
				if(field.getDeclaredAnnotation(ToString.class)!=null){
					try {
					Object obj = field.get(this);
					if(field.getDeclaredAnnotation(ToString.class).toUpperCase()){
						retour.append(obj.toString().toUpperCase()).append(" ");
					}else{
						retour.append(obj.toString().toLowerCase()).append(" ");
					}
			
					
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
			return retour.toString();

		
		
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

}
