package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class Menu {
	HashMap<Integer, OptionMenu> optionsMenu = new HashMap<>();

	public Menu(IPizzaDao dao) {
		optionsMenu.put(1, new ListerPizzaOptionMenu(dao));
		optionsMenu.put(2, new SaveNewPizza(dao));
		optionsMenu.put(3, new UpdatePizza(dao));
		optionsMenu.put(4, new DeletePizza(dao));
	}
	/**
	 * @author Jerome Samson
	 * @throws StockageException
	 * Afficher le menu pour l'interaction de l'utilisateur
	 * 
	 */
	public void afficher() throws StockageException {
		System.out.println("****** Pizzeria administration *****");
		for (int i = 1; i <= optionsMenu.size(); i++) {
			System.out.println(optionsMenu.get(i).getLibelle());
		}
		System.out.println("99. Sortie");
		/**
		 *  On verifie la saisie de l'utilisateur tant que la saisie est incorrect
		 *  on continue d'afficher le menu
		 */
		int numCommande = verifCommande();
		while (numCommande != 99) {
			//On execute la demande de l'utilisateur
			optionsMenu.get(numCommande).execute();
			//On affiche le menu de nouveau
			System.out.println("****** Pizzeria administration *****");
			for (int i = 1; i <= optionsMenu.size(); i++) {
				System.out.println(optionsMenu.get(i).getLibelle());
			}
			System.out.println("99. Sortie");
			numCommande = verifCommande();
			
		}
		System.out.println("Au revoir");

	}

	public int verifCommande() {
		Scanner commande = new Scanner(System.in);
		int numCommande = 0;
		String numCommandeString = null;
		/**
		 * tant que la saisie est incorrect on continue 
		 * d'afficher le menu et on indique les erreurs.
		 */
		while (numCommande <= 0) {
			try {
				numCommandeString = commande.next();
				numCommande = Integer.parseInt(numCommandeString);
				if(numCommande<0){
					numCommande = numCommande*-1;
				}

			} catch (InputMismatchException e1) {
				// TODO Auto-generated catch block3
				System.out.println("Erreur a la saisie veuillez saisir un chiffre ");
				numCommande = 0;

			}catch (NumberFormatException e){
				System.out.println(
						"Erreur a la saisie veuillez saisir un chiffre ");
				numCommande = 0;
			}

		}
		return numCommande;
	}
}
