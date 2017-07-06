package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class NouvellePizzaOptionMenu extends OptionMenu {
	String libelle = "2. Ajouter une pizza";

	public NouvellePizzaOptionMenu(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	@Override
	public boolean execute(){
		String nom;
		String code;
		double prix;
		Scanner saisie = new Scanner(System.in);
		saisie.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code de la nouvelle pizza : ");
		code = saisie.next();
		if (dao.pizzaExist(code.toUpperCase()))
			return false;
		System.out.println("Veuillez saisir le nom de la nouvelle pizza : ");
		nom = saisie.next();
		if (dao.pizzaExist(code)) {
			return false;
		}
		System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
		prix = saisie.nextDouble();
		
		if (dao.saveNewPizza(new Pizza(nom, code.toUpperCase(), prix))) {
			return true;
		} else {
			return false;
		}
	}

}
