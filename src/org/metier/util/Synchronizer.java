/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.util;



import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Cette classe permet de synchronizer deux Threads car la méthode classique avec le wait() et le notify() ne convenait pas
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Synchronizer {
    
    int value = 0;
    
    
    
    /**
     * Fait attendre le thread courant
     * @param value Correspond à la valeur qui doit débloquer le thread courant
     */
    public void waiting(int value){
        while (this.value != value) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Synchronizer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Renvoie la valeur en cours de l'objet
     * @return Retourne la valeur en cours de l'objet
     */
    public synchronized int getValue() {
        return value;
    }
    
    /**
     * Modifie la valeur en cours de l'objet
     * @param value Correspond à la nouvelle valeur
     */
    public synchronized void set(int value){
        this.value = value;
    }
}
