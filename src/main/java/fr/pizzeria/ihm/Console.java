package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDao;

public class Console {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PizzaDao pizzaDao = new PizzaDao();
		Menu m = new Menu(pizzaDao);
		m.afficher();
	}

}
 