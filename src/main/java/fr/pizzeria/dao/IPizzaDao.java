package fr.pizzeria.dao;

import javax.security.sasl.SaslException;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	Pizza[] findAllPizzas();

	boolean saveNewPizza(Pizza pizza);

	boolean updatePizza(String codePizza, Pizza pizza);

	boolean deletePizza(String codePizza);

	boolean pizzaExist(String codePizza);

	boolean isEmpty();

}
