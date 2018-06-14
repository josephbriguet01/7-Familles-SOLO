/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.metier.Game;



/**
 * Cette classe est la classe principale du jeu puisqu'il s'agit de la classe qui lance le programme.
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Main {

    
    
    private static Game game;
    
    
    
    /**
     * Méthode de démarrage du programme
     * @param args Correspond aux éventuels arguments (ici on ne les prends pas en compte)
     */
    public static void main(String[] args) {
        
        //Initialise le Look And Feel
        initialiseLAF();
        
        //Récupère l'éventuel nom de joueur dans le jeu d'un fichier texte (name.txt) à la racine du jeu
        loadNameGamer();
        
        //Lance réellement l'application
        game = new Game();
        
    }
    
    /**
     * Initialise le Look And Feel
     */
    private static void initialiseLAF(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (Infos.SOFT_LAF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Récupère l'éventuel nom de joueur dans le jeu d'un fichier texte (name.txt) à la racine du jeu
     */
    private static void loadNameGamer(){
        if(new File("name.txt").exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("name.txt")));
                String str = null;
                while(br.ready()){
                    str = br.readLine();
                }
                br.close();
                if(str != null && !str.isEmpty()){
                    Infos.NOM_JOUEUR = str;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}