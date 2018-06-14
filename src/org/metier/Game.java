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
import java.util.HashMap;
import java.util.List;
import org.Infos;
import org.ihm.Screen;
import org.ihm.components.CardComponent;
import org.metier.salon.Gamer;
import org.metier.salon.Human;
import org.metier.salon.ia.Aurore;
import org.metier.salon.ia.Diane;
import org.metier.salon.ia.Marc;
import org.metier.util.Matrix;
import org.metier.util.Message;
import org.metier.util.Synchronizer;



/**
 * Cette classe permet d'initialiser le jeu. Elle contient quelques variables statiques utiles pour les joueurs en cours de jeu
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Game {

    private final List<Card> listCardsGame;
    private Pioche pioche;
    public List<Gamer> gamers;
    public static List<Family> families;
    private Screen screen;
    private HashMap<Gamer, List<Family>> score;
    public static boolean gameFinished;
    public static Synchronizer syn;
    public static Matrix matrix;
    
    
    
    /**
     * Initialise le jeu
     */
    @SuppressWarnings("static-access")
    public Game() {
        
        this.listCardsGame = new ArrayList<>();
        this.families = new ArrayList<>();
        this.gamers = new ArrayList<>();
        this.score = new HashMap<>();
        this.gameFinished = false;
        this.syn = new Synchronizer();
        this.screen = new Screen();
        this.matrix = new Matrix();
        
        String[] familiesName = new String[]{"Instruments à cordes frottées", "Instruments à cordes pincées ou frappées", "Instruments à vent: les cuivres", "Instruments électriques", "Instruments à percussion: les idiophones", "Instruments à percussion: les membranophones", "Instruments à vent: les bois"};
        String[][] cardsName = {
            {"le violon", "l'alto", "le violoncelle", "la contrebasse", "La vielle à roue", "la viole de gambe"},
            {"le piano", "la guitare", "la lyre", "le banjo", "la harpe", "la mandoline"},
            {"la trompette", "le serpent", "le tuba", "le didgeridoo", "le cor d'harmonie", "le trombone"},
            {"l'orgue Hammond", "le thérémine", "l'instrument virtuel", "le piano électrique", "les ondes Martenot", "le synthétiseur"},
            {"le triangle", "les castagnettes", "les claves", "les maracas", "les cymbales", "les grelots"},
            {"le tambour", "le djembé", "le bongo", "les timbales", "le tambourin", "la grosse caisse"},
            {"la clarinette", "la flûte à bec", "l'harmonica", "la cornemuse", "le saxophone", "l'accordéon"}
        };
        
        for(int i=1;i<=7;i++){
            Family f = new Family(i, familiesName[i-1]);
            families.add(f);
            for(int j=1;j<=6;j++){
                Card c = new Card(f, j, cardsName[i-1][j-1], "/org/ihm/img/cards/f"+i+"-c"+j+".png");
                this.listCardsGame.add(c);
                screen.addScreen(new CardComponent(c));
            }
        }
        
        this.pioche = new Pioche(listCardsGame);
        this.pioche.shuffle();
        List<List<Card>> cardsList = pioche.getCards(4);
        
        for(int i=0;i<cardsList.size();i++){
            Gamer g;
            switch (i) {
                case 0:
                    g = new Human(this, Infos.NOM_JOUEUR, listCardsGame, cardsList.get(i), screen);
                    break;
                case 1:
                    g = new Diane(this, listCardsGame, cardsList.get(i), screen);
                    break;
                case 2:
                    g = new Marc(this, listCardsGame, cardsList.get(i), screen);
                    break;
                default:
                    g = new Aurore(this, listCardsGame, cardsList.get(i), screen);
                    break;
            }
            gamers.add(g);
        }
        
        screen.setGamers(gamers);
        
        for(int i=0;i<gamers.size();i++){
            if(i == 0){
                screen.setTheGamer(gamers.get(i));
                screen.setCards(cardsList.get(0));
            }
        }
        
        this.screen.setVisible(true);
        
        int numStartPlayer = (int) (Math.random() * (4));
        Gamer startPlayer = gamers.get(numStartPlayer);
        
        startPlayer.play(new Message(null, null, null, false, null, false, null, startPlayer, -1, -1));
        
        
    }
    
    /**
     * Renvoie une carte de la pioche
     * @return Retourne une carte de la pioche
     */
    public Card getCardPioche(){
        return pioche.getCard();
    }
    
}
