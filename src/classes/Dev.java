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
public class Dev {

    public static boolean dev = true ;
    
    /**
     * Affiche un message dans la console
     * @param str Message à afficher
     */
    public static void debug(String str)
    {
        if(dev)
        {
            System.out.println(str);
        }
    }
    
    /**
     * Affiche un nombre dans la console
     * @param str Nombre à afficher
     */
    public static void debug(int str)
    {
        if(dev)
        {
            System.out.println(str);
        }
    }
}
