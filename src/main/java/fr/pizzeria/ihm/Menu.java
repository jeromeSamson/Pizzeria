package fr.pizzeria.ihm;

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

	public void afficher() throws StockageException{
		System.out.println("****** Pizzeria administration *****");
		for (int i = 0; i < optionsMenu.length; i++) {
			System.out.println(optionsMenu[i].getLibelle());
		}
		System.out.println("99. Sortie");
		Scanner commande = new Scanner(System.in);
		int numCommande = commande.nextInt();
		while (numCommande != 99) {
			switch (numCommande) {
			case (1):
				optionsMenu[0].execute();
				break;
			case (2):
				if (optionsMenu[1].execute()) {
					System.out.println("Pizza ajouter");
				} else {
					System.out.println("La pizza n'a pas pu �tre ajouter");
				}

				break;
			case (3):
				if (optionsMenu[2].execute()) {
					System.out.println("Pizza modifi�");
				} else {
					System.out.println("Impossible de modifier la pizza");
				}
				break;
			case (4):

				if (optionsMenu[3].execute()) {
					System.out.println("Pizza supprim�");
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
			numCommande = commande.nextInt();
		}
		System.out.println("Au revoir");

	}
}
