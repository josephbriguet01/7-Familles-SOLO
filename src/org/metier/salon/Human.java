/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.salon;



import java.util.List;
import org.ihm.Screen;
import org.metier.Card;
import org.metier.Game;
import org.metier.util.Message;



/**
 * Cette classe permet de créer un joueur humain
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Human extends Gamer{
    
    
    
    /**
     * Crée un humain
     * @param game Correspond à l'objet Game
     * @param name Correspond au nom de l'IA
     * @param gameCards Correspond aux 42 cartes du jeu
     * @param gamerCards Correspond au cartes du joueur
     * @param screen Correspond à l'écran graphique
     */
    public Human(Game game, String name, List<Card> gameCards, List<Card> gamerCards, Screen screen) {
        super(game, name, gameCards, gamerCards, screen);
    }

    
    
    @Override
    public void play(Message m) {
        new Thread(() -> {
            screen.display(m);
            Game.syn.waiting(1);
            Game.syn.set(0);
            screen.canPlay();
        }).start();
    }
    
}