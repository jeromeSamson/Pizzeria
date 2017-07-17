package fr.pizzeria.model;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.SaveNewPizza;

public class Pizza {
	@ToString
	private String nom;
	// @ToString
	private String code;
	private static final Logger LOG = LoggerFactory.getLogger(SaveNewPizza.class);
	private double prix;
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

	/**
	 * Affiche uniquement les attributs de la classe pizza étant annoté par
	 * l'annotation @ToString Par default en minuscule.
	 */
	@Override
	public String toString() {
		StringBuilder retour = new StringBuilder();
		retour.append("");
		// Parcours de la liste des attributs de la classe Pizza
		for (Field field : this.getClass().getDeclaredFields()) {
			// Si l'attribut est annoté alors la condition est réussi
			// Sinon on ne fait rien
			if (field.getDeclaredAnnotation(ToString.class) != null) {
				try {
					// On Déclare un objet qui va récuperer la variable anoté
					Object obj = field.get(this);
					// On vérifie si le paramètre toUpperCase de l'annotation
					// est a vrai ou faux.
					if (field.getDeclaredAnnotation(ToString.class).toUpperCase()) {
						retour.append(obj.toString().toUpperCase()).append(" ");
					} else {
						retour.append(obj.toString().toLowerCase()).append(" ");
					}

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					LOG.info("Illegal Argument");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					LOG.info("Illegal Access");
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
