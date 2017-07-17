package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.StockageException;

public class Console {

	public static void main(String[] args) throws StockageException {
		PizzaDao pizzaDao = new PizzaDao();
		Menu m = new Menu(pizzaDao);
		m.afficher();
	}

}
