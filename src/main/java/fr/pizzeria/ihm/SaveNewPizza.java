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
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	/**
	 * Méthode pour enregistré une nouvelle pizza Verifie si : - le code saisie
	 * n'existe pas déja - la categorie est bien saisie - le prix est bien
	 * saisie Appel la methode saveNewPizza de la DAOS
	 * 
	 */
	@Override
	public boolean execute() {
		String nom;
		String code;
		double prix;
		saisie.useLocale(Locale.US);
		LOG.info("Veuillez saisir le code de la nouvelle pizza : ");
		code = saisie.next();
		while (dao.pizzaExist(code.toUpperCase())) {
			LOG.info("Erreur le code saisi existe déja ");
			LOG.info("Veuillez saisir le code de la nouvelle pizza: \n");
			code = saisie.next();

		}
		LOG.info("Veuillez saisir le nom de la nouvelle pizza : ");
		nom = saisie.next();
		CategoriePizza cate;
		cate = verifCate();
		try {
			prix = getPrix();

			if (dao.saveNewPizza(new Pizza(nom, code.toUpperCase(), prix, cate))) {
				LOG.info("Pizza enregistrée");
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			LOG.info(
					"Erreur à la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");

		}
		return false;

	}

	/**
	 * Vérifiaction de la saisie du prix (si il n'y a pas de virgule a la place
	 * du . Si il n'y a pas de lettre dans la valeur
	 * 
	 * @return
	 */
	public double getPrix() {
		String prixStr = null;
		double prix = 0.0;
		while (prix <= 0.0) {
			try {
				LOG.info("Veuillez saisir le prix de la nouvelle pizza : ");
				prixStr = saisie.next();
				prix = Double.parseDouble(prixStr);
				if (prix <= 0.0) {
					LOG.info("Erreur le prix doit être supérieur à 0");
				}

			} catch (InputMismatchException e1) {
				// TODO Auto-generated catch block3
				LOG.info(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			} catch (NumberFormatException e) {
				LOG.info("Erreur a la saisie veuillez mettre chiffre (exemple : 12.5) ");
				prix = 0.0;
			}

		}
		return prix;
	}

	/**
	 * 
	 * @return Categorie de la pizza Vérifie que la saisie de la categorie
	 *         correspond une categorie existante.
	 * 
	 */
	public CategoriePizza verifCate() {
		System.out.println("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
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
			System.out.println("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
			cate = saisie.next();

		}

		return null;
	}

}
