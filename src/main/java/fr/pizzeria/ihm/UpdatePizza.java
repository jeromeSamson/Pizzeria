package fr.pizzeria.ihm;

import java.awt.Label;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class UpdatePizza extends OptionMenu {
	String libelle = "3. Modifier une pizza";

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
		Scanner saisie = new Scanner(System.in);
		saisie.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code de la pizza a modifier : \n");
		code = saisie.next();
		while (!dao.pizzaExist(code.toUpperCase())) {
			System.out.println("Erreur le code saisi n'existe pas ");
			System.out.println("Veuillez saisir le code de la pizza a modifier : \n");
			code = saisie.next();
		}

		System.out.println("Veuillez saisir le nouvau nom de la pizza : ");
		nom = saisie.next();
		System.out.println("Veuillez saisir le nouveau code de la pizza : ");
		nouvCode = saisie.next();
		while(dao.pizzaExist(nouvCode)){
			if(nouvCode.toUpperCase().equals(code.toUpperCase())){
				break;
			}
			System.out.println("Erreur le code saisi existe déja");
			System.out.println("Veuillez saisir le nouveau code de la pizza : ");
			nouvCode = saisie.next();			
		}
		try {
			System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
			prix = saisie.nextDouble();
			while(prix<0){
				System.out.println("Erreur le prix doit être supérieur à 0");
				System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
				prix = saisie.nextDouble();
			}
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

}
