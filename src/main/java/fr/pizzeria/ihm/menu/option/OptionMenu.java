package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.CommandeDaoJpa;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.StockageException;

public abstract class OptionMenu {
	protected IPizzaDao daoPizza;
	protected ClientDaoJpa daoClient;
	protected CommandeDaoJpa daoCommande;

	public OptionMenu(IPizzaDao dao) {
		super();
		this.daoPizza = dao;

	}

	public OptionMenu(ClientDaoJpa dao) {
		super();
		this.daoClient = dao;

	}

	public OptionMenu(CommandeDaoJpa dao) {
		super();
		this.daoCommande = dao;

	}

	public abstract String getLibelle();

	public abstract boolean execute(EntityManagerFactory emf)
			throws StockageException, SQLException, ClassNotFoundException;
}
