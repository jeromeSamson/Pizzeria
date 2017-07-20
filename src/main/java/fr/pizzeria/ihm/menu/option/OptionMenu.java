package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.StockageException;

public abstract class OptionMenu {
	protected IPizzaDao dao;

	public OptionMenu(IPizzaDao dao) {
		super();
		this.dao = dao;

	}

	public abstract String getLibelle();

	public abstract boolean execute() throws StockageException;
}
