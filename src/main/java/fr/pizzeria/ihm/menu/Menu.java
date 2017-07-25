package fr.pizzeria.ihm.menu;

import static fr.pizzeria.ihm.utils.VerificationSaisie.verifCommande;

import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.ihm.menu.option.DeletePizza;
import fr.pizzeria.ihm.menu.option.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SaveNewPizza;
import fr.pizzeria.ihm.menu.option.UpdatePizza;

public class Menu {
	HashMap<Integer, OptionMenu> optionsMenu = new HashMap<>();
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	private EntityManagerFactory emf;

	public Menu(IPizzaDao dao) {
		optionsMenu.put(1, new ListerPizzaOptionMenu(dao));
		optionsMenu.put(2, new SaveNewPizza(dao));
		optionsMenu.put(3, new UpdatePizza(dao));
		optionsMenu.put(4, new DeletePizza(dao));
	}

	public Menu(HashMap<Integer, OptionMenu> options, EntityManagerFactory emf) {
		optionsMenu = new HashMap<>();
		optionsMenu = options;
		this.emf = emf;
	}

	/**
	 * @author Jerome Samson
	 * @throws StockageException
	 *             Afficher le menu pour l'interaction de l'utilisateur
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public void afficher() throws StockageException, SQLException, ClassNotFoundException {
		LOG.info("****** Pizzeria administration *****");
		for (int i = 1; i <= optionsMenu.size(); i++) {
			LOG.info(optionsMenu.get(i).getLibelle());
		}
		LOG.info("99. Sortie");
		/**
		 * On verifie la saisie de l'utilisateur tant que la saisie est
		 * incorrect on continue d'afficher le menu
		 */
		int numCommande = verifCommande(optionsMenu.size());
		while (numCommande != 99) {
			// On execute la demande de l'utilisateur
			optionsMenu.get(numCommande).execute(emf);
			// On affiche le menu de nouveau
			LOG.info("****** Pizzeria administration *****");
			for (int i = 1; i <= optionsMenu.size(); i++) {
				LOG.info(optionsMenu.get(i).getLibelle());
			}
			LOG.info("99. Sortie");
			numCommande = verifCommande(optionsMenu.size());

		}
		LOG.info("Au revoir");
		emf.close();

	}

}