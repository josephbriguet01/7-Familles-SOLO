/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.ihm.components;



/**
 * Cette interface permet pour une carte graphique de dialoguer avec l'écran principale
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public interface IGamerComponent {
    
    /**
     * Lorsqu'une carte est sélectionnée
     * @param gc Correspond à la carte graphique cliquée
     */
    public void gamerMouseClicked(GamerComponent gc);
    
}
