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
public class Entraineur {

	private int id;
	private String nom = "";
	private String prenom = "";
	private String login = "";
	private String pwd = "";

	/**
	 * On crée un nouvel entraineur qui n'est pas dans la base de donnée
	 */
	public Entraineur() {

	}

	public Entraineur(int id, String nom, String prenom, String login) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
	}

	/*****************************************************
	 * ** Getter / Setter ** **
	 ******************************************************/

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.prenom = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/*****************************************************
	 * ** Autres fonctions ** **
	 ******************************************************/

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		String str = "Id(" + this.id + "); Nom (" + this.nom + "); Prenom (" + this.prenom + ");";

		return str;
	}

}
