package fr.pizzeria.ihm.utils;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.menu.option.SaveNewPizza;
import fr.pizzeria.model.CategoriePizza;

public class VerificationSaisie {

	/**
	 * Vérifiaction de la saisie du prix (si il n'y a pas de virgule a la place
	 * du . Si il n'y a pas de lettre dans la valeur
	 * 
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SaveNewPizza.class);
	private final static String ERREUR_SAISIE = "Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ";

	private VerificationSaisie() {
		throw new IllegalStateException("Utility class");
	}

	public static double verifSaisiePrix() {
		Scanner saisie = new Scanner(System.in);
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
				LOG.debug(ERREUR_SAISIE, e1);
				LOG.info(ERREUR_SAISIE);

				prix = 0.0D;
			} catch (NumberFormatException e) {
				LOG.debug(ERREUR_SAISIE, e);
				LOG.info(ERREUR_SAISIE);

				prix = 0.0D;
			} catch (NoSuchElementException e2) {
				LOG.debug(ERREUR_SAISIE, e2);
				LOG.info(ERREUR_SAISIE);

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
		Scanner saisie = new Scanner(System.in);
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
				LOG.debug("Veuillez saisir une des catégorie suivante : {}", CategoriePizza.listEnum(), e);
				LOG.info("Veuillez saisir une des catégorie suivante : {}", CategoriePizza.listEnum());
			} catch (NoSuchElementException e1) {
				LOG.debug("Veuillez saisir une des catégorie suivante : {}", CategoriePizza.listEnum(), e1);
				LOG.info("Veuillez saisir une des catégorie suivante : {}", CategoriePizza.listEnum());
			}

		}

		return null;

	}

}
