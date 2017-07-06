package fr.pizzeria.console.PizzeriaAdminConsoleApp;

import java.util.ArrayList;

public class Menu {
	private String titreMenu;
	private ArrayList<OptionMenu> actions = new ArrayList<OptionMenu>();
	private ArrayList<Pizza> listP = new ArrayList<Pizza>();

	public Menu(OptionMenu actions, String titreMenu, ArrayList<Pizza> listP){
		this.titreMenu = titreMenu;
		this.actions.add(actions);
		this.listP.addAll(listP);
	}
	
	public String getTitreMenu() {
		return titreMenu;
	}

	public void setTitreMenu(String titreMenu) {
		this.titreMenu = titreMenu;
	}

	public void afficher() {
		System.out.println(titreMenu);
		for (int i = 0; i < actions.size(); i++) {
			System.out.println(actions.get(i).getCodeExe() + " " + actions.get(i).getLibelle() + "\n");
		}	
	}
	
	public void executeAction(){
		
	}
	
	public ArrayList<OptionMenu> getActions() {
		return actions;
	}

	public void addActions(OptionMenu actions) {
		this.actions.add(actions);
	}
	
	public void removeAction(OptionMenu actions){
		this.actions.remove(actions);
	}
}
