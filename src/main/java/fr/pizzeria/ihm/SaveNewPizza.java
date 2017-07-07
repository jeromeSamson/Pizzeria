package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class SaveNewPizza extends OptionMenu {
	String libelle = "2. Ajouter une pizza";
	Scanner saisie = new Scanner(System.in);
	public SaveNewPizza(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}

	@Override
	public boolean execute() {
		String nom;
		String code;
		double prix;
		saisie.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code de la nouvelle pizza : ");
		code = saisie.next();
		while (dao.pizzaExist(code.toUpperCase())) {
			System.out.println("Erreur le code saisi existe d�ja ");
			System.out.println("Veuillez saisir le code de la nouvelle pizza: \n");
			code = saisie.next();
		}
		System.out.println("Veuillez saisir le nom de la nouvelle pizza : ");
		nom = saisie.next();

		try {
			prix = getPrix();
			
			if (dao.saveNewPizza(new Pizza(nom, code.toUpperCase(), prix))) {
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			System.out.println(
					"Erreur � la saisie veuillez mettre un point entre la partie enti�re et la partie d�cimal (exemple : 12.5) ");

		}
		return false;

	}
	
	public double getPrix(){
		String prixStr = null;
		double prix = 0.0;
		while (prix<=0.0){
			try {
				System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
				prixStr = saisie.next();
				prix = Double.parseDouble(prixStr);
				if (prix<=0.0){
					System.out.println("Erreur le prix doit �tre sup�rieur � 0");
				}
				
			} catch (InputMismatchException e1) {
				// TODO Auto-generated catch block3
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie enti�re et la partie d�cimal (exemple : 12.5) ");
				prix = 0.0;
			} catch (NumberFormatException e){
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie enti�re et la partie d�cimal (exemple : 12.5) ");
				prix = 0.0;
			}
			
		}
		return prix;
	}

}
