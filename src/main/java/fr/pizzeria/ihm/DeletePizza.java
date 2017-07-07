package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
/**
 * 
 * @author Jerome Samson
 * 
 *
 */
public class DeletePizza extends OptionMenu {
	String libelle = "4. Supprimer une Pizza";

	public DeletePizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}
	/**
	 * Demande à l'utilisateur de saisir le code de la pizza 
	 * à supprimer 
	 * Effectue une vérification 
	 * 		liste non vide
	 * 		code correct
	 * Supprime la pizza
	 * Notifie l'utilisateur de la suppression
	 * 		
	 */
	@Override
	public boolean execute(){
		String nom, code;
		double prix;
		Scanner saisie = new Scanner(System.in);
		// Si il n'y a pas de pizza alors on ne peut pas supprimé
		if(dao.isEmpty()){
			System.out.println("Aucune pizza dans la base de données.\n Veuillez en ajouter une. ");
			return false;
		}
		System.out.println("Veuillez saisir le code de la pizza a supprimer (QUIT pour sortir) : ");
		code = saisie.next();
		/*
		 * Tant que l'on ne trouve pas de code correspondant a la saisie de l'utilisateur on 
		 * continue de demander la saisie 
		 * TODO Mettre un code de sortie pour la boucle.
		 */
	
		while (!dao.pizzaExist(code.toUpperCase())) {
			if(code.toUpperCase().equals("QUIT")){
				return false;
			}
			System.out.println("Erreur le code saisi n'existe pas ");
			System.out.println("Veuillez saisir le code de la pizza a modifier (QUIT pour sortir): ");
			code = saisie.next();
			
		}
			dao.deletePizza(code.toUpperCase());
			System.out.println("Pizza supprimée");
			return true;
	}
}
