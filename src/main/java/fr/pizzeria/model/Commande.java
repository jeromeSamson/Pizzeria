package fr.pizzeria.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "commande.findAllByClient", query = "Select c from Commande c where c.idClient=:id_Client")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numCommande;
	@Enumerated
	private Statut statut;
	private Timestamp date_commande;
	@ManyToMany
	@JoinTable(name = "Commande_Pizza", joinColumns = @JoinColumn(name = "Id_Commande", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Id_Pizza", referencedColumnName = "id"))
	private List<Pizza> pizzas;

	@ManyToOne
	@JoinColumn(name = "Livreur_Id")
	private Livreur idLivreur;

	@ManyToOne
	@JoinColumn(name = "Client_Id")
	private Client idClient;

	public String getNumCommande() {
		return numCommande;
	}

	public Commande() {

	}

	public Commande(String numCommande, Statut statut, Timestamp date_commande, List<Pizza> pizzas, Client idClient) {
		super();
		this.numCommande = numCommande;
		this.statut = statut;
		this.date_commande = date_commande;
		this.pizzas = pizzas;
		this.idClient = idClient;
	}

	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Timestamp getDate_commande() {
		return date_commande;
	}

	public void setDate_commande(Timestamp date_commande) {
		this.date_commande = date_commande;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Livreur getIdLivreur() {
		return idLivreur;
	}

	public void setIdLivreur(Livreur idLivreur) {
		this.idLivreur = idLivreur;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

}
