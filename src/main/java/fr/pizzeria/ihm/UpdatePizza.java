package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class UpdatePizza extends OptionMenu {
	String libelle = "3. Modifier une pizza";
	Scanner saisie = new Scanner(System.in);

	public UpdatePizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		return libelle;
	}

	@Override
	public boolean execute() throws UpdatePizzaException {
		String nom, code, nouvCode;
		double prix;
		String categorie;
		saisie.useLocale(Locale.US);
		if (dao.isEmpty()) {
			System.out.println("Aucune pizza dans la base de données.\n Veuillez en ajouter une. ");
			return false;
		}
		System.out.println("Veuillez saisir le code de la pizza à modifier (quit pour quitter) : \n");
		code = saisie.next();
		while (!dao.pizzaExist(code.toUpperCase())) {
			if (code.toUpperCase().equals("QUIT")) {
				return false;
			}
			System.out.println("Erreur le code saisi n'existe pas ");
			System.out.println("Veuillez saisir le code de la pizza à modifier : \n");
			code = saisie.next();
		}

		System.out.println("Veuillez saisir le nouvau nom de la pizza : ");
		nom = saisie.next();
		if (nom.toUpperCase().equals("QUIT")) {
			return false;
		}
		System.out.println("Veuillez saisir le nouveau code de la pizza : ");
		nouvCode = saisie.next();
		if (nouvCode.toUpperCase().equals("QUIT")) {
			return false;
		}
		while (dao.pizzaExist(nouvCode)) {
			if (nouvCode.toUpperCase().equals("QUIT")) {
				return false;
			}
			if (nouvCode.toUpperCase().equals(code.toUpperCase())) {
				break;
			}
			System.out.println("Erreur le code saisi existe déjà");
			System.out.println("Veuillez saisir le nouveau code de la pizza : ");
			nouvCode = saisie.next();
		}
		CategoriePizza cate;
		cate = verifCate();
		try {
			prix = getPrix();
			if (prix == 0.0) {
				return false;
			}
			if (dao.updatePizza(code.toUpperCase(),
					new Pizza(nom, nouvCode.toUpperCase(), prix,cate))) {
				System.out.println("Pizza modifier");
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			System.out.println(
					"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
		}

		return false;
	}

	public double getPrix() {
		String prixStr = null;
		double prix = 0.0;
		while (prix <= 0.0) {
			try {
				System.out.println("Veuillez saisir le nouveau prix de la pizza : ");
				prixStr = saisie.next();
				if (prixStr.toUpperCase().equals("QUIT")) {
					return 0.0;
				}
				prix = Double.parseDouble(prixStr);

			} catch (InputMismatchException e1) {
				// TODO Auto-generated catch block3
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			} catch (NumberFormatException e) {
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			}
			if (prix <= 0.0) {
				System.out.println("Erreur le prix doit être supérieur à 0");
			}
		}
		return prix;
	}
	/**
	 * 
	 * @return Categorie de la pizza
	 * Vérifie que la saisie de la categorie correspond une categorie existante.
	 * 
	 */
	public CategoriePizza verifCate() {
		System.out.println("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
		String cate = saisie.next();
		boolean sortieWhile = false;
		String[] split = CategoriePizza.listEnum().split(" ");
		while(!sortieWhile){
			for(int i =0; i<split.length;i++){
				if(split[i].toUpperCase().toString().equals(cate.toUpperCase().toString() )){
					switch (i) {
					case 0 : return CategoriePizza.VIANDE;
					
					case 1 :return CategoriePizza.POISSON;
						
					case 2 : return CategoriePizza.SANS_VIANDES;
					
					default:
						break;
					}
				}
			}
			System.out.println("Veuillez saisir la categorie de la pizza (" + CategoriePizza.listEnum() + " : ");
			cate = saisie.next();
			
		}

		return null;
	}

}
