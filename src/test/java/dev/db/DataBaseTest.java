package dev.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ihm.VerificationSaisieTest;
import fr.pizzeria.dao.PizzaDaoJDBC;
import fr.pizzeria.dao.exception.SavePizza;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class DataBaseTest {
	private static final Logger LOG = LoggerFactory.getLogger(VerificationSaisieTest.class);
	private static Connection connection;
	private static Statement statement;
	private static PizzaDaoJDBC pizzaJDBC;

	@BeforeClass
	public static void intialisation() throws SQLException, ClassNotFoundException {
		pizzaJDBC = new PizzaDaoJDBC("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "", "org.h2.Driver");
		connection = pizzaJDBC.createConnection();
		Statement st = connection.createStatement();
		st.execute("Create table pizza (code varchar(3) primary key, Nom varchar(50),Prix double, Categorie int); ");
	}

	@Test
	public void testFindAllPizza() throws SQLException, ClassNotFoundException, SavePizza {
		pizzaJDBC.saveNewPizza(new Pizza("Test", "PEP", 12.5, CategoriePizza.VIANDE));
		connection = pizzaJDBC.createConnection();
		List<Pizza> result = pizzaJDBC.findAllPizzas();
		for (Pizza l : result) {
			assertThat(l.getCode()).isIn("PEP", "LEG", "REI", "FRO", "CAN", "SAV", "ORI", "CHA");
			assertThat(l.getNom()).isIn("Test", "Peperonni", "Fromage", "Orientale", "Canibale");
		}
	}

	@Test
	public void testSavePizza() throws SQLException, SavePizza, ClassNotFoundException {
		pizzaJDBC.saveNewPizza(new Pizza("Marg", "SAV", 12.5, CategoriePizza.VIANDE));
		connection = pizzaJDBC.createConnection();
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery("Select * from Pizza");
		while (result.next()) {
			assertThat(result.getString("code")).isIn("PEP", "MAR", "REI", "FRO", "CAN", "SAV", "ORI", "CHA");
		}
		result.close();

	}

	@Test
	public void testUpdate() throws SQLException, ClassNotFoundException {
		connection = pizzaJDBC.createConnection();
		pizzaJDBC.updatePizza("SAV", new Pizza("Peperonni", "SAV", 9.0, CategoriePizza.POISSON));
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery("Select * from Pizza where code like 'SAV';");
		while (result.next()) {
			assertThat(result.getString("Nom")).contains("Peperonni");
		}

	}

}
