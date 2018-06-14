/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.salon;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.ihm.Screen;
import org.metier.Card;
import org.metier.Family;
import org.metier.Game;



/**
 * Cette classe est une classe abstraite qui montre ce qu'est qu'une IA
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public abstract class IA extends Gamer{
    
    
    /**
     * Correspond à l'enregistrement des coups
     */
    public HsPossession hsp;
    /**
     * Correspond au certitude qu'une personne n'a pas une carte
     */
    public HnPossession hnp;

    
    
    /**
     * Crée un IA
     * @param game Correspond à l'objet Game
     * @param name Correspond au nom de l'IA
     * @param gameCards Correspond aux 42 cartes du jeu
     * @param gamerCards Correspond au cartes du joueur
     * @param screen Correspond à l'écran graphique
     */
    public IA(Game game, String name, List<Card> gameCards, List<Card> gamerCards, Screen screen) {
        super(game, name+" (IA)", gameCards, gamerCards, screen);
        this.hsp = new HsPossession();
        this.hnp = new HnPossession();
    }
    

    
    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     * Renvoie la liste des autres joueurs hormis soi
     * @return Retourne la liste des autres joueurs
     */
    public List<Gamer> getOtherGamers(){
        List<Gamer> ogs = new ArrayList<>();
        for (int i = 0; i < game.gamers.size(); i++) {
            if (!ogs.contains(game.gamers.get(i)) && !game.gamers.get(i).equals(this)) {
                ogs.add(game.gamers.get(i));
            }
        }
        return ogs;
    }
    
    /**
     * Renvoie la liste des familles qui peuvent être jouées
     * @return Retourne la liste
     */
    public List<Family> whatFamilyCanBePlay(){
        List<Family> families1 = new ArrayList<>();
        gamerCards.stream().filter((gamerCard) -> (!families1.contains(gamerCard.getFamily()))).forEachOrdered((gamerCard) -> {
            families1.add(gamerCard.getFamily());
        });
        return families1;
    }
    
    /**
     * Renvoie le nombre de carte que possède le joueur par famille
     * @param idFamily Correspond à l'id de la famille
     * @return Retourne le nombre de carte
     */
    public int nbCardHasByFamily(int idFamily){
        int cpt=0;
        for(int i=0;i<gamerCards.size();i++){
            if(gamerCards.get(i).getFamily().getId() == idFamily){
                cpt++;
            }
        }
        return cpt;
    }
    
    /**
     * Renvoie la liste des carte qui manque pour obtenir une famille
     * @param idFamily Correspond à l'id de la famille
     * @return Retourne la liste de cartes
     */
    public List<Card> getCardsMissing(int idFamily){
        List<Card> cards = new ArrayList<>();
        for(int i=0;i<gameCards.size();i++){
            if(gameCards.get(i).getFamily().getId() == idFamily  && !gamerCards.contains(gameCards.get(i))){
                cards.add(gameCards.get(i));
            }
        }
        return cards;
    }
    
    
    
    /**
     * Correspond à l'enregistrement des coups
     */
    public class HsPossession{
        ArrayMap<Gamer, Card> am;

        public HsPossession() {
            this.am = new ArrayMap<>();
        }
        
        public void add(Gamer g, Card c){
            if(am.containsByValue(c)) am.removeByValue(c);
            am.add(g, c);
        }
        
        public Object[] whoPossession(List<Card> cards){
            for(int i=0;i<am.size();i++){
                if(cards.contains((Card)am.get(i)[1])) return new Object[]{am.get(i)[0], am.get(i)[1]};
            }
            return null;
        }
    }
    
    /**
     * Correspond au certitude qu'une personne n'a pas une carte
     */
    public class HnPossession{
        ArrayMap<Gamer, Card> am;

        public HnPossession() {
            this.am = new ArrayMap<>();
        }
        
        public void add(Gamer g, Card c){
            if(am.containsByValue(c)) am.removeByValue(c);
            am.add(g, c);
        }
        
        public void removeAllCertitude(Gamer g){
            am.removeByKey(g);
        }
        
        public List<Gamer> canHasCard(Card c){
            List<Gamer> gs = new ArrayList<>();
            for(int i=0;i<am.sizeByValue(c);i++){
                Gamer g = am.getByValue(c, i);
                if(!gs.contains(g) && !g.equals(IA.this)) gs.add(g);
            }
            List<Gamer> ogs = new ArrayList<>();
            for(int i=0;i<game.gamers.size();i++){
                Gamer g = game.gamers.get(i);
                if(!gs.contains(g) && !g.equals(IA.this) && !ogs.contains(g)) ogs.add(g);
            }
            if(ogs.isEmpty()) ogs = null;
            return ogs;
        }
    }
    
    /**
     * Crée un objet ArrayMap (Un HashMap ne convenait pas dans notre cas)
     * @param <K> Correspond à la clée
     * @param <V> Correspond à la valeur
     */
    private class ArrayMap<K,V> implements Serializable{
        private static final long serialVersionUID = 1L;
        
        List<K> l1;
        List<V> l2;

        public ArrayMap() {
            this.l1 = new ArrayList<>();
            this.l2 = new ArrayList<>();
        }
        
        public synchronized void add(K key, V value){
            l1.add(key);
            l2.add(value);
        }
        
        public synchronized void removeByKey(K key){
            for(int i=l1.size()-1;i>-1;i--){
                K k = l1.get(i);
                if(k.equals(key)){
                    l1.remove(i);
                    l2.remove(i);
                }
            }
        }
        
        public synchronized void removeByValue(V value){
            for(int i=l2.size()-1;i>-1;i--){
                V v = l2.get(i);
                if(v.equals(value)){
                    l1.remove(i);
                    l2.remove(i);
                }
            }
        }
        
        public synchronized void remove(K key, V value){
            for(int i=l1.size()-1;i>-1;i--){
                K k = l1.get(i);
                V v = l2.get(i);
                if(k.equals(key) && v.equals(value)){
                    l1.remove(i);
                    l2.remove(i);
                }
            }
        }
        
        public synchronized void remove(int indice){
            l1.remove(indice);
            l2.remove(indice);
        }
        
        public synchronized void clear(){
            l1.clear();
            l2.clear();
        }
        
        public synchronized int sizeByKey(K key){
            int cpt = 0;
            for(int i=l1.size()-1;i>-1;i--){
                K k = l1.get(i);
                if(k.equals(key)) cpt++;
            }
            return cpt;
        }
        
        public synchronized int sizeByValue(V value){
            int cpt = 0;
            for(int i=l2.size()-1;i>-1;i--){
                V v = l2.get(i);
                if(v.equals(value)) cpt++;
            }
            return cpt;
        }
        
        public synchronized int size(){
            return this.l1.size();
        }
        
        public synchronized boolean containsByKey(K key){
            return l1.contains(key);
        }
        
        public synchronized boolean containsByValue(V value){
            return l2.contains(value);
        }
        
        public synchronized boolean contains(K key, V value){
            for(int i=l1.size()-1;i>-1;i--){
                K k = l1.get(i);
                V v = l2.get(i);
                if(k.equals(key) && v.equals(value)){
                    return true;
                }
            }
            return false;
        }
        
        public synchronized int indexOfByKey(K key){
            return l1.indexOf(key);
        }
        
        public synchronized int indexOfByValue(V value){
            return l2.indexOf(value);
        }
        
        public synchronized int indexOf(K key, V value){
            for(int i=0;i<l1.size();i++){
                K k = l1.get(i);
                V v = l2.get(i);
                if(k.equals(key) && v.equals(value)) return i;
            }
            return -1;
        }
        
        public synchronized int lastIndexOfByKey(K key){
            return l1.lastIndexOf(key);
        }
        
        public synchronized int lastIndexOfByValue(V value){
            return l2.lastIndexOf(value);
        }
        
        public synchronized int lastIndexOf(K key, V value){
            for(int i=l1.size()-1;i>-1;i--){
                K k = l1.get(i);
                V v = l2.get(i);
                if(k.equals(key) && v.equals(value)) return i;
            }
            return -1;
        }
        
        public synchronized V getByKey(K key, int indice){
            int cpt = 0;
            for(int i=l1.size()-1;i>-1;i--){
                K k = l1.get(i);
                if(k.equals(key)){
                    if(cpt == indice){
                        return l2.get(i);
                    }else{
                        cpt++;
                    }
                }
            }
            return null;
        }
        
        public synchronized K getByValue(V value, int indice){
            int cpt = 0;
            for(int i=l2.size()-1;i>-1;i--){
                V v = l2.get(i);
                if(v.equals(value)){
                    if(cpt == indice){
                        return l1.get(i);
                    }else{
                        cpt++;
                    }
                }
            }
            return null;
        }
        
        public synchronized Object[] get(int indice){
            return new Object[]{l1.get(indice), l2.get(indice)};
        }
        
    }
    
}
