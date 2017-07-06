package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizza extends OptionMenu {
	String libelle = "3. Modifier une pizza";

	public ModifierPizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	@Override
	public boolean execute() {
		String nom, code, nouvCode;
		double prix;
		Scanner saisie = new Scanner(System.in);
		saisie.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code de la pizza a modifier : \n");
		code = saisie.next();
		if (!dao.pizzaExist(code)) {
			return false;
		}
		System.out.println("Veuillez saisir le nom de la nouvelle pizza : ");
		nom = saisie.next();
		System.out.println("Veuillez saisir le code de la nouvelle pizza : ");
		nouvCode = saisie.next();
		System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
		prix = saisie.nextDouble();
		if (dao.updatePizza(code, new Pizza(nom, nouvCode, prix))) {
			return true;
		} else {
			return false;
		}

	}

}
