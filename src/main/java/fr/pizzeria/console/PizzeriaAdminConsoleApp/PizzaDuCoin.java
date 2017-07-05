package fr.pizzeria.console.PizzeriaAdminConsoleApp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PizzaDuCoin extends JFrame{
	JButton afficherPizza = null;
	JButton ajouterPizza = null;
	JButton mAJPizza = null;
	JButton suppPizza = null;
	JButton exit = null;
	
	public PizzaDuCoin(String nom){
		super(nom);
		setSize(370,250);
		
		JPanel panel = new JPanel();
		
		afficherPizza = new JButton("Afficher pizza");	
		ajouterPizza = new JButton("Ajouter pizza");
		mAJPizza = new JButton("Mettre a Jour");
		
		
		
		GridBagLayout placeur = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		getContentPane().setLayout(placeur);
		constraints.gridx=0;
		constraints.gridwidth=2;
		getContentPane().add(afficherPizza, constraints);
	}
	
}
