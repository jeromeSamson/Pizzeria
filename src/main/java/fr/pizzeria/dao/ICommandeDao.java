package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.dao.exception.SavePizza;
import fr.pizzeria.model.Commande;

public interface ICommandeDao {

	/**
	 * Sauve la commande
	 * 
	 * @param pizza
	 *            à stocker
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void saveCommande(Commande commande) throws SavePizza, SQLException, ClassNotFoundException;

	List<Commande> findAllCommandeByClient(Integer c);

}
