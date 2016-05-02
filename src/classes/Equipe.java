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
public class Equipe {
    
    private int id ;
    private int id_entraineur = 0 ;
    private String categorie = "";

    /**
     * On crée une nouvelle equipe qui n'est pas dans la base de donnée
     */
    public Equipe(){
        
    }

    /**
     * Créer un entraineur et vas chercher ses joueurs dans la bdd
     * @param id Id de l'équipe
     * @param id_ent Id de l'entraineur
     * @param libelle Nom de l'=équipe
     */
    public Equipe(int id, int id_entraineur, String categorie) {
        this.id = id;
        this.id_entraineur = id_entraineur;
        this.categorie = categorie;
        
        Dev.debug(toString());
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getId_entraineur() {
        return id_entraineur;
    }

    public void setId_entraineur(int id_entraineur) {
        this.id_entraineur = id_entraineur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
    @Override
    public String toString()
    {
        String str =  "Id("+this.id+"), Id_ent("+this.id_entraineur+"), Categorie("+this.categorie+")";
                
        return str;
    }
    
    
}
