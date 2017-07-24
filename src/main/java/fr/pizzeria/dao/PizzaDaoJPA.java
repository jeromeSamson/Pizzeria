package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import fr.pizzeria.dao.exception.SavePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPA implements IPizzaDao {
	private static EntityManagerFactory emf;
	private EntityManager em;

	public PizzaDaoJPA() throws ClassNotFoundException {
		this.emf = Persistence.createEntityManagerFactory("Pizzeria");
	}

	@Override
	public List<Pizza> findAllPizzas() throws SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		return em.createNamedQuery("pizza.findAll", Pizza.class).getResultList();

	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizza, SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Pizza p = em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza)
				.getSingleResult();
		p.setNom(pizza.getNom());
		p.setCategorie(pizza.getCategorie());
		p.setPrix(pizza.getPrix());
		em.merge(p);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deletePizza(String codePizza) throws SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza p = em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza)
				.getSingleResult();
		em.remove(p);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public Pizza pizzaById(String codePizza) throws ClassNotFoundException, SQLException {
		em = emf.createEntityManager();
		try {
			return em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza)
					.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public boolean pizzaExist(String codePizza) throws ClassNotFoundException, SQLException {
		em = emf.createEntityManager();
		try {
			em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza).getSingleResult();
			em.close();
			return true;
		} catch (NoResultException e) {
			return false;
		}

	}

	public void closeEMF() {
		this.emf.close();
	}

}
