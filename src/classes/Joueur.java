/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppe.jv.classes;

/**
 *
 * @author Julien
 */
public class Joueur {
	private int id;
	private int id_equi = 0;
	private String nom = "";
	private String prenom = "";
	private String anneeNaissance = "";
	private String email = "";
	private Boolean surclas = false;
	private int poste;
	private int dispo;

	/**
	 * On crée un nouveau joueur qui n'est pas dans la base de donnée
	 */
	public Joueur() {

	}

	/**
	 * Créer un joueur avec toutes ses informations
	 * 
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Joueur(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;

		// Dev.debug(toString());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_equi() {
		return id_equi;
	}

	public void setId_equi(int id_equi) {
		this.id_equi = id_equi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAnneeNaissance() {
		return anneeNaissance;
	}

	public void setAnneeNaissance(String anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getSurclas() {
		return surclas;
	}

	public void setSurclas(Boolean surclas) {
		this.surclas = surclas;
	}

	public int getPoste() {
		return poste;
	}

	public void setPoste(int poste) {
		this.poste = poste;
	}
	
	public int getDispo(){
		return dispo;
	}
	
	public void setDispo(int dispo){
		this.dispo=dispo;
	}

	public String toString() {
		String str = "Id(" + this.id + "), Nom(" + this.nom + "), Prenom(" + this.prenom + "), Email(" + this.email
				+ "), Surclassement(" + this.surclas + "), Poste(" + this.poste + "), Dispo(" + this.dispo + ")";

		return str;
	}

}
