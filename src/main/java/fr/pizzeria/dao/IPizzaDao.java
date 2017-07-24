package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.dao.exception.SavePizza;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	/**
	 * Récupère l'ensemble des pizzas
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<Pizza> findAllPizzas() throws SQLException, ClassNotFoundException;

	/**
	 * Sauve la pizza
	 * 
	 * @param pizza
	 *            à stocker
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void saveNewPizza(Pizza pizza) throws SavePizza, SQLException, ClassNotFoundException;

	void updatePizza(String codePizza, Pizza pizza) throws SQLException, ClassNotFoundException;

	void deletePizza(String codePizza) throws SQLException, ClassNotFoundException;

	boolean pizzaExist(String codePizza) throws ClassNotFoundException, SQLException;

}