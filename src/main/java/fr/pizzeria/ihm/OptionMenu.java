package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;

public abstract class OptionMenu {
	protected IPizzaDao dao;
	protected PizzaDao dao2;

	public OptionMenu(IPizzaDao dao) {
		super();
		this.dao = dao;

	}

	public abstract String getLibelle();

	public abstract boolean execute();
}
