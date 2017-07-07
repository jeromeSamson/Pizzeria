package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;

public class Menu {
	private OptionMenu[] optionsMenu;

	public Menu(IPizzaDao dao) {
		optionsMenu = new OptionMenu[4];
		optionsMenu[0] = new ListerPizzaOptionMenu(dao);
		optionsMenu[1] = new SaveNewPizza(dao);
		optionsMenu[2] = new UpdatePizza(dao);
		optionsMenu[3] = new DeletePizza(dao);
	}

	public void afficher() throws StockageException {
		System.out.println("****** Pizzeria administration *****");
		for (int i = 0; i < optionsMenu.length; i++) {
			System.out.println(optionsMenu[i].getLibelle());
		}
		System.out.println("99. Sortie");
		int numCommande = verifCommande();
		while (numCommande != 99) {
			switch (numCommande) {
			case (1):
				if (!optionsMenu[0].execute()) {
					System.out.println("La liste de pizza est vide");
				}
			break;
			case (2):
				if (optionsMenu[1].execute()) {
					System.out.println("Pizza ajouter");
				} else {
					System.out.println("La pizza n'a pas pu être ajouter");
				}

			break;
			case (3):
				if (optionsMenu[2].execute()) {
					System.out.println("Pizza modifié");
				} else {
					System.out.println("Impossible de modifier la pizza");
				}
			break;
			case (4):

				if (optionsMenu[3].execute()) {
					System.out.println("Pizza supprimé");
				} else {
					System.out.println("Impossible de supprimer la pizza");
				}
			break;
			case (99):
				break;
			default:
				System.out.println("Commande invalide");
				break;
			}
			System.out.println("****** Pizzeria administration *****");
			for (int i = 0; i < optionsMenu.length; i++) {
				System.out.println(optionsMenu[i].getLibelle());
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
