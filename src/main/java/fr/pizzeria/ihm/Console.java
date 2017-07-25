package fr.pizzeria.ihm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.PizzaDaoJPA;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.ihm.menu.option.Connect;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.Subscribe;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class Console {

	public static void main(String[] args) throws StockageException, ClassNotFoundException, SQLException {
		// PizzaDaoJDBC pizzaDaoJDBC = new
		// PizzaDaoJDBC("jdbc:mysql://localhost:3306/pizza", "root", "",
		// "com.mysql.jdbc.Driver");

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

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pizzeria");
		ArrayList<Pizza> tabPizza = new ArrayList<>();
		tabPizza.add(new Pizza("Péperoni", "PEP", 12.5, CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("Margherita", "MAR", 14.0, CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La Reine", "REI", 11.5, CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La 4 Fromage", "FRO", 12.0, CategoriePizza.SANS_VIANDES));
		tabPizza.add(new Pizza("La mer", "MER", 12.5, CategoriePizza.POISSON));
		tabPizza.add(new Pizza("La savoyarde", "SAV", 13.0, CategoriePizza.VIANDE));
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Pizza pizza : tabPizza) {
			em.persist(pizza);
		}
		em.getTransaction().commit();
		em.close();

		PizzaDaoJPA pizzaDaoJPA = new PizzaDaoJPA(emf);
		HashMap<Integer, OptionMenu> options = new HashMap<>();
		options.put(1, new Subscribe(new ClientDaoJpa(emf)));
		options.put(2, new Connect(new ClientDaoJpa(emf)));
		Menu m = new Menu(options, emf);
		// Menu m = new Menu(pizzaDaoJDBC);
		m.afficher();
		// pizzaDaoJPA.closeEMF();
	}

}