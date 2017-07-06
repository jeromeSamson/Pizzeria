package fr.pizzeria.console.PizzeriaAdminConsoleApp;

public abstract class OptionMenu {
	private String libelle;
	private int codeExe;

	public String getLibelle() {
		return libelle;
	}
	
	
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public boolean execute() {
		return false;
	}


	public int getCodeExe() {
		return codeExe;
	}


	public void setCodeExe(int codeExe) {
		this.codeExe = codeExe;
	}
	
}
	

