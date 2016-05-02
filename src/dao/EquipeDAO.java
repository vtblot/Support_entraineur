/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppe.jv.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ppe.jv.classes.Dev;
import com.ppe.jv.classes.Entraineur;
import com.ppe.jv.classes.Equipe;

/**
 *
 * @author Julien
 */
public class EquipeDAO extends DAO<Equipe> {

	@Override
	public Equipe select(int id) {
		Equipe equipe = new Equipe();
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT *  FROM equipe WHERE id = ?";
			prepare = conn.prepareStatement(sql);// on créer la requete
			prepare.setInt(1, id);// on place les variables dans la requete
			rs = prepare.executeQuery();// on execute la requete et on recupere
										// le resultat

			rs.first();
			equipe.setId(id);
			equipe.setCategorie(rs.getString("categorie"));
			equipe.setId_entraineur(rs.getInt("id_entraineur"));

			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donnée
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return equipe;
	}

	/**
	 * Recupère les équipes de l'entraineur dans la bdd
	 * 
	 * @param obj
	 *            Entraineur
	 */
	public ArrayList<Equipe> selectForTrainer(Entraineur obj) {
		ArrayList<Equipe> list = new ArrayList<Equipe>();
		try {
			getConnection();

			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT *  FROM equipe WHERE id_entraineur = ?";
			prepare = conn.prepareStatement(sql);// on créer la requete
			prepare.setInt(1, obj.getId());// on place les variables dans la
											// requete
			rs = prepare.executeQuery();// on execute la requete et on recupere
										// le resultat

			while (rs.next()) {
				list.add(new Equipe(rs.getInt("id"), rs.getInt("id_entraineur"), rs.getString("categorie")));
			}

			// obj.setEquipes(list);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donnée
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public void insert(Equipe obj) {
		if (!isOk(obj)) {
			return;
		}
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "INSERT INTO equipe(id_entraineur,categorie) VALUES (?,?)";
			prepare = conn.prepareStatement(sql);// on créer la requete
			prepare.setInt(1, obj.getId_entraineur());// on place les variables
														// dans la requete
			prepare.setString(2, obj.getCategorie());
			prepare.execute();// on execute la requete et on recupere le
								// resultat
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donnée
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Equipe obj) {
		if (!isOk(obj)) {
			return;
		}
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "UPDATE equipe SET id_entraineur=?,categorie=? WHERE id=?";
			prepare = conn.prepareStatement(sql);// on créer la requete
			prepare.setInt(1, obj.getId_entraineur());// on place les variables
														// dans la requete
			prepare.setString(2, obj.getCategorie());
			prepare.setInt(3, obj.getId());
			prepare.execute();// on execute la requete et on recupere le
								// resultat
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donnée
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Equipe obj) {
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "DELETE FROM equipe WHERE id = ?";
			prepare = conn.prepareStatement(sql);// on créer la requete
			prepare.setInt(1, obj.getId());// on place les variables dans la
											// requete
			prepare.execute();// on execute la requete et on recupere le
								// resultat

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donnée
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isOk(Equipe obj) {
		if (obj.getId_entraineur() == 0) {
			Dev.debug("/!\\ Erreur : Id_ent invalide /!\\");
			return false;
		} else if (obj.getCategorie().isEmpty()) {
			Dev.debug("/!\\ Erreur : Libelle vide /!\\");
			return false;
		} else {
			return true;
		}
	}

}
