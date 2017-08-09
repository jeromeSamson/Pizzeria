package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import fr.pizzeria.model.Client;

public class ClientDaoJpa {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Client client;

	public ClientDaoJpa(EntityManagerFactory emf, Client c) throws ClassNotFoundException {
		this.emf = emf;
		this.client = c;
	}

	public ClientDaoJpa(EntityManagerFactory emf) throws ClassNotFoundException {
		this.emf = emf;
	}

	public List<Client> findAllClient() {
		em = emf.createEntityManager();
		List<Client> listC = em.createNamedQuery("client.findAll", Client.class).getResultList();
		em.close();
		return listC;
	}

	public void saveNewClient(Client c) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}

	public void updateClient(String email, Client client) throws SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Client c = em.createNamedQuery("client.findByCode", Client.class).setParameter("email", client)
				.getSingleResult();
		c.setCommandes(client.getCommandes());
		c.setNom(client.getNom());
		c.setPrenom(client.getPrenom());
		c.setPwd(client.getPwd());
		em.merge(c);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteClient(String email) throws SQLException, ClassNotFoundException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Client c = em.createNamedQuery("client.findByEmail", Client.class).setParameter("email", email)
				.getSingleResult();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

	public Client clientByMail(String email) throws ClassNotFoundException, SQLException {
		em = emf.createEntityManager();
		try {
			Client c = em.createNamedQuery("client.findByEmail", Client.class).setParameter("email", email)
					.getSingleResult();
			em.close();
			return c;

		} catch (NoResultException e) {
			return null;
		}

	}

	public boolean clientExist(String email) throws ClassNotFoundException, SQLException {
		em = emf.createEntityManager();
		try {
			em.createNamedQuery("client.findByEmail", Client.class).setParameter("email", email).getSingleResult();
			em.close();
			return true;
		} catch (NoResultException e) {
			return false;
		}

	}

	public boolean pwdCorrect(String email, String pwd) throws ClassNotFoundException, SQLException {
		Client c = clientByMail(email);
		if (c.getPwd().equals(pwd)) {
			return true;
		} else {
			return false;
		}

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
