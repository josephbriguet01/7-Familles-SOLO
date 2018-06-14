/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier;



import java.util.Objects;



/**
 * Cette classe permet de créer des cartes
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Card {
    
    
    
    private final Family family;
    private final int    id;
    private final String name;
    private final String pathImg;

    
    
    /**
     * Crée une carte
     * @param family Correspond à la famille de la carte
     * @param id Correspond à l'id de la carte
     * @param name Correspond au nom de la carte
     * @param pathImg Correspond au chemin de l'image de la carte dans le projet
     */
    public Card(Family family, int id, String name, String pathImg) {
        this.family = family;
        this.id = id;
        this.name = name;
        this.pathImg = pathImg;
    }

    
    
    /**
     * Renvoie la famille de la carte
     * @return Retourne la famille de la carte
     */
    public Family getFamily() {
        return family;
    }

    /**
     * Renvoie l'id de la carte
     * @return Retourne l'id de la carte
     */
    public int getId() {
        return id;
    }

    /**
     * Renvoie le nom de la carte
     * @return Retourne le nom de la carte
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie le chemin de l'image de la carte
     * @return Retourne le chemin de l'image de la carte
     */
    public String getPathImg() {
        return pathImg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.family);
        hash = 41 * hash + this.id;
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
        final Card other = (Card) obj;
        if (!Objects.equals(this.family, other.family)) {
            return false;
        }
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return name+" ("+family+")";
    }
    
}
