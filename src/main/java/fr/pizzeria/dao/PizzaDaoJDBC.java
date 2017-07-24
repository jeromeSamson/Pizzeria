package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBC implements IPizzaDao {
	private static final String SAVE_PIZZA = "Insert into Pizza Values (?,?,?,?)";
	private static final String UPDATE_PIZZA = "Update Pizza Set Nom = ?, Prix = ?, Categorie = ? where code like ?";
	private static final String DELETE_PIZZA = "DELETE FROM Pizza where code like ?";
	private static final String FIND_PIZZA = "Select * from Pizza where code like ?";

	ArrayList<Pizza> tabPizza;
	private String url;
	private String user;
	private String pwd;
	private String driver;
	Connection connection;

	public PizzaDaoJDBC(String url, String user, String pwd, String driver)
			throws ClassNotFoundException, SQLException {
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.driver = driver;
		Class.forName(driver);

	}

	public PizzaDaoJDBC() throws ClassNotFoundException {
		ResourceBundle bundle = ResourceBundle.getBundle("fr.pizzeria.domaine.properties.prod_application");
		this.url = bundle.getString("sgbd.url");
		this.user = bundle.getString("sgbd.user");
		this.pwd = bundle.getString("sgbd.pwd");
	}

	public Connection createConnection() throws ClassNotFoundException, SQLException {

		connection = DriverManager.getConnection(url, user, pwd);
		return connection;
	}

	@Override
	public List<Pizza> findAllPizzas() {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = createConnection();
			statement = connection.createStatement();
			result = statement.executeQuery("Select * from Pizza");
			tabPizza = new ArrayList<>();
			if (result.wasNull()) {
				tabPizza = null;
			} else {
				while (result.next()) {
					String code = result.getString("code");
					String nom = result.getString("Nom");
					double prix = result.getDouble("Prix");
					int cat = result.getInt("Categorie");
					Pizza p = new Pizza(nom, code, prix, CategoriePizza.values()[cat]);
					tabPizza.add(p);
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				result.close();
				connection.close();
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tabPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		Connection connection = null;
		PreparedStatement savePizza = null;
		try {
			connection = createConnection();
			savePizza = connection.prepareStatement(SAVE_PIZZA);
			savePizza.setString(1, pizza.getCode());
			savePizza.setString(2, pizza.getNom());
			savePizza.setDouble(3, pizza.getPrix());
			savePizza.setInt(4, pizza.getCategorie().ordinal());
			savePizza.execute();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				savePizza.close();
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		Connection connection = null;
		PreparedStatement updatePizza = null;
		try {
			connection = createConnection();
			updatePizza = connection.prepareStatement(UPDATE_PIZZA);
			updatePizza.setString(1, pizza.getNom());
			updatePizza.setDouble(2, pizza.getPrix());
			updatePizza.setInt(3, pizza.getCategorie().ordinal());
			updatePizza.setString(4, pizza.getCode());
			updatePizza.execute();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				updatePizza.close();
				connection.close();
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		Connection connection = null;
		PreparedStatement deletePizza = null;
		try {
			connection = createConnection();
			deletePizza = connection.prepareStatement(DELETE_PIZZA);
			deletePizza.setString(1, codePizza);
			deletePizza.execute();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				deletePizza.close();
				connection.close();
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean pizzaExist(String codePizza) {
		Connection connection;
		ResultSet result = null;
		PreparedStatement findPizza = null;
		try {
			connection = createConnection();
			findPizza = connection.prepareStatement(FIND_PIZZA);
			findPizza.setString(1, codePizza);
			result = findPizza.executeQuery();
			boolean exist = result.first();
			return exist;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				result.close();
				findPizza.close();
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
