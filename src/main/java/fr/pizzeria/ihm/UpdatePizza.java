package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class UpdatePizza extends OptionMenu {
	String libelle = "3. Modifier une pizza";
	Scanner saisie = new Scanner(System.in);

	public UpdatePizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	@Override
	public boolean execute() throws UpdatePizzaException {
		String nom, code, nouvCode;
		double prix;
		
		saisie.useLocale(Locale.US);
		if(dao.isEmpty()){
			System.out.println("Aucune pizza dans la base de données.\n Veuillez en ajouter une. ");
			return false;
		}
		System.out.println("Veuillez saisir le code de la pizza à modifier : \n");
		code = saisie.next();
		while (!dao.pizzaExist(code.toUpperCase())) {
			System.out.println("Erreur le code saisi n'existe pas ");
			System.out.println("Veuillez saisir le code de la pizza à modifier : \n");
			code = saisie.next();
		}

		System.out.println("Veuillez saisir le nouvau nom de la pizza : ");
		nom = saisie.next();
		System.out.println("Veuillez saisir le nouveau code de la pizza : ");
		nouvCode = saisie.next();
		while (dao.pizzaExist(nouvCode)) {
			if (nouvCode.toUpperCase().equals(code.toUpperCase())) {
				break;
			}
			System.out.println("Erreur le code saisi existe déjà");
			System.out.println("Veuillez saisir le nouveau code de la pizza : ");
			nouvCode = saisie.next();
		}
		
		
		
		try {
			prix = getPrix();
			if (dao.updatePizza(code.toUpperCase(), new Pizza(nom, nouvCode.toUpperCase(), prix))) {
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			System.out.println(
					"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
		}

		return false;
	}
	
	
	public double getPrix(){
		String prixStr = null;
		double prix = 0.0;
		while (prix<=0.0){
			try {
				System.out.println("Veuillez saisir le nouveau prix de la pizza : ");
				prixStr = saisie.next();
				prix = Double.parseDouble(prixStr);
				
			} catch (InputMismatchException e1) {
				// TODO Auto-generated catch block3
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			} catch (NumberFormatException e){
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			}
			if (prix<=0.0){
				System.out.println("Erreur le prix doit être supérieur à 0");
			}
		}
		return prix;
	}

}
