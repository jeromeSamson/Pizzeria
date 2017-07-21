package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends OptionMenu {
	String libelle = "1. Lister les pizzas";
	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzaOptionMenu.class);

	public ListerPizzaOptionMenu(IPizzaDao dao) {
		super(dao);

	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	// Retourne la liste des pizzas
	@Override
	public boolean execute() throws ClassNotFoundException {
		List<Pizza> pizzas;

		try {
			pizzas = dao.findAllPizzas();
			if (pizzas.isEmpty()) {
				LOG.info("Liste vide");
				return false;
			}
			pizzas.stream().forEach(p -> LOG.info(p.toString()));
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
