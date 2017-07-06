package fr.pizzeria.console.PizzeriaAdminConsoleApp;

import java.util.ArrayList;

public class ListerPizzaOptionMenu extends OptionMenu {
	
	public ListerPizzaOptionMenu(String libelle, int codeExe){
		super.setLibelle(libelle);
		super.setCodeExe(codeExe);
	}

	public boolean execute(ArrayList<Pizza> listPizza) {
		// Boucle parcourant de la liste
		for (int i = 0; i < listPizza.size(); i++) {
			System.out.println("\n" + listPizza.get(i).toString());
		}		return false;
	}
}
