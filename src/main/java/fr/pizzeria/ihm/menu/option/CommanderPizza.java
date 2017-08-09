package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.CommandeDaoJpa;
import fr.pizzeria.dao.PizzaDaoJPA;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Statut;

public class CommanderPizza extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(CommanderPizza.class);
	String libelle = "1. Commander une pizza";
	Scanner saisie = new Scanner(System.in);
	private Client c;

	public CommanderPizza(ClientDaoJpa dao) {
		super(dao);
		this.c = dao.getClient();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	@Override
	public boolean execute(EntityManagerFactory emf) throws StockageException, SQLException, ClassNotFoundException {
		PizzaDaoJPA daoPizza = new PizzaDaoJPA(emf);
		List<Pizza> listP = new ArrayList<>();
		List<Pizza> pizzas;
		pizzas = daoPizza.findAllPizzas();
		int i = 1;

		for (Pizza pizza : pizzas) {
			LOG.info("{} {}", i, pizza.toString());
			i++;
		}
		String result = "";
		int index = -1;
		while (index != 0) {
			index = verifCommande(pizzas.size());
			if (index > 0) {
				listP.add(pizzas.get(index - 1));
			}

		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		Commande com = new Commande("a", Statut.NON_TRAITER, date, listP, c);
		CommandeDaoJpa daoCom = new CommandeDaoJpa(emf);
		daoCom.saveCommande(com);
		return true;
	}

	public static int verifCommande(int size) {
		Scanner saisie = new Scanner(System.in);
		String commande = null;
		int numPizza = 0;
		while (numPizza <= 0) {

			LOG.info("Veuillez saisir le code de la pizza à ajouter à votre commande quit pour terminer");
			commande = saisie.next();
			if ("QUIT".equalsIgnoreCase(commande)) {
				return 0;
			}
			numPizza = Integer.parseInt(commande);
			if (numPizza > size) {
				LOG.info("Erreur le code doit être inférieur à {}", size);
				numPizza = 0;
			} else if (numPizza <= 0) {
				LOG.info("Erreur le code doit être supérieur à 0");
				numPizza = 0;
			}

		}
		return numPizza;
	}

}
