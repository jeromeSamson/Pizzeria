package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class SaveNewPizza extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(SaveNewPizza.class);
	String libelle = "2. Ajouter une pizza";
	Scanner saisie = new Scanner(System.in);

	public SaveNewPizza(IPizzaDao dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Méthode pour enregistr� une nouvelle pizza Verifie si : - le code saisie
	 * n'existe pas déja - la categorie est bien saisie - le prix est bien
	 * saisie Appel la methode saveNewPizza de la DAOS
	 * 
	 */
	@Override
	public boolean execute() {
		double prix;
		saisie.useLocale(Locale.US);
		LOG.info("Veuillez saisir le code de la nouvelle pizza (quit pour quitter) : ");
		String code = saisie.next();
		while (dao.pizzaExist(code.toUpperCase())) {
			LOG.info("Erreur le code saisi existe déja ");
			LOG.info("Veuillez saisir le code de la nouvelle pizza (quit pour quitter) : \n");
			code = saisie.next();
			if ("QUIT".equalsIgnoreCase(code)) {
				return false;
			}

		}
		LOG.info("Veuillez saisir le nom de la nouvelle pizza (quit pour quitter) : ");
		String nom = saisie.next();
		if ("QUIT".equalsIgnoreCase(nom)) {
			return false;
		}
		CategoriePizza cate;
		cate = verifCate();
		if (cate == null) {
			return false;
		}
		try {
			prix = getPrix();

			if (dao.saveNewPizza(new Pizza(nom, code.toUpperCase(), prix, cate))) {
				LOG.info("Pizza enregistrée");
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			LOG.info(
					"Erreur à la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");

		}
		return false;

	}

	/**
	 * V�rifiaction de la saisie du prix (si il n'y a pas de virgule a la place
	 * du . Si il n'y a pas de lettre dans la valeur
	 * 
	 * @return
	 */
	public double getPrix() {
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
	public CategoriePizza verifCate() {
		LOG.info("Veuillez saisir la categorie de la pizza :" + CategoriePizza.listEnum());
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
				LOG.info("Veuillez saisir une des catégorie suivante : ");
				LOG.debug(e.getMessage());
				LOG.info(CategoriePizza.listEnum());
			}

		}

		return null;

	}
}
