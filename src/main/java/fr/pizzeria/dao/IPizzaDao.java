package fr.pizzeria.dao;

import java.util.ArrayList;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	ArrayList<Pizza> findAllPizzas();

	boolean saveNewPizza(Pizza pizza);

	boolean updatePizza(String codePizza, Pizza pizza);

	boolean deletePizza(String codePizza);

	boolean pizzaExist(String codePizza);

	boolean isEmpty();

}