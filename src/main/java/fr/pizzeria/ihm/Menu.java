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

	public void afficher() throws StockageException {
		System.out.println("****** Pizzeria administration *****");
		for (int i = 1; i <= optionsMenu.size(); i++) {
			System.out.println(optionsMenu.get(i).getLibelle());
		}
		System.out.println("99. Sortie");
		int numCommande = verifCommande();
		while (numCommande != 99) {
			optionsMenu.get(numCommande).execute();
			System.out.println("****** Pizzeria administration *****");
			for (int i = 1; i <= optionsMenu.size(); i++) {
				System.out.println(optionsMenu.get(i).getLibelle());
			}
			System.out.println("99. Sortie");
			numCommande = verifCommande();
			
		}
//			switch (numCommande) {
//			case (1):
//				if (!optionsMenu[0].execute()) {
//					System.out.println("La liste de pizza est vide");
//				}
//			break;
//			case (2):
//				if (optionsMenu[1].execute()) {
//					System.out.println("Pizza ajouter");
//				} else {
//					System.out.println("La pizza n'a pas pu être ajouter");
//				}
//
//			break;
//			case (3):
//				if (optionsMenu[2].execute()) {
//					System.out.println("Pizza modifié");
//				} else {
//					System.out.println("Impossible de modifier la pizza");
//				}
//			break;
//			case (4):
//
//				if (optionsMenu[3].execute()) {
//					System.out.println("Pizza supprimé");
//				}
//			break;
//			case (99):
//				break;
//			default:
//				System.out.println("Commande invalide");
//				break;
//			}
//			System.out.println("****** Pizzeria administration *****");
//			for (int i = 0; i < optionsMenu.length; i++) {
//				System.out.println(optionsMenu[i].getLibelle());
//			}
//			System.out.println("99. Sortie");
//			numCommande = verifCommande();
//		}
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
