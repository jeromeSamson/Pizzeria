package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class UpdatePizza extends OptionMenu {
	String libelle = "3. Modifier une pizza";
	Scanner saisie = new Scanner(System.in);
	private static final Logger LOG = LoggerFactory.getLogger(SaveNewPizza.class);

	public UpdatePizza(IPizzaDao dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Mise a jour d'une pizza On verifie si la liste des pizzas n'est pas vide
	 * On demande le code de la pizza � modifier On v�rifie que le code saisi
	 * existe Si non on redemande une saisie Si oui on demande le nouveau nom,
	 * le nouveau code qui peut �tre identique que celui saisi pr�cedemment mais
	 * différent d'un déja présent dans la liste le nouveau Prix (on verifie la
	 * saisie) la nouvelle categorie (on verifie la saisie)
	 */
	@Override
	public boolean execute() throws UpdatePizzaException {
		String nom, code, nouvCode;
		double prix;
		String categorie;
		saisie.useLocale(Locale.US);
		if (dao.isEmpty()) {
			LOG.info("Aucune pizza dans la base de données.\n Veuillez en ajouter une. ");
			return false;
		}
		LOG.info("Veuillez saisir le code de la pizza à modifier (quit pour quitter) : \n");
		code = saisie.next();
		while (!dao.pizzaExist(code.toUpperCase())) {
			if (code.equalsIgnoreCase("QUIT")) {
				return false;
			}
			LOG.info("Erreur le code saisi n'existe pas ");
			LOG.info("Veuillez saisir le code de la pizza à modifier : \n");
			code = saisie.next();
		}

		LOG.info("Veuillez saisir le nouvau nom de la pizza : ");
		nom = saisie.next();
		if (nom.equalsIgnoreCase("QUIT")) {
			return false;
		}
		LOG.info("Veuillez saisir le nouveau code de la pizza : ");
		nouvCode = saisie.next();
		if (nouvCode.equalsIgnoreCase("QUIT")) {
			return false;
		}
		while (dao.pizzaExist(nouvCode)) {
			if (nouvCode.equalsIgnoreCase("QUIT")) {
				return false;
			}
			if (nouvCode.equalsIgnoreCase(code)) {
				break;
			}
			LOG.info("Erreur le code saisi existe déjà");
			LOG.info("Veuillez saisir le nouveau code de la pizza : ");
			nouvCode = saisie.next();
		}
		CategoriePizza cate;
		cate = verifCate();
		try {
			prix = getPrix();
			if (prix == 0.0) {
				return false;
			}
			if (dao.updatePizza(code.toUpperCase(), new Pizza(nom, nouvCode.toUpperCase(), prix, cate))) {
				LOG.info("Pizza modifier");
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			LOG.info(
					"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
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
		while (prix <= 0.0) {
			try {
				LOG.info("Veuillez saisir le nouveau prix de la pizza : ");
				prixStr = saisie.next();
				if (prixStr.toUpperCase().equals("QUIT")) {
					return 0.0;
				}
				prix = Double.parseDouble(prixStr);

			} catch (InputMismatchException e1) {
				LOG.info(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			} catch (NumberFormatException e) {
				LOG.info(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			}
			if (prix <= 0.0) {
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
		LOG.info("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
		String cate = saisie.next();
		boolean sortieWhile = false;
		String[] split = CategoriePizza.listEnum().split(" ");
		while (!sortieWhile) {
			for (int i = 0; i < split.length; i++) {
				if (split[i].toUpperCase().toString().equals(cate.toUpperCase().toString())) {
					switch (i) {
					case 0:
						return CategoriePizza.VIANDE;

					case 1:
						return CategoriePizza.POISSON;

					case 2:
						return CategoriePizza.SANS_VIANDES;

					default:
						break;
					}
				}
			}
			LOG.info("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
			cate = saisie.next();

		}

		return null;
	}

}
