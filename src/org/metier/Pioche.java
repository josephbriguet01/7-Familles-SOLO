/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Cette classe crée un objet pioche
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Pioche {
    
    
    
    private List<Card> listCardsGame;

    
    
    /**
     * Crée un objet Pioche
     * @param listCardsGame Correspond à la liste de cartes utilisée 
     */
    public Pioche(List<Card> listCardsGame) {
        this.listCardsGame = new ArrayList<>();
        for (Card listCardsGame1 : listCardsGame) {
            this.listCardsGame.add(listCardsGame1);
        }
    }
    
    /**
     * Mélange la pioche
     */
    public void shuffle(){
        int nombreAleatoire = 5 + (int)(Math.random() * ((11 - 5) + 1));
        for(int i=0;i<nombreAleatoire;i++){
            Collections.shuffle(listCardsGame);
        }
    }
    
    /**
     * Renvoie n nombres de paquets de 6 cartes (un paquet par joueur)
     * @param nombreJoueurs Correspond au nombre de joueurs dans le jeu
     * @return Retourne n packet de 6 cartes (méthode utilisée au départ pour la distribution des cartes)
     */
    public List<List<Card>> getCards(int nombreJoueurs){
        List<List<Card>> gen = new ArrayList<>();
        for(int i=0;i<nombreJoueurs;i++){
            List<Card> cards = new ArrayList<>();
            for(int j=0;j<6;j++){
                cards.add(getCard());
            }
            gen.add(cards);
        }
        return gen;
    }
    
    /**
     * Renvoie la taille de la pioche
     * @return Retourne la taille de la pioche
     */
    public int size(){
        return listCardsGame.size();
    }
    
    /**
     * Prend la première carte de la pioche
     * @return Renvoie la carte du dessus
     */
    public Card getCard(){
        if(size()>0){
            Card c = listCardsGame.get(0);
            listCardsGame.remove(0);
            return c;
        }else{
            return null;
        }
    }
}
