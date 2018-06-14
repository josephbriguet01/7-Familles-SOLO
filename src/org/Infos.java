/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org;



/**
 * Cette classe contient les infos de base du jeu
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Infos {
    
    public static       String               NOM_JOUEUR          = System.getProperties().getProperty("user.name");
    public static final String               SOFT_NAME           = "7 Familles";
    public static final String[]             SOFT_AUTHOR         = {"Joseph BRIGUET", "Ben YOUSSOUFA", "Fabien Tressaille", "Damien ARROUDJ"};
    public static final String               SOFT_COPYRIGHT      = "Copyright © 2018 - Tous droits réservés";
    public static final String               SOFT_LAF            = "Metal";
    public static       boolean              SOFT_DEBUG          = false;
    public static       java.awt.Image       SOFT_ICON           = java.awt.Toolkit.getDefaultToolkit().getImage(Infos.class.getResource("/org/ihm/img/icon.png"));
    public static       int                  SPEED_IA            = 1500;

}