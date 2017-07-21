package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * 
 * @author Jerome Samson
 * 
 *
 */
public class DeletePizza extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(DeletePizza.class);
	String libelle = "4. Supprimer une Pizza";

	public DeletePizza(IPizzaDao dao) {
		super(dao);

	}

	@Override
	public String getLibelle() {

		return libelle;
	}

	/**
	 * Demande à l'utilisateur de saisir le code de la pizza � supprimer
	 * Effectue une vérification liste non vide code correct Supprime la pizza
	 * Notifie l'utilisateur de la suppression
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	@Override
	public boolean execute() throws SQLException, ClassNotFoundException {
		Scanner saisie = new Scanner(System.in);
		// Si il n'y a pas de pizza alors on ne peut pas supprimé
		List<Pizza> pizzas = dao.findAllPizzas();
		if (pizzas.isEmpty()) {
			LOG.info("Liste vide");
			return false;
		}

		LOG.info("Veuillez saisir le code de la pizza a supprimer (QUIT pour sortir) : ");
		String code = saisie.next();
		/*
		 * Tant que l'on ne trouve pas de code correspondant a la saisie de
		 * l'utilisateur on continue de demander la saisie
		 */

		while (!dao.pizzaExist(code.toUpperCase())) {
			if ("QUIT".equalsIgnoreCase(code)) {
				return false;
			}
			LOG.info("Erreur le code saisi n'existe pas ");
			LOG.info("Veuillez saisir le code de la pizza a supprimer (QUIT pour sortir): ");
			code = saisie.next();

		}
		dao.deletePizza(code.toUpperCase());
		LOG.info("Pizza supprimée");
		return true;
	}
}
