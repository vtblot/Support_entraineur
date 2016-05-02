/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppe.jv.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ppe.jv.classes.Dev;

/**
 *
 * @author Julien
 * @param <T>
 */
public abstract class DAO<T> {

	/*****************************************************
	 * ** Variables pour la relation avec la db ** **
	 ******************************************************/
	protected java.sql.Connection conn;
	protected PreparedStatement prepare;
	protected Statement state;
	protected ResultSet rs;
	protected ResultSetMetaData resultMeta;

	/**
	 * Ouvre une connection
	 */
	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (conn == null) {
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supportentraineur", "root", "");
				} catch (SQLException ex) {
					Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ferme la connection
	 */
	public void closeConnection() {
		conn = null;
	}

	/**
	 * Retourne un object en fonction de son id
	 * 
	 * @param id
	 *            Id de l'objet
	 * @return L'objet
	 */
	public abstract T select(int id);

	/**
	 * Insert l'objet dans la base de données
	 * 
	 * @param obj
	 *            Objet à inserer
	 */
	public abstract void insert(T obj);

	/**
	 * Met à jour l'objet dans la base de données
	 * 
	 * @param obj
	 *            Objet à mettre à jour
	 */
	public abstract void update(T obj);

	/**
	 * Supprime l'objet de la base de données
	 * 
	 * @param obj
	 *            Objet à supprimer
	 */
	public abstract void delete(T obj);

	/**
	 * Vérifie l'objet (variable obligatoire remplies)
	 * 
	 * @param obj
	 *            Objet à vérifier
	 * @return
	 */
	public abstract boolean isOk(T obj);
}
