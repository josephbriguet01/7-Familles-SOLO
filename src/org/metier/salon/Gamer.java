/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.salon;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.ihm.Screen;
import org.metier.Card;
import org.metier.Family;
import org.metier.Game;
import org.metier.util.Message;



/**
 * Crée des objets Gamer
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public abstract class Gamer {
    
    
    
    public Game          game;
    private final String name;
    public List<Card>    gameCards;
    public List<Card>    gamerCards;
    public final Screen  screen;
    
    public List<Family>         myFamilies;
    public static List<Family>  finishFamilies;

    
    
    /**
     * Crée un joueur (IA/Humain)
     * @param game Correspond à l'objet Game
     * @param name Correspond au nom de l'IA
     * @param gameCards Correspond aux 42 cartes du jeu
     * @param gamerCards Correspond au cartes du joueur
     * @param screen Correspond à l'écran graphique
     */
    @SuppressWarnings("static-access")
    public Gamer(Game game, String name, List<Card> gameCards, List<Card> gamerCards, Screen screen) {
        this.game       = game;
        this.screen     = screen;
        this.name       = name;
        this.gameCards  = gameCards;
        this.gamerCards = gamerCards;
        this.myFamilies = new ArrayList<>();
        this.finishFamilies = new ArrayList<>();
    }
    
    
    
    /**
     * Cette méthode abstraite permet de dire comment un joueur peut jouer
     * @param m Correspond au message du tour précédant
     */
    public abstract void play(Message m);
    
    /**
     * Envoie la requête d'un joueur expéditeur à un joueur receveur
     * @param receiver Correspond au joueur destinataire
     * @param c Correspond à la carte demandé
     */
    public void GTG_SendRequest(Gamer receiver, Card c){
        Card gc = receiver.getCard(c);
        
        //Le joueur n'avais pas la carte
        if(gc == null){
            
            //Le joueur pioche
            gc = game.getCardPioche();
            
            //Ajoute la carte et vérifie s'il y a famille
            Family f = addAndVerifyFamily(gc);
            
            
            
            //La famille est toujours terminée
            if(f != null){
                finishFamilies.add(f);
                myFamilies.add(f);
            }
            
            
            
            if(gc != null){
                if(gc.equals(c)){
                    //Le joueur doit rejouer
                    play(new Message(this, c, receiver, false, gc, true, f, this, gamerCards.size(), receiver.gamerCards.size()));
                }else{
                    //Sinon c'est à l'autre joueur de jouer
                    receiver.play(new Message(this, c, receiver, false, gc, false, f, receiver, gamerCards.size(), receiver.gamerCards.size()));
                }
            }else{
                //Sinon c'est à l'autre joueur de jouer
                receiver.play(new Message(this, c, receiver, false, gc, false, f, receiver, gamerCards.size(), receiver.gamerCards.size()));
            }
        }
        //Le joueur avais la carte
        else{
            //Ajoute la carte et vérifie s'il y a famille
            Family f = addAndVerifyFamily(gc);
            
            
            
            //La famille est toujours terminée
            if(f != null){
                finishFamilies.add(f);
                myFamilies.add(f);
            }
                
            
            
            //Le joueur doit rejouer
            play(new Message(this, c, receiver, true, null, false, f, this, gamerCards.size(), receiver.gamerCards.size()));
        }
    }
    
    /**
     * Renvoie la carte du jeu
     * @param c Correspond à la carte du jeu que l'on veut récupérer
     * @return Retourne la carte
     */
    public Card getCard(Card c){
        if(gamerCards.contains(c)){
            if(gamerCards.contains(c)) gamerCards.remove(c);
            return c;
        }else{
            return null;
        }
    }
    
    /**
     * Ajoute une carte dans le jeu et renvoie s'il y a une famille
     * @param c Correspond à la carte à ajouter dans le jeu
     * @return Retourne l'éventuelle famille terminée ou null
     */
    public Family addAndVerifyFamily(Card c){
        synchronized(gamerCards){
            
            if(!gamerCards.contains(c) && c != null) gamerCards.add(c);

            for(int i=1;i<=7;i++){
                int numberCards = nbCardByFamily(i);
                Family f = getFamily(i);
                if(numberCards == 6 && !myFamilies.contains(f)) return f;
            }
            return null;
        }
    }
    
    /**
     * Renvoie le nombre de carte dans une famille
     * @param f Correspond à la famille dont ont cherche à connaitre le nombre de carte
     * @return Retourne le nombre de carte de cette famille
     */
    private int nbCardByFamily(int idFamily){
        synchronized(gamerCards){
            int cpt = 0;
            for(int i=0;i<gamerCards.size();i++){
                if(gamerCards.get(i).getFamily().getId() == idFamily) cpt++;
            }
            return cpt;
        }
    }
    
    /**
     * Renvoie une famille à partir de son ID
     * @param idFamily Correspond à l'id de la famille
     * @return Retourne la famille
     */
    private Family getFamily(int idFamily){
        return game.families.get(idFamily-1);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gamer other = (Gamer) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return name;
    }
    
}