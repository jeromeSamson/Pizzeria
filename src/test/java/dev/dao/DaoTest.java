package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class DaoTest {
	PizzaDao pizza = new PizzaDao();

	@Test
	public void pizzaDaoTestFindAll() {
		List<Pizza> listPizza = pizza.findAllPizzas();
		List<String> listCode = listPizza.stream().map(t -> t.getCode()).collect(Collectors.toList());
		String[] listCodePizza = { "PEP", "SAV", "MAR", "REI", "FRO", "MER" };
		int count = 0;
		for (String code : listCode) {
			for (String list : listCodePizza) {
				if (list.equalsIgnoreCase(code)) {
					count++;
				}
			}
		}
		assertThat(count).isEqualByComparingTo(6);
	}

	@Test
	public void pizzaDaoTestSaveNewPizza() {
		pizza.saveNewPizza(new Pizza("Test", "TET", 15, CategoriePizza.VIANDE));
		List<Pizza> listPizza = pizza.findAllPizzas();
		assertThat("TET").contains(listPizza.get(listPizza.size() - 1).getCode());
	}

	@Test
	public void pizzaDaoTestUpdatePizza() {
		pizza.updatePizza("PEP", new Pizza("TestUpdate", "TETUP", 15, CategoriePizza.VIANDE));
		List<Pizza> listPizza = pizza.findAllPizzas();
		List<String> listCode = listPizza.stream().map(t -> t.getCode()).collect(Collectors.toList());
		int count = 0;
		for (String code : listCode) {
			if ("TETUP".equalsIgnoreCase(code)) {
				count++;
			}
		}
		assertThat(count).isEqualByComparingTo(1);
	}

	@Test
	public void pizzaDaoTestDeletePizza() {

		List<Pizza> listPizza = pizza.findAllPizzas();
		assertThat(listPizza.size()).isEqualByComparingTo(6);
		pizza.deletePizza("PEP");
		assertThat(listPizza.size()).isEqualByComparingTo(5);

	}

	@Test
	public void pizzaDaoTestExist() {
		assert (pizza.pizzaExist("PEP"));
		assertFalse(pizza.pizzaExist("TET"));
	}

}
