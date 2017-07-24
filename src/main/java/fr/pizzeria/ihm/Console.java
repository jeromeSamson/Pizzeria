package fr.pizzeria.ihm;

import java.sql.SQLException;

import fr.pizzeria.dao.PizzaDaoJPA;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.ihm.menu.Menu;

public class Console {

	public static void main(String[] args) throws StockageException, ClassNotFoundException, SQLException {
		// PizzaDaoJDBC pizzaDaoJDBC = new
		// PizzaDaoJDBC("jdbc:mysql://localhost:3306/pizza", "root", "",
		// "com.mysql.jdbc.Driver");
		// PizzaDaoJDBC pizzaDaoJDBC = new PizzaDaoJDBC();
		// ArrayList<Pizza> tabPizza = new ArrayList<>();
		// tabPizza.add(new Pizza("Péperoni", "PEP", 12.5,
		// CategoriePizza.VIANDE));
		// tabPizza.add(new Pizza("Margherita", "MAR", 14.0,
		// CategoriePizza.VIANDE));
		// tabPizza.add(new Pizza("La Reine", "REI", 11.5,
		// CategoriePizza.VIANDE));
		// tabPizza.add(new Pizza("La 4 Fromage", "FRO", 12.0,
		// CategoriePizza.SANS_VIANDES));
		// tabPizza.add(new Pizza("La mer", "MER", 12.5,
		// CategoriePizza.POISSON));
		// tabPizza.add(new Pizza("La savoyarde", "SAV", 13.0,
		// CategoriePizza.VIANDE));
		// pizzaDao.initPizzaDao(tabPizza);

		PizzaDaoJPA pizzaDaoJPA = new PizzaDaoJPA();
		Menu m = new Menu(pizzaDaoJPA);
		m.afficher();
		pizzaDaoJPA.closeEMF();
	}

}
