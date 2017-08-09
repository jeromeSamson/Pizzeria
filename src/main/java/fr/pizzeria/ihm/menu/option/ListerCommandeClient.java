package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.CommandeDaoJpa;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class ListerCommandeClient extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(ListerCommandeClient.class);
	String libelle = "2. Lister ses commandes";
	Scanner saisie = new Scanner(System.in);
	private Client c;

	public ListerCommandeClient(CommandeDaoJpa commandeDaoJpa) {
		super(commandeDaoJpa);
		this.c = commandeDaoJpa.getC();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	@Override
	public boolean execute(EntityManagerFactory emf) throws StockageException, SQLException, ClassNotFoundException {

		LOG.info(c.getId().toString());
		CommandeDaoJpa commandeDaoJpa = new CommandeDaoJpa(emf);
		List<Commande> listC = commandeDaoJpa.findAllCommandeByClient(c.getId());
		if (listC == null) {
			LOG.info("Aucune commande");
			return false;
		} else {
			listC.stream().forEach(System.out::println);
			return true;
		}

	}

}
