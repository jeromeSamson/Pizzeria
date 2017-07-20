package fr.pizzeria.ihm;

import java.util.ArrayList;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class Console {

	public static void main(String[] args) throws StockageException {
		PizzaDao pizzaDao = new PizzaDao();
		ArrayList<Pizza> tabPizza = new ArrayList<>();
		tabPizza.add(new Pizza("Péperoni", "PEP", 12.5, CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("Margherita", "MAR", 14.0, CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La Reine", "REI", 11.5, CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La 4 Fromage", "FRO", 12.0, CategoriePizza.SANS_VIANDES));
		tabPizza.add(new Pizza("La mer", "MER", 12.5, CategoriePizza.POISSON));
		tabPizza.add(new Pizza("La savoyarde", "SAV", 13.0, CategoriePizza.VIANDE));
		pizzaDao.initPizzaDao(tabPizza);
		Menu m = new Menu(pizzaDao);
		m.afficher();
	}

}
