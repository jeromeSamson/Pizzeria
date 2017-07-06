package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public class PizzaDao implements IPizzaDao {
	Pizza[] tabPizza = new Pizza[6];

	public PizzaDao() {
		tabPizza[0] = new Pizza("Péperonni", "PEP", 12.5);
		tabPizza[1] = new Pizza("Margherita", "MAR", 14.0);
		tabPizza[2] = new Pizza("La Reine", "REI", 11.5);
		tabPizza[3] = new Pizza("La 4 Fromage", "FRO", 12.0);
		tabPizza[4] = new Pizza("La cannibale", "CAN", 12.5);
		tabPizza[5] = new Pizza("La savoyarde", "SAV", 13.0);
		Pizza.setNbPizza(6);
	}

	public Pizza[] findAllPizzas() {
		return tabPizza;
	}

	public boolean saveNewPizza(Pizza pizza) {
		int nbPiz = Pizza.getNbPizza();
		nbPiz++;
		Pizza[] temp = new Pizza[nbPiz];

		for (int i = 0; i < nbPiz - 1; i++) {
			temp[i] = tabPizza[i];
		}
		tabPizza = new Pizza[nbPiz];
		for (int i = 0; i < nbPiz; i++) {
			tabPizza[i] = temp[i];
		}

		tabPizza[nbPiz - 1] = pizza;
		Pizza.setNbPizza(nbPiz);
		return true;

	}

	public boolean updatePizza(String codePizza, Pizza pizza) {
		int nbPiz = Pizza.getNbPizza();
		for (int i = 0; i < nbPiz; i++) {
			if (codePizza.equals(tabPizza[i].getCode())) {
				tabPizza[i].setCode(pizza.getCode());
				tabPizza[i].setNom(pizza.getNom());
				tabPizza[i].setPrix(pizza.getPrix());
				return true;
			}
		}

		return false;
	}

	public boolean deletePizza(String codePizza) {
		int nbPizza = Pizza.getNbPizza();
		for (int i = 0; i < nbPizza; i++) {
			if (codePizza.equals(tabPizza[i].getCode())) {
				for (int j = i; j < nbPizza - 1; j++) {
					if (tabPizza[j + 1].equals(null)) {
						break;
					} else {
						tabPizza[j] = tabPizza[j + 1];
					}

				}
				nbPizza--;
				Pizza.setNbPizza(nbPizza);
				break;
			}
		}
		return true;
	}

	public boolean pizzaExist(String codePizza) {
		int nbPiz = Pizza.getNbPizza();
		for (int i = 0; i < nbPiz; i++) {
			if (codePizza.equals(tabPizza[i].getCode())) {
				return true;
			}
		}
		return false;

	}
}
