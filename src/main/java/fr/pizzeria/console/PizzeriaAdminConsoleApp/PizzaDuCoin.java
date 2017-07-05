package fr.pizzeria.console.PizzeriaAdminConsoleApp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalTreeUI;

public class PizzaDuCoin extends JFrame {
	JButton afficherPizza = null;
	JButton ajouterPizza = null;
	JButton mAJPizza = null;
	JButton suppPizza = null;
	JButton exit = null;

	JTextField fieldNomPizza = null;
	JLabel labelNomPizza = null;
	JTextField fieldCodePizza = null;
	JLabel labelCodePizza = null;
	JTextField fieldPrixPizza = null;
	JLabel labelPrixPizza = null;

	public PizzaDuCoin(String nom) {
		super(nom);
		setSize(380, 270);

		JPanel panel = new JPanel();

		afficherPizza = new JButton("Afficher pizza");
		ajouterPizza = new JButton("Ajouter pizza");
		mAJPizza = new JButton("Mettre a Jour");

		fieldNomPizza = new JTextField();
		fieldNomPizza.setPreferredSize(new Dimension(100, 5));
		fieldCodePizza = new JTextField();
		fieldCodePizza.setPreferredSize(new Dimension(100, 5));
		fieldPrixPizza = new JTextField();
		fieldPrixPizza.setPreferredSize(new Dimension(100, 5));

		labelNomPizza = new JLabel("Nom : ");
		labelCodePizza = new JLabel("Code : ");
		labelPrixPizza = new JLabel("Prix : ");

		GridBagLayout placeur = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		getContentPane().setLayout(placeur);
		/*
		 * Zone gauche bouton
		 */
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		constraints.ipady = 1;
		placeur.setConstraints(afficherPizza, constraints);
		getContentPane().add(afficherPizza);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(ajouterPizza, constraints);
		getContentPane().add(ajouterPizza);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(mAJPizza, constraints);
		getContentPane().add(mAJPizza);
		/*
		 * zone milieu libelle JTextField avec JLabel
		 */
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(labelNomPizza, constraints);
		getContentPane().add(labelNomPizza);

		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(labelCodePizza, constraints);
		getContentPane().add(labelCodePizza);

		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(labelPrixPizza, constraints);
		getContentPane().add(labelPrixPizza);

		/*
		 * zone droite Jtextfield pour l'edition de pizza
		 */
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(fieldNomPizza, constraints);
		getContentPane().add(fieldNomPizza);
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(fieldCodePizza, constraints);
		getContentPane().add(fieldCodePizza);
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipadx = 2;
		placeur.setConstraints(fieldPrixPizza, constraints);
		getContentPane().add(fieldPrixPizza);
		
		ajouterPizza.setEnabled(false);
		mAJPizza.setEnabled(false);

	}
}
