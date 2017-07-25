package fr.pizzeria.ihm.menu.option;

import static fr.pizzeria.ihm.utils.VerificationSaisie.verifCate;
import static fr.pizzeria.ihm.utils.VerificationSaisie.verifSaisiePrix;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.PizzaUpdate;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class UpdatePizza extends OptionMenu {
	String libelle = "3. Modifier une pizza";
	Scanner saisie = new Scanner(System.in);
	private static final Logger LOG = LoggerFactory.getLogger(UpdatePizza.class);

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
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public boolean execute(EntityManagerFactory emf) throws PizzaUpdate, SQLException, ClassNotFoundException {

		double prix;
		saisie.useLocale(Locale.US);
		List<Pizza> pizzas = daoPizza.findAllPizzas();
		if (pizzas.isEmpty()) {
			LOG.info("Aucune pizza dans la base de données.\n Veuillez en ajouter une. ");
			return false;
		}

		LOG.info("Veuillez saisir le code de la pizza à modifier (quit pour quitter) : ");

		String code = saisie.next();
		if ("QUIT".equalsIgnoreCase(code)) {
			return false;
		}
		while (!daoPizza.pizzaExist(code.toUpperCase())) {
			LOG.info("Erreur le code saisi n'existe pas ");
			LOG.info("Veuillez saisir le code de la pizza à modifier (quit pour quitter) : ");
			code = saisie.next();
			if ("QUIT".equalsIgnoreCase(code)) {
				return false;
			}
		}

		LOG.info("Veuillez saisir le nouvau nom de la pizza : ");
		String nom = saisie.next();
		if ("QUIT".equalsIgnoreCase(nom)) {
			return false;
		}
		CategoriePizza cate;
		cate = verifCate();
		if (cate == null)
			return false;
		try {
			prix = verifSaisiePrix();
			if (Math.abs(prix - 0.0D) <= 0) {
				return false;
			}

			daoPizza.updatePizza(code.toUpperCase(), new Pizza(nom, code.toUpperCase(), prix, cate));
			LOG.info("Pizza modifier");
			return true;

		} catch (InputMismatchException e1) {
			LOG.info(
					"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ",
					e1);
		}

		return false;
	}

}
