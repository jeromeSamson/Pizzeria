package fr.pizzeria.dao;

import java.util.ArrayList;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDao implements IPizzaDao {
	ArrayList<Pizza> tabPizza = new ArrayList<>();

	public PizzaDao() {
		// Initialisation de la liste de pizza
		tabPizza.add(new Pizza("Péperonni", "PEP", 12.5,CategoriePizza.VIANDE));
	 	tabPizza.add(new Pizza("Margherita", "MAR", 14.0,CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La Reine", "REI", 11.5,CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La 4 Fromage", "FRO", 12.0,CategoriePizza.SANS_VIANDES));
		tabPizza.add(new Pizza("La mer", "MER", 12.5,CategoriePizza.POISSON));
		tabPizza.add(new Pizza("La savoyarde", "SAV", 13.0,CategoriePizza.VIANDE));
	}
	/**
	 * Retourne la liste des pizzas
	 */
	public ArrayList<Pizza> findAllPizzas() {
		return tabPizza;
	}
	/**
	 * ajoute une nouvelle pizza à la liste
	 */
	public boolean saveNewPizza(Pizza pizza) {
		tabPizza.add(pizza);
		return true;

	}
	/**
	 * Met à jour une pizza sélectionné dans la liste des pizzas
	 */
	public boolean updatePizza(String codePizza, Pizza pizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				tabPizza.set(i, pizza);
				return true;
			}
		}

		return false;
	}
	/**
	 * Supprime une pizza de la liste de pizza
	 */
	public boolean deletePizza(String codePizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				tabPizza.remove(i);
				return true;
			}
		}

		return true;
	}

	/**
	 * Vérifie si une pizza existe dans la liste 
	 * des pizzas
	 */
	public boolean pizzaExist(String codePizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Indique si la liste des pizzas est vide
	 */
	public boolean isEmpty(){
		return tabPizza.isEmpty();
	}
}
