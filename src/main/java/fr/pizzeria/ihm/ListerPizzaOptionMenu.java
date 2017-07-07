package fr.pizzeria.ihm;

import java.util.ArrayList;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends OptionMenu {
	String libelle = "1. Lister les pizzas";

	public ListerPizzaOptionMenu(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	@Override
	public boolean execute() {
		ArrayList<Pizza> pizzas = dao.findAllPizzas();
		if(pizzas.isEmpty()){
			return false;
		}
		for (int i = 0; i < pizzas.size(); i++) {
			System.out.println(pizzas.get(i).getCode() + " " + pizzas.get(i).getNom() + " " + pizzas.get(i).getPrix());
		}
		System.out.println("\n");
		return true;
	}

}
