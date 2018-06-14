/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.util;



import org.metier.Card;
import org.metier.Family;
import org.metier.salon.Gamer;



/**
 * Cette classe permet de créer des objets qui communiqueront les actions d'un tour de joueur
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Message {
    
    
    
    Gamer expeditor;
    Card  requestCard;
    Gamer receiver;
    boolean receiverHas;
    Card  piocheCard;
    boolean goodPioche;
    Family finishFamily;
    Gamer nextPlayer;
    int nbCardExpeditor;
    int nbCardReceiver;

    
    
    /**
     * Crée un objet de communication
     * @param expeditor Correspond au joueur qui demande une carte
     * @param requestCard Correspond à la carte qu'il demande
     * @param receiver Correspond au joueur à qui il demande
     * @param receiverHas Correspond à true si receiver possède la carte, sinon false
     * @param piocheCard Correspond à la carte pioché par l'expéditeur (s'il a piocher une carte)
     * @param goodPioche Correspond à true s'il y a une bonne pioche, sinon false
     * @param finishFamily Correspond au nom de la famille terminée (si elle est terminée)
     * @param nextPlayer Correspond au joueur qui devra jouer après cette action
     * @param nbCardExpeditor Correspond au nombre de carte de l'expéditeur
     * @param nbCardReceiver Correspond au nombre de carte du destinataire
     */
    public Message(Gamer expeditor, Card requestCard, Gamer receiver, boolean receiverHas, Card piocheCard, boolean goodPioche, Family finishFamily, Gamer nextPlayer, int nbCardExpeditor, int nbCardReceiver) {
        this.expeditor = expeditor;
        this.requestCard = requestCard;
        this.receiver = receiver;
        this.receiverHas = receiverHas;
        this.piocheCard = piocheCard;
        this.goodPioche = goodPioche;
        this.finishFamily = finishFamily;
        this.nextPlayer = nextPlayer;
        this.nbCardExpeditor = nbCardExpeditor;
        this.nbCardReceiver = nbCardReceiver;
    }

    
    
    //Nous avons en dessous tous les GETTERS de cette classes
    public Gamer getExpeditor() {
        return expeditor;
    }

    public Card getRequestCard() {
        return requestCard;
    }

    public Gamer getReceiver() {
        return receiver;
    }

    public boolean isReceiverHas() {
        return receiverHas;
    }

    public Card getPiocheCard() {
        return piocheCard;
    }

    public boolean isGoodPioche() {
        return goodPioche;
    }

    public Family getFinishFamily() {
        return finishFamily;
    }

    public Gamer getNextPlayer() {
        return nextPlayer;
    }

    public int getNbCardExpeditor() {
        return nbCardExpeditor;
    }

    public int getNbCardReceiver() {
        return nbCardReceiver;
    }

    @Override
    public String toString() {
        return "Message{" + "expeditor=" + expeditor + ", requestCard=" + requestCard + ", receiver=" + receiver + ", receiverHas=" + receiverHas + ", piocheCard=" + piocheCard + ", goodPioche=" + goodPioche + ", finishFamily=" + finishFamily + ", nextPlayer=" + nextPlayer + ", nbCardExpeditor=" + nbCardExpeditor + ", nbCardReceiver=" + nbCardReceiver + '}';
    }
    
}
