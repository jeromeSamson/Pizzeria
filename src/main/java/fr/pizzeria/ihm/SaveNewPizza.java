package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
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
	/**
	 * Méthode pour enregistré une nouvelle pizza
	 * Verifie si :
	 * 				- le code saisie n'existe pas déja
	 * 				- la categorie est bien saisie
	 * 				- le prix est bien saisie
	 * Appel la methode saveNewPizza de la DAOS
	 * 
	 */
	@Override
	public boolean execute() {
		String nom;
		String code;
		double prix;
		saisie.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code de la nouvelle pizza : ");
		code = saisie.next();
		while (dao.pizzaExist(code.toUpperCase())) {
			System.out.println("Erreur le code saisi existe déja ");
			System.out.println("Veuillez saisir le code de la nouvelle pizza: \n");
			code = saisie.next();
			
		}
		System.out.println("Veuillez saisir le nom de la nouvelle pizza : ");
		nom = saisie.next();
		CategoriePizza cate;
		cate = verifCate();
		try {
			prix = getPrix();
			
			if (dao.saveNewPizza(new Pizza(nom, code.toUpperCase(), prix, cate))) {
				System.out.println("Pizza enregistrée");
				return true;
			} else {
				return false;
			}
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			System.out.println(
					"Erreur à la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");

		}
		return false;

	}
	/**
	 * Vérifiaction de la saisie du prix (si il n'y a pas de virgule a la place du .
	 * Si il n'y a pas de lettre dans la valeur
	 * @return
	 */
	public double getPrix(){
		String prixStr = null;
		double prix = 0.0;
		while (prix<=0.0){
			try {
				System.out.println("Veuillez saisir le prix de la nouvelle pizza : ");
				prixStr = saisie.next();
				prix = Double.parseDouble(prixStr);
				if (prix<=0.0){
					System.out.println("Erreur le prix doit être supérieur à 0");
				}
				
			} catch (InputMismatchException e1) {
				// TODO Auto-generated catch block3
				System.out.println(
						"Erreur a la saisie veuillez mettre un point entre la partie entière et la partie décimal (exemple : 12.5) ");
				prix = 0.0;
			} catch (NumberFormatException e){
				System.out.println(
						"Erreur a la saisie veuillez mettre chiffre (exemple : 12.5) ");
				prix = 0.0;
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
