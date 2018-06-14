/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier;



/**
 * Cette classe crée des objets famille
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Family {
    
    private int id;
    private String name;

    
    
    /**
     * Crée une famille
     * @param id Correspond à l'id de la famille
     * @param name Correspond à son nom 
     */
    public Family(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
    
    /**
     * Renvoie l'id unique de la famille
     * @return Retourne l'id unique de la famille
     */
    public int getId() {
        return id;
    }

    /**
     * Renvoie le nom de la famille
     * @return Retourne le nom de la famille
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de la famille
     * @param name Correspond au nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Family other = (Family) obj;
        return this.id == other.id;
    }
    
}
