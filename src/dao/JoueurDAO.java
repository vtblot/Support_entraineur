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
import com.ppe.jv.classes.Equipe;
import com.ppe.jv.classes.Joueur;

/**
 *
 * @author Julien
 */
public class JoueurDAO extends DAO<Joueur> {

	@Override
	public Joueur select(int id) {
		Joueur obj = new Joueur();
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT * FROM joueur WHERE id = ?";
			prepare = conn.prepareStatement(sql);// on cr√©er la requete
			prepare.setInt(1, id);// on place les variables dans la requete
			rs = prepare.executeQuery();// on execute la requete et on recupere
										// le resultat

			rs.first();
			obj.setId(id);
			obj.setId_equi(rs.getInt("id_equipe"));
			obj.setNom(rs.getString("nom"));
			obj.setPrenom(rs.getString("prenom"));
			obj.setAnneeNaissance(rs.getString("date_naissance"));
			obj.setEmail(rs.getString("mail"));
			obj.setPoste(rs.getInt("poste"));
			obj.setDispo(rs.getInt("dispo"));

			if (rs.getInt("surclassement") == 0) {
				obj.setSurclas(false);
			} else {
				obj.setSurclas(true);
			}

			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();// on ferme la base de donn√©e

		}
		return obj;
	}

	public ArrayList<Joueur> selectForTeam(Equipe equipe) {
		ArrayList<Joueur> list = new ArrayList<Joueur>();
		try {
			getConnection();

			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT *  FROM joueur WHERE id_equipe = ?";
			prepare = conn.prepareStatement(sql);// on cr√©er la requete
			prepare.setInt(1, equipe.getId());// on place les variables dans la
												// requete
			rs = prepare.executeQuery();// on execute la requete et on recupere
										// le resultat

			while (rs.next()) {
				Joueur obj = new Joueur();
				obj.setId(rs.getInt("id"));
				obj.setId_equi(rs.getInt("id_equipe"));
				obj.setNom(rs.getString("nom"));
				obj.setPrenom(rs.getString("prenom"));
				obj.setAnneeNaissance(rs.getString("date_naissance"));
				obj.setEmail(rs.getString("mail"));
				obj.setPoste(rs.getInt("poste"));
				obj.setDispo(rs.getInt("dispo"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donn√©e
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void insert(Joueur obj) {
		if (!isOk(obj)) {
			return;
		}
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "INSERT INTO joueur(id_equipe,nom,prenom,date_naissance,mail,surclassement) VALUES (?,?,?,?,?,?)";
			prepare = conn.prepareStatement(sql);// on cr√©er la requete
			prepare.setInt(1, obj.getId_equi());// on place les variables dans
												// la requete
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setString(4, obj.getAnneeNaissance());
			prepare.setString(5, obj.getEmail());
			if (obj.getSurclas()) {
				prepare.setInt(6, 1);
			} else {
				prepare.setInt(6, 0);
			}

			prepare.execute();// on execute la requete et on recupere le
								// resultat
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donn√©e
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Joueur obj) {
		if (!isOk(obj)) {
			return;
		}
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "UPDATE joueur SET id_equipe=?,nom=?,prenom=?,date_naissance=?,mail=?,surclassement=?, poste=?, dispo=? WHERE id=?";
			prepare = conn.prepareStatement(sql);// on cr√©er la requete
			prepare.setInt(1, obj.getId_equi());// on place les variables dans
												// la requete
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setString(4, obj.getAnneeNaissance());
			prepare.setString(5, obj.getEmail());
			if (obj.getSurclas()) {
				prepare.setInt(6, 1);
			} else {
				prepare.setInt(6, 0);
			}

			prepare.setInt(7, obj.getPoste());
			prepare.setInt(8, obj.getId());
			prepare.setInt(9, obj.getDispo());

			prepare.execute();// on execute la requete et on recupere le
								// resultat

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donn√©e
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Met ‡ jour la disponibilitÈ d'un joueur
	 * @param obj joueur a mettre a jour
	 */
	public void updateDispo(Joueur obj) {

		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "UPDATE joueur SET dispo=? WHERE id=?";
			prepare = conn.prepareStatement(sql);// on cr√©er la requete

			prepare.setInt(1, obj.getDispo());
			prepare.setInt(2, obj.getId());

			prepare.execute();// on execute la requete et on recupere le
								// resultat

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donn√©e
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Met ‡ jour le poste d'un joueur
	 * @param obj joueur a mettre a jour
	 */
	public void updatePoste(Joueur obj) {
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			//on met tous les joueurs de l'Èquipe avec ca poste ‡ 0
			String sql = "UPDATE joueur SET poste=0 WHERE id_equipe=? AND poste =?";
			prepare = conn.prepareStatement(sql);// on crÈer la requete
			prepare.setInt(1, obj.getId_equi());
			prepare.setInt(2, obj.getPoste());

			prepare.execute();// on execute la requete
			
			//on met ‡ jour le poste du joueur
			sql = "UPDATE joueur SET poste=? WHERE id=?";
			prepare = conn.prepareStatement(sql);// on crÈer la requete
			prepare.setInt(1, obj.getPoste());
			prepare.setInt(2, obj.getId());

			prepare.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donn√©e
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	

	@Override
	public void delete(Joueur obj) {
		try {
			getConnection();
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sql = "DELETE FROM joueur WHERE id = ?";
			prepare = conn.prepareStatement(sql);// on cr√©er la requete
			prepare.setInt(1, obj.getId());// on place les variables dans la
											// requete
			prepare.execute();// on execute la requete et on recupere le
								// resultat

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepare.close();
				closeConnection();// on ferme la base de donn√©e
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isOk(Joueur obj) {
		if (obj.getId_equi() == 0) {
			Dev.debug("/!\\ Erreur : Id_equi invalide /!\\");
			return false;
		} else if (obj.getNom().isEmpty()) {
			Dev.debug("/!\\ Erreur : Nom vide /!\\");
			return false;
		} else if (obj.getPrenom().isEmpty()) {
			Dev.debug("/!\\ Erreur : Prenom vide /!\\");
			return false;
		} else if (obj.getEmail().isEmpty()) {
			Dev.debug("/!\\ Erreur : Email vide /!\\");
			return false;
		} else {
			return true;
		}
	}
}
