package fr.pizzeria.console.PizzeriaAdminConsoleApp;

import javax.swing.JFrame;

public class PizzeriaConsole extends JFrame {
	public static void main(String[] args) {

		// ArrayList<Pizza> listPizza = new ArrayList<Pizza>();
		// int commande = 0;
		// listPizza.add(new Pizza("Péperoni","PEP",12.50));
		// listPizza.add(new Pizza("4 fromages","FRO",11.50));
		// listPizza.add(new Pizza("Reine","REI",10.50));
		//
		// Scanner scan = new Scanner(System.in);
		// while (commande != 99) {
		// // Affichage du menu
		// System.out.println("***** Pizzeria Administration ***** \n" + "1.
		// Lister les pizzas \n"
		// + "2. Ajouter une nouvelle pizza\n" + "3. Mettre à jour une pizza\n"
		// + "4. Supprimer une pizza\n"
		// + "99. Sortir \n");
		// // Récupération de l'action souhaiter par l'utilisateur
		// commande = scan.nextInt();
		// // Action en fonction du choix utilisateur
		// switch (commande) {
		// case (1):
		// // Boucle parcourant de la liste
		// for (int i = 0; i < listPizza.size(); i++) {
		// System.out.println("\n" + listPizza.get(i).toString());
		// }
		// break;
		// case (2):
		// /*
		// * Saisie des informations concernant la nouvelle pizza
		// */
		// Pizza p = new Pizza();
		// System.out.println("Veuillez saisir le code");
		// p.setCode(scan.next());
		// System.out.println("Veuillez saisir le nom (sans espace)");
		// p.setNom(scan.next());
		// System.out.println("Veuillez saisir le prix");
		// p.setPrix(scan.nextInt());
		// listPizza.add(p);
		//
		// break;
		// case (3):
		// // Boucle parcourant l'ensemble du tableau de 0 a nbPizza-1
		// for (int i = 0; i < listPizza.size(); i++) {
		// System.out.println("\n" + listPizza.get(i).toString());
		// }
		// /*
		// * L'utilisateur saisi le code de la pizza à modifier On
		// * recherche l'indice de la pizza dans le tableau On demande la
		// * saisie de nouvelles informations sur la pizza. On modifie la
		// * pizza et on quitte la boucle.
		// */
		// System.out.println("Veuillez saisir le code de la pizza à modifier :
		// \n");
		// String code = scan.next();
		// for (int i = 0; i < listPizza.size(); i++) {
		// if (code.equals(listPizza.get(i).getCode())) {
		// System.out.println("\n" + listPizza.get(i).toString());
		// System.out.println("Veuillez saisir le code :");
		// listPizza.get(i).setCode(scan.next());
		// System.out.println("Veuillez saisir le nom :");
		// listPizza.get(i).setNom(scan.next());
		// System.out.println("Veuillez saisir le prix :");
		// listPizza.get(i).setPrix(scan.nextDouble());
		// break;
		// }
		// }
		// System.out.println("Modification effectué\n");
		//
		// break;
		// case (4):
		// // Boucle parcourant l'ensemble du tableau de 0 à nbPizza-1
		// for (int i = 0; i < listPizza.size(); i++) {
		// System.out.println("\n" + listPizza.get(i).toString());
		// }
		// /*
		// * On parcour l'ensemble du tableau jusqu'à trouver la pizza à
		// * supprimé. Puis on d�cale les pizza situ�es a droite de la
		// * pizza a supprim�. On quitte la boucle de décalage a droite On
		// * décrémente le nombre de pizza et on quitte la boucle de
		// * recherche;
		// */
		//
		// System.out.println("Veuillez saisir le code de la pizza a modifier :
		// \n");
		// code = scan.next();
		// for (int i = 0; i < listPizza.size(); i++) {
		// if (code.equals(listPizza.get(i).getCode())) {
		// listPizza.remove(i);
		// }
		// }
		// System.out.println("Suppression effectu�\n");
		// break;
		// case (99):
		// break;
		// default:
		// break;
		// }
		//
		// }
		// System.out.println("Au revoir");
		// }

	}
}