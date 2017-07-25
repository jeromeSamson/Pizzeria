package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.SavePizza;
import fr.pizzeria.model.Commande;

public class CommandeDaoJpa implements ICommandeDao {
	private static final Logger LOG = LoggerFactory.getLogger(CommandeDaoJpa.class);
	private static EntityManagerFactory emf;
	private EntityManager em;

	public CommandeDaoJpa(EntityManagerFactory emf) throws ClassNotFoundException {
		this.emf = emf;
	}

	@Override
	public void saveCommande(Commande commande) throws SavePizza, SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(commande);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Commande> findAllCommandeByClient(Integer c) {
		em = emf.createEntityManager();
		List<Commande> listC = em.createNamedQuery("commande.findAllByClient", Commande.class)
				.setParameter("id_Client", c).getResultList();
		em.close();
		return listC;
	}

}
