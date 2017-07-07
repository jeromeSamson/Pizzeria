package fr.pizzeria.dao;

import java.util.ArrayList;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDao implements IPizzaDao {
	ArrayList<Pizza> tabPizza = new ArrayList<>();

	public PizzaDao() {
		tabPizza.add(new Pizza("Péperonni", "PEP", 12.5,CategoriePizza.VIANDE));
	 	tabPizza.add(new Pizza("Margherita", "MAR", 14.0,CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La Reine", "REI", 11.5,CategoriePizza.VIANDE));
		tabPizza.add(new Pizza("La 4 Fromage", "FRO", 12.0,CategoriePizza.SANS_VIANDES));
		tabPizza.add(new Pizza("La mer", "MER", 12.5,CategoriePizza.POISSON));
		tabPizza.add(new Pizza("La savoyarde", "SAV", 13.0,CategoriePizza.VIANDE));
	}

	public ArrayList<Pizza> findAllPizzas() {
		return tabPizza;
	}

	public boolean saveNewPizza(Pizza pizza) {
		tabPizza.add(pizza);
		return true;

	}

	public boolean updatePizza(String codePizza, Pizza pizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				tabPizza.set(i, pizza);
				return true;
			}
		}

		return false;
	}

	public boolean deletePizza(String codePizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				tabPizza.remove(i);
				return true;
			}
		}

		return true;
	}

	public boolean pizzaExist(String codePizza) {
		for (int i = 0; i < tabPizza.size(); i++) {
			if (codePizza.equals(tabPizza.get(i).getCode())) {
				return true;
			}
		}
		return false;
	}
	public boolean isEmpty(){
		return tabPizza.isEmpty();
	}
}
