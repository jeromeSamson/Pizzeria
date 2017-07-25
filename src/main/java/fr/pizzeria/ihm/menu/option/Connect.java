package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.CommandeDaoJpa;
import fr.pizzeria.dao.PizzaDaoJPA;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.model.Client;

public class Connect extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(Connect.class);
	String libelle = "2. Se connecter";
	Scanner saisie = new Scanner(System.in);
	public static Client clientCo;

	public Connect(ClientDaoJpa dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	@Override
	public boolean execute(EntityManagerFactory emf) throws StockageException, SQLException, ClassNotFoundException {
		LOG.info("Veuillez saisir votre email (quit pour quitter) : ");
		String code = saisie.next();
		if ("QUIT".equalsIgnoreCase(code)) {
			return false;
		}
		while (!daoClient.clientExist(code.toUpperCase())) {
			LOG.info("Erreur le client saisi n'existe pas ");
			LOG.info("Veuillez saisir votre email (quit pour quitter) : \n");
			code = saisie.next();
			if ("QUIT".equalsIgnoreCase(code)) {
				return false;
			}
		}

		String email = code;

		LOG.info("Veuillez saisir votre mot de passe (quit pour quitter) : ");
		code = saisie.next();
		String pwd = code;

		while (!daoClient.pwdCorrect(email, pwd)) {
			LOG.info("Erreur le client saisi n'existe pas ");
			LOG.info("Veuillez saisir votre email (quit pour quitter) : \n");
			code = saisie.next();
			if ("QUIT".equalsIgnoreCase(code)) {
				return false;
			}
			pwd = code;
		}
		clientCo = daoClient.clientByMail(email);

		PizzaDaoJPA pizzaDaoJPA = new PizzaDaoJPA(emf);
		HashMap<Integer, OptionMenu> options = new HashMap<>();
		options.put(1, new CommanderPizza(new ClientDaoJpa(emf)));
		options.put(2, new ListerCommandeClient(new CommandeDaoJpa(emf)));
		Menu m = new Menu(options, emf);
		m.afficher();

		return true;
	}
}
