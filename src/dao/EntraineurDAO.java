/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppe.jv.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ppe.jv.classes.Dev;
import com.ppe.jv.classes.Entraineur;

/**
 *
 * @author Julien
 */
public class EntraineurDAO extends DAO<Entraineur>{

    @Override
    public Entraineur select(int id) {
        
        Entraineur ent= new Entraineur();        
        try{            
            getConnection();
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String sql = "SELECT *  FROM entraineur WHERE Id = ?";
            prepare = conn.prepareStatement(sql);//on créer la requete
            prepare.setInt(1,id);//on place les variables dans la requete
            rs = prepare.executeQuery();//on execute la requete  et on recupere le resultat

            rs.first();
            ent.setId(id);
            ent.setNom(rs.getString("Nom"));
            ent.setPrenom(rs.getString("Prenom"));
            ent.setLogin(rs.getString("Login")); 
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                prepare.close();
                closeConnection();//on ferme la base de donnée
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
        
        return ent;
    }
public Entraineur auth(String email, String pwd){
	 try{ 
		 getConnection();
		 conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    
		 String sql = "SELECT * FROM entraineur WHERE login = ? AND motdepasse= ?";
		 prepare = conn.prepareStatement(sql);//on cr�er la requete
		 prepare.setString(1,email);//on place les variables dans la requete
		 prepare.setString(2,pwd);
		 rs = prepare.executeQuery();//on execute la requete  et on recupere le resultat

		 if(rs.first()){
			
			 return new Entraineur(rs.getInt("id"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Login"));
		 }
			 } catch (SQLException e) {
		 e.printStackTrace();
	 } finally {
		 try {
        prepare.close();
        closeConnection();//on ferme la base de donn�e
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	 }
	return null;
}
    @Override
    public void insert(Entraineur obj) {
        if(!isOk(obj))
        {
            return;
        }
        try{            
            getConnection();
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String sql = "INSERT INTO entraineur(Nom, Prenom) VALUES (?,?)";
            prepare = conn.prepareStatement(sql);//on créer la requete
            prepare.setString(1,obj.getNom());//on place les variables dans la requete
            prepare.setString(2,obj.getPrenom());
            prepare.execute();//on execute la requete  et on recupere le resultat
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                prepare.close();
                closeConnection();//on ferme la base de donnée
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Entraineur obj) {
        if(!isOk(obj))
        {
            return;
        }
        try{            
            getConnection();
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String sql = "UPDATE entraineur SET Nom=?,Prenom=? WHERE id=?";
            prepare = conn.prepareStatement(sql);//on créer la requete
            prepare.setString(1,obj.getNom());//on place les variables dans la requete
            prepare.setString(2,obj.getPrenom());
            prepare.setInt(3,obj.getId());
            prepare.execute();//on execute la requete  et on recupere le resultat
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                prepare.close();
                closeConnection();//on ferme la base de donnée
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Entraineur obj) {
        try{            
            getConnection();
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String sql = "DELETE FROM entraineur WHERE id = ?";
            prepare = conn.prepareStatement(sql);//on créer la requete
            prepare.setInt(1,obj.getId());//on place les variables dans la requete
            prepare.execute();//on execute la requete  et on recupere le resultat
            
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                prepare.close();
                closeConnection();//on ferme la base de donnée
            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean isOk(Entraineur obj)
    {
        if(obj.getNom().isEmpty())
        {
            Dev.debug("/!\\ Erreur : Nom vide /!\\");
            return false;
        }
        else if(obj.getPrenom().isEmpty())
        {
            Dev.debug("/!\\ Erreur : Prenom vide /!\\");
            return false;
        }
        else
        {
            return true;
        }
    }
}
