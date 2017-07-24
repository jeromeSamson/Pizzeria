package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.pizzeria.model.Pizza;

public class PizzaDao implements IPizzaDao {
	List<Pizza> tabPizza = new ArrayList<>();

	public void initPizzaDao(List<Pizza> list) {
		// Initialisation de la liste de pizza
		for (Pizza l : list) {
			saveNewPizza(l);
		}
	}

	/**
	 * Retourne la liste des pizzas
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		return new ArrayList<>(tabPizza);
	}

	/**
	 * ajoute une nouvelle pizza � la liste
	 */
	@Override
	public void saveNewPizza(Pizza pizza) {
		tabPizza.add(pizza);
	}

	/**
	 * Met à jour une pizza s�lectionn� dans la liste des pizzas
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				tabPizza.set(i, pizza);
			}
		}

	}

	/**
	 * Supprime une pizza de la liste de pizza
	 */
	@Override
	public void deletePizza(String codePizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				tabPizza.remove(i);
			}
		}
	}

	/**
	 * Vérifie si une pizza existe dans la liste des pizzas
	 */
	@Override
	public boolean pizzaExist(String codePizza) {
		return tabPizza.stream().anyMatch(p -> p.getCode().equalsIgnoreCase(codePizza));
	}

	@Override
	public Pizza pizzaById(String codePizza) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub*

		Optional<Pizza> pizza = tabPizza.stream().findFirst();
		return pizza.get();
	}

	/**
	 * Indique si la liste des pizzas est vide
	 */

}
