package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

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

	@Override
	public boolean execute(){
		String nom, code;
		double prix;
		Scanner saisie = new Scanner(System.in);
		System.out.println("Veuillez saisir le code de la pizza a supprimer : \n");
		code = saisie.next();
		while (!dao.pizzaExist(code.toUpperCase())) {
			System.out.println("Erreur le code saisi n'existe pas ");
			System.out.println("Veuillez saisir le code de la pizza a modifier : \n");
			code = saisie.next();
		}
			dao.deletePizza(code.toUpperCase());
			return true;
	}
}
