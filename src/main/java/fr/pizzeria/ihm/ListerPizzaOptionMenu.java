package fr.pizzeria.ihm;

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
		Pizza[] pizzas = dao.findAllPizzas();
		for (int i = 0; i < Pizza.getNbPizza(); i++) {
			System.out.println(pizzas[i].getCode() + " " + pizzas[i].getNom() + " " + pizzas[i].getPrix());
		}
		System.out.println("\n");
		return true;
	}

}
