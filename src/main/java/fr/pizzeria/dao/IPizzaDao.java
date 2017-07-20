package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.dao.exception.SavePizza;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	/**
	 * Récupère l'ensemble des pizzas
	 * 
	 * @return
	 */
	List<Pizza> findAllPizzas();

	/**
	 * Sauve la pizza
	 * 
	 * @param pizza
	 *            à stocker
	 */
	void saveNewPizza(Pizza pizza) throws SavePizza;

	void updatePizza(String codePizza, Pizza pizza);

	void deletePizza(String codePizza);

	boolean pizzaExist(String codePizza);

	boolean isEmpty();

}