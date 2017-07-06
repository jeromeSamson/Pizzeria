package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

public class SupprimerPizza extends OptionMenu {
	String libelle = "4. Supprimer une Pizza";

	public SupprimerPizza(IPizzaDao dao) {
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
		String nom, code;
		double prix;
		Scanner saisie = new Scanner(System.in);
		System.out.println("Veuillez saisir le code de la pizza a supprimer : \n");
		code = saisie.next();
		if (!dao.pizzaExist(code.toUpperCase())) {
			return false;
		} else {
			try {
				dao.deletePizza(code.toUpperCase());
			} catch (DeletePizzaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

	}

}
