package fr.pizzeria.ihm;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends OptionMenu {
	String libelle = "1. Lister les pizzas";
	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzaOptionMenu.class);

	public ListerPizzaOptionMenu(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	// Retourne la liste des pizzas
	@Override
	public boolean execute() {
		ArrayList<Pizza> pizzas = dao.findAllPizzas();
		if (pizzas.isEmpty()) {
			System.out.println("Liste vide");
			LOG.info("Liste vide");
			return false;
		}
		for (Pizza piz : pizzas) {
			LOG.info(piz.toString());
		}
		LOG.info("\n");
		return true;
	}

}
