package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class Menu {
	HashMap<Integer, OptionMenu> optionsMenu = new HashMap<>();
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);

	public Menu(IPizzaDao dao) {
		optionsMenu.put(1, new ListerPizzaOptionMenu(dao));
		optionsMenu.put(2, new SaveNewPizza(dao));
		optionsMenu.put(3, new UpdatePizza(dao));
		optionsMenu.put(4, new DeletePizza(dao));
	}

	/**
	 * @author Jerome Samson
	 * @throws StockageException
	 *             Afficher le menu pour l'interaction de l'utilisateur
	 * 
	 */
	public void afficher() throws StockageException {
		LOG.info("****** Pizzeria administration *****");
		for (int i = 1; i <= optionsMenu.size(); i++) {
			LOG.info(optionsMenu.get(i).getLibelle());
		}
		LOG.info("99. Sortie");
		/**
		 * On verifie la saisie de l'utilisateur tant que la saisie est
		 * incorrect on continue d'afficher le menu
		 */
		int numCommande = verifCommande();
		while (numCommande != 99) {
			// On execute la demande de l'utilisateur
			optionsMenu.get(numCommande).execute();
			// On affiche le menu de nouveau
			LOG.info("****** Pizzeria administration *****");
			for (int i = 1; i <= optionsMenu.size(); i++) {
				LOG.info(optionsMenu.get(i).getLibelle());
			}
			LOG.info("99. Sortie");
			numCommande = verifCommande();

		}
		LOG.info("Au revoir");

	}

	public int verifCommande() {
		Scanner commande = new Scanner(System.in);
		int numCommande = 0;
		String numCommandeString = null;
		/**
		 * tant que la saisie est incorrect on continue d'afficher le menu et on
		 * indique les erreurs.
		 */
		while (numCommande <= 0) {
			try {
				numCommandeString = commande.next();
				numCommande = Integer.parseInt(numCommandeString);
				if (numCommande < 0) {
					numCommande = numCommande * -1;
				}

			} catch (InputMismatchException e1) {
				LOG.debug(e1.getMessage());
				LOG.info("Erreur a la saisie veuillez saisir un chiffre ");
				numCommande = 0;

			} catch (NumberFormatException e) {
				LOG.debug(e.getMessage());
				LOG.info("Erreur a la saisie veuillez saisir un chiffre ");
				numCommande = 0;
			}

		}
		return numCommande;
	}
}
