package fr.pizzeria.model;

public enum CategoriePizza {
	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDES("Sans viandes");

	private String categorie;

	private CategoriePizza(String categorie) {
		this.categorie = categorie;

	}

	public String getCategorie() {
		return categorie;
	}

	public static String listEnum() {
		return VIANDE.toString() + " " + POISSON.toString() + " " + SANS_VIANDES.toString();
	}
}
