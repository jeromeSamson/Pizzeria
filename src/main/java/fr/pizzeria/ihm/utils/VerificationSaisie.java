package fr.pizzeria.ihm.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.SaveNewPizza;
import fr.pizzeria.model.CategoriePizza;

public class VerificationSaisie {

	/**
	 * Vérifiaction de la saisie du prix (si il n'y a pas de virgule a la place
	 * du . Si il n'y a pas de lettre dans la valeur
	 * 
	 * @return
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SaveNewPizza.class);
	static Scanner saisie = new Scanner(System.in);

	private VerificationSaisie() {
		throw new IllegalStateException("Utility class");
	}

	public static double getPrix() {
		String prixStr = null;
		double prix = 0.0;
		while (prix <= 0.0D) {
			try {
				LOG.info("Veuillez saisir le nouveau prix de la pizza : ");
				prixStr = saisie.next();
				if ("QUIT".equalsIgnoreCase(prixStr)) {
					return 0.0D;
				}
				prix = Double.parseDouble(prixStr);

			} catch (InputMismatchException e1) {
				LOG.debug(e1.getMessage());
				LOG.info(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");

				prix = 0.0D;
			} catch (NumberFormatException e) {
				LOG.debug(e.getMessage());
				LOG.info(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");

				prix = 0.0D;
			}
			if (prix <= 0.0D) {
				LOG.info("Erreur le prix doit être supérieur à 0");
			}
		}
		return prix;
	}

	/**
	 * 
	 * @return Categorie de la pizza V�rifie que la saisie de la categorie
	 *         correspond une categorie existante.
	 * 
	 */
	public static CategoriePizza verifCate() {
		LOG.info("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
		String cate;
		boolean sortieWhile = false;

		while (!sortieWhile) {
			try {
				cate = saisie.next();
				if ("QUIT".equalsIgnoreCase(cate)) {
					return null;
				} else {
					return CategoriePizza.valueOf(cate.toUpperCase());
				}
			} catch (IllegalArgumentException e) {
				LOG.debug(e.getMessage());
				LOG.info("Veuillez saisir une des catégorie suivante : ");
				LOG.info(CategoriePizza.listEnum());

			}

		}

		return null;

	}

}
