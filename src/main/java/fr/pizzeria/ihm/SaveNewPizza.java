package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class SaveNewPizza extends OptionMenu {
	String libelle = "2. Ajouter une pizza";

	public SaveNewPizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	@Override
	public boolean execute() {
		String nom;
		String code;
		double prix;
		Scanner saisie = new Scanner(System.in);
		saisie.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code de la nouvelle pizza : ");
		code = saisie.next();
		while (dao.pizzaExist(code.toUpperCase())) {
			System.out.println("Erreur le code saisi existe déja ");
			System.out.println("Veuillez saisir le code de la nouvelle pizza : \n");
			code = saisie.next();
		}
		System.out.println("Veuillez saisir le nom de la nouvelle pizza : ");
		nom = saisie.next();
		try {
			System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
			prix = saisie.nextDouble();
			while (prix < 0 ) {
				System.out.println("Erreur le prix doit être supérieur à 0");
				System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
				prix = saisie.nextDouble();
			}
			if (dao.saveNewPizza(new Pizza(nom, code.toUpperCase(), prix))) {
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			System.out.println(
					"Erreur à la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");

		} 
		return false;

	}

}
