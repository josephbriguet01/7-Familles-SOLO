/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.util;



import java.util.ArrayList;
import java.util.List;
import org.metier.Card;
import org.metier.Family;



/**
 * Cette classe permet de déterminer quelles cartes doivent être affiché sur la grille de jeu
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Matrix {
    
    
    
    public static int HAS_CARD      = 1;
    public static int CAN_HAVE_CARD = 2;
    public static int HASNOT_CARD   = 3;
    
    
    
    private final int[][] matrix;
    private List<Card> cards;

    
    
    /**
     * Crée une matrice dans laquelle aucune carte n'est visible
     */
    public Matrix() {
        this.matrix = new int[7][6];
        
        for(int i=0;i<7;i++){
            for(int j=0;j<6;j++){
                this.matrix[i][j] = HASNOT_CARD;
            }
        }
        
        this.cards = new ArrayList<>();
    }
    
    
    
    /**
     * Ajoute une carte dans la matrice
     * @param c Correspond à une carte à ajouter
     */
    public synchronized void add(Card c){
        this.cards.add(c);
        calculMatrix();
    }
    
    /**
     * Enlève une carte de la matrice
     * @param c Correspond à une carte à enlever
     */
    public synchronized void remove(Card c){
        this.cards.remove(c);
        calculMatrix();
    }
    
    /**
     * Assigne plusieurs cartes à la matrices
     * @param possession Correspond à une liste de cartes
     */
    public synchronized void setCards(List<Card> possession){
        this.cards = possession;
        calculMatrix();
    }
    
    /**
     * Renvoie la matrice 2D en liste
     * @return Retourne la matrice 2D convertie
     */
    public synchronized List<Integer> getMatrixToList(){
        calculMatrix();
        List<Integer> lTempo = new ArrayList<>();
        for(int i=0;i<7;i++){
            for(int j=0;j<6;j++){
                lTempo.add(matrix[i][j]);
            }
        }
        return lTempo;
    }
    
    /**
     * Renvoie la matrice 2D en liste
     * @param notFinished Correspond à la liste des familles qui ne sont pas encore terminées
     * @return Retourne la matrice 2D convertie
     */
    public synchronized List<Integer> getNeutralMatrixToList(List<Family> notFinished){
        calculMatrix();
        calculNeutralMatrix(notFinished);
        
        List<Integer> lTempo = new ArrayList<>();
        for(int i=0;i<7;i++){
            for(int j=0;j<6;j++){
                lTempo.add(matrix[i][j]);
            }
        }
        return lTempo;
    }
    
    /**
     * Calcule la matrice
     * @param notFinished Correspond à la liste des familles qui ne sont pas encore terminées
     */
    private synchronized void calculNeutralMatrix(List<Family> notFinished){
        for(int i=0;i<notFinished.size();i++){
            Family f = notFinished.get(i);
            int id = f.getId();
            
            for(int j=0;j<6;j++){
                matrix[id-1][j] = CAN_HAVE_CARD;
            }
        }
    }
    
    /**
     * Calcule la matrice
     */
    private synchronized void calculMatrix(){
        for(int i=1;i<=7;i++){
            if(hasOneCardOfFamily(i)){
                List<Card> has = getPossessionCardByFamily(i);

                for (int j = 1; j <= 6; j++) {
                    if (contain(has, j)) {
                        matrix[i - 1][j - 1] = HAS_CARD;
                    } else {
                        matrix[i - 1][j - 1] = CAN_HAVE_CARD;
                    }
                }
            }else{
                for(int j=0;j<6;j++)
                    matrix[i-1][j] = HASNOT_CARD;
            }
        }
    }
    
    /**
     * Renvoie true si parmis la liste, l'une des carte possède l'id idCard
     * @param cs Correspond à une liste de carte
     * @param idCard Correspond à l'id d'une carte
     * @return Retourne true si la carte existe dans la liste
     */
    private synchronized boolean contain(List<Card> cs, int idCard){
        for(int i=0;i<cs.size();i++){
            if(cs.get(i).getId() == idCard) return true;
        }
        return false;
    }
    
    /**
     * Renvoie la liste des cartes d'une famille
     * @param idFamily Correspond à l'id d'une famille
     * @return Retourne la liste des cartes de la famille
     */
    private synchronized List<Card> getPossessionCardByFamily(int idFamily){
        List<Card> cs = new ArrayList<>();
        for(int i=0;i<cards.size();i++){
            Card c = cards.get(i);
            if(c.getFamily().getId() == idFamily){
                cs.add(c);
            }
        }
        return cs;
    }
    
    /**
     * Renvoie si oui le joueur possède au moin une carte de la famille
     * @param idFamily Correspond à l'id de la famille
     * @return Renvoie true s'il en existe au moins une
     */
    private synchronized boolean hasOneCardOfFamily(int idFamily){
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getFamily().getId() == idFamily) return true;
        }
        return false;
    }
    
}