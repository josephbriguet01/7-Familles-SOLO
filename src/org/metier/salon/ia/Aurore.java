/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.salon.ia;



import java.util.ArrayList;
import java.util.List;
import org.ihm.Screen;
import org.metier.Card;
import org.metier.Family;
import org.metier.Game;
import org.metier.salon.Gamer;
import org.metier.salon.IA;
import org.metier.util.Message;



/**
 * Crée une IA appelé Aurore
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Aurore extends IA{

    
    
    /**
     * Crée l'IA
     * @param game Correspond à l'objet Game
     * @param gameCards Correspond aux 42 cartes du jeu
     * @param gamerCards Correspond au cartes du joueur
     * @param screen Correspond à l'écran graphique
     */
    public Aurore(Game game, List<Card> gameCards, List<Card> gamerCards, Screen screen) {
        super(game, "Aurore", gameCards, gamerCards, screen);
    }

    
    
    @Override
    public void play(Message m) {
        new Thread(() -> {
            screen.display(m);
            Game.syn.waiting(1);
            Game.syn.set(0);
        
            if(!Game.gameFinished){
                
                List<Card> lcs = new ArrayList<>();
                for(int i=0;i<gamerCards.size();i++){
                    Card c = gamerCards.get(i);
                    if(!myFamilies.contains(c.getFamily()) && !Gamer.finishFamilies.contains(c.getFamily())){
                        lcs.add(c);
                    }
                }
                
                if(!lcs.isEmpty()){
                    int nbPassage = (int) (Math.random() * (10)) + 5;
                    int random = lcs.size() - 1;
                    for (int i = 0; i < nbPassage; i++) {
                        random = (int) (Math.random() * (lcs.size() - 0)) + 0;
                    }

                    if (random != -1) {

                        //Récupère une carte au pif
                        Card c = lcs.get(random);

                        //Récupère une famille pointé
                        Family f = c.getFamily();
                        
                        request(f);
                    }
                }else{
                    List<Family> missing = new ArrayList<>();
                    for(int i=0;i<Game.families.size();i++){
                        Family f = Game.families.get(i);
                        if(!Gamer.finishFamilies.contains(f)){
                            missing.add(f);
                        }
                    }
                    Family choosen = missing.get(0 + (int)(Math.random() * ((missing.size() -1 - 0) + 1)));
                    request(choosen);
                }
            }
        }).start();
    }
    
    /**
     * Calcul la requête
     * @param f Correspond à la famille de la carte d'une requête
     */
    private void request(Family f){
        Card c;
        //Récupère les cartes manquante de la famille
        List<Card> cs = getCardsMissing(f.getId());

        //Quel joueur possèderais l'une de ces cartes (cs)
        Object[] obj = hsp.whoPossession(cs);

        Gamer g;

        //Si on ne sais pas à quel joueur demander alors on en prend un au pif
        if (obj == null) {
            //On choisit une carte au pif parmi les choix de la famille sélectionnée
            c = cs.get((int) (Math.random() * (cs.size())) + 0);

            //On renvoie une liste de gamers potentiels
            List<Gamer> gs = hnp.canHasCard(c);

            if (gs != null) {
                g = gs.get((int) (Math.random() * (gs.size())) + 0);
            } else {
                List<Gamer> ogs = getOtherGamers();
                g = ogs.get((int) (Math.random() * (ogs.size())) + 0);
            }
        } else {
            g = (Gamer) obj[0];
            c = (Card) obj[1];
        }

        if (g != null && c != null) {
            GTG_SendRequest(g, c);
        }
    }
    
}