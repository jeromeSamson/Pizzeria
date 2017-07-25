package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.model.Client;

public class Subscribe extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(DeletePizza.class);
	String libelle = "1. S'inscrire";
	Scanner saisie = new Scanner(System.in);

	public Subscribe(ClientDaoJpa dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	@Override
	public boolean execute(EntityManagerFactory emf) throws StockageException, SQLException, ClassNotFoundException {
		LOG.info("******** Inscription ******");

		LOG.info("Veuillez saisir votre nom (quit pour quitter) : ");
		String code = saisie.next();
		if ("QUIT".equalsIgnoreCase(code)) {
			return false;
		}

		String nom = code;
		LOG.info("Veuillez saisir votre prénom (quit pour quitter) : ");
		code = saisie.next();
		if ("QUIT".equalsIgnoreCase(code)) {
			return false;
		}
		String prenom = code;

		/**
		 * Email
		 */
		LOG.info("Veuillez saisir votre email (quit pour quitter) : ");
		code = saisie.next();
		if ("QUIT".equalsIgnoreCase(code)) {
			return false;
		}
		while (daoClient.clientExist(code.toUpperCase())) {
			LOG.info("Erreur l'email saisi existe déja ");
			LOG.info("Veuillez saisir votre email (quit pour quitter) : \n");
			code = saisie.next();
			if ("QUIT".equalsIgnoreCase(code)) {
				return false;
			}

		}

		String email = code;
		LOG.info("Veuillez saisir votre mot de passe (quit pour quitter) : ");
		code = saisie.next();
		if ("QUIT".equalsIgnoreCase(code)) {
			return false;
		}
		String pwd = code;

		Client c = new Client(nom, prenom, email, pwd);
		daoClient.saveNewClient(c);
		return true;

	}
}
