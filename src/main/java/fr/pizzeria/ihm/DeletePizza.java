package fr.pizzeria.ihm;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;

/**
 * 
 * @author Jerome Samson
 * 
 *
 */
public class DeletePizza extends OptionMenu {
	private static final Logger LOG = LoggerFactory.getLogger(DeletePizza.class);
	String libelle = "4. Supprimer une Pizza";

	public DeletePizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	/**
	 * Demande � l'utilisateur de saisir le code de la pizza � supprimer
	 * Effectue une v�rification liste non vide code correct Supprime la pizza
	 * Notifie l'utilisateur de la suppression
	 * 
	 */
	@Override
	public boolean execute() {
		String nom, code;
		double prix;
		Scanner saisie = new Scanner(System.in);
		// Si il n'y a pas de pizza alors on ne peut pas supprim�
		if (dao.isEmpty()) {
			LOG.info("Aucune pizza dans la base de donn�es.\n Veuillez en ajouter une.");
			return false;
		}

		LOG.info("Veuillez saisir le code de la pizza a supprimer (QUIT pour sortir) : ");
		code = saisie.next();
		/*
		 * Tant que l'on ne trouve pas de code correspondant a la saisie de
		 * l'utilisateur on continue de demander la saisie TODO Mettre un code
		 * de sortie pour la boucle.
		 */

		while (!dao.pizzaExist(code.toUpperCase())) {
			if (code.toUpperCase().equals("QUIT")) {
				return false;
			}
			LOG.info("Erreur le code saisi n'existe pas ");
			;
			System.out.println("Veuillez saisir le code de la pizza a modifier (QUIT pour sortir): ");
			code = saisie.next();

		}
		dao.deletePizza(code.toUpperCase());
		LOG.info("Pizza supprim�e");
		return true;
	}
}
