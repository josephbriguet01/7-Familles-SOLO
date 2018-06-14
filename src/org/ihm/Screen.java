/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.ihm;



import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.Infos;
import static org.Infos.SPEED_IA;
import org.ihm.components.CardComponent;
import org.ihm.components.Frame;
import org.metier.Card;
import org.metier.Family;
import org.metier.Game;
import org.metier.salon.Gamer;
import org.metier.util.Message;
import org.metier.util.Pictures;



/**
 * Crée la fenêtre principale
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Screen extends Frame{

    
    
    private Gamer theGamer;
    private List<Card> myCard;
    private boolean canPlay;
    private List<Gamer> gamers;
    private Color[] colorsText = {Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED};
    
    
    
    /**
     * Creée la fenêtre principale
     */
    public Screen() {
        initComponents();
        
        String authors = "";
        
        for(int i=0;i<Infos.SOFT_AUTHOR.length;i++){
            if(i == 0) authors += Infos.SOFT_AUTHOR[i];
            else authors += ", "+Infos.SOFT_AUTHOR[i];
        }
        
        copyright.setText("Développeur"+((Infos.SOFT_AUTHOR.length > 1) ? "s" : "")+": "+authors+"  -  "+Infos.SOFT_COPYRIGHT);
        
        this.gamers = new ArrayList<>();
    }
    
    
    
    /**
     * Place les cartes au départ sur l'écran
     * @param list Correspond à la liste de cartes
     */
    public void setCards(List<Card> list){
        this.myCard = list;
        Game.matrix.setCards(list);
        refreshCards();
    }
    
    /**
     * Donne le nom des joueurs dans le jeu
     * @param gamers Correspond à la liste des joueurs
     */
    public void setGamers(List<Gamer> gamers){
        this.gamers = gamers;
    }
    
    /**
     * Ajoute une carte graphique
     * @param cc Correspond à une carte graphique
     */
    public void addScreen(CardComponent cc){
        cc.setParent(this);
        jPanel8.add(cc);
        jPanel8.repaint();
    }

    /**
     * Définit le joueur qui visionne l'écran
     * @param theGamer Correspond au joueur qui visionne l'écran
     */
    public void setTheGamer(Gamer theGamer) {
        this.theGamer = theGamer;
    }
    
    /**
     * Lorsqu'une carte est cliqué
     * @param c Correspond à la carte cliqué
     */
    public void clickedCardComponent(Card c){
        if(canPlay){
            List<Gamer> tempo = new ArrayList<>();
            for(int i=0;i<gamers.size();i++){
                tempo.add(gamers.get(i));
            }
            tempo.remove(theGamer);
            
            Gamer g = new ChoiceGamer(this, true, tempo).showForm();
            
            cannotPlay();
            
            theGamer.GTG_SendRequest(g, c);
        }
    }
    
    /**
     * Lorsque la souris entre sur une carte
     * @param icon Correspond à l'icone de la carte
     */
    public void enterCardComponent(Icon icon){
        Pictures pic = new Pictures(icon);
        pic.resize(jPanel9.getPreferredSize().width, jPanel9.getPreferredSize().height);
        
        jLabel3.setIcon(pic.getIcon());
    }
    
    /**
     * Lorsque la souris sort d'une carte
     */
    public void exitCardComponent(){
        jLabel3.setIcon(null);
    }
    
    /**
     * Affiche les tours dans la console
     * @param m Correspond au message du tour précédant
     */
    public void display(Message m){
        new Thread(() -> {
            
            if(m.getExpeditor() != null){
                
                appendToTextPane(" " + m.getExpeditor() + ": " + m.getReceiver() + " as-tu la carte " + m.getRequestCard()+ " ?", getColorByGamer(m.getExpeditor()));
                
                waiting(SPEED_IA);
                
                if(m.isReceiverHas()) {
                    
                    appendToTextPane("    " + m.getReceiver()+ ": oui je l'ai ! Je te la donne !", getColorByGamer(m.getReceiver()));
                    
                    waiting(SPEED_IA);
                    
                    if(m.getFinishFamily() != null){
                        
                        appendToTextPane("    " + m.getExpeditor() + ": J'ai fini la famille "+m.getFinishFamily()+".", getColorByGamer(m.getExpeditor()));
                
                        waiting(SPEED_IA);
                        
                    }
                    
                }else{
                        
                    appendToTextPane("    " + m.getReceiver() + ": non je ne l'ai pas !"+((m.getPiocheCard() != null) ? " Pioche !" : ""), getColorByGamer(m.getReceiver()));

                    waiting(SPEED_IA);
                    
                    if(m.getPiocheCard() != null){
                        
                        if(m.getExpeditor().equals(theGamer)){
                        
                            appendToTextPane(" # Vous piochez la carte " + m.getPiocheCard()+ ".", getColorByGamer(m.getExpeditor()));
                
                        }else{
                            
                            appendToTextPane(" # "+m.getExpeditor()+"  pioche une carte.", getColorByGamer(m.getExpeditor()));
                            
                        }
                        
                        waiting(SPEED_IA);
                        
                        if(m.isGoodPioche()){
                            
                            if(m.getFinishFamily() != null){
                            
                                appendToTextPane("    " + m.getExpeditor() + ": Héhé, Bonne pioche ! Et famille: " + m.getFinishFamily() + ".", getColorByGamer(m.getExpeditor()));
                
                                waiting(SPEED_IA);
                
                            }else{
                                
                                appendToTextPane("    " + m.getExpeditor() + ": Héhé, Bonne pioche !", getColorByGamer(m.getExpeditor()));
                                
                                waiting(SPEED_IA);
                                
                            }
                            
                        }else{
                            
                            if(m.getFinishFamily() != null){
                                
                                appendToTextPane("    " + m.getExpeditor() + ": Je viens de terminer la famille: " + m.getFinishFamily() + ".", getColorByGamer(m.getExpeditor()));
                                
                                waiting(SPEED_IA);
                                
                            }
                            
                        }
                        
                    }
                    
                }
            
                if (Gamer.finishFamilies.size() < 7) {

                    appendToTextPane("    " + m.getNextPlayer() + ": C'est à moi de jouer maintenant !", getColorByGamer(m.getNextPlayer()));

                    if(!m.getNextPlayer().equals(theGamer)) waiting(SPEED_IA);
                    
                }else{
                    
                    Game.gameFinished = true;
                    this.dispose();
                    new Score(gamers).setVisible(true);
                    
                }
                
            }else{
                
                appendToTextPane(" " + m.getNextPlayer() + ": Je commence la partie !", getColorByGamer(m.getNextPlayer()));
                
            }
            refreshCards();
            Game.syn.set(1);
        }).start();
    }
    
    /**
     * Ferme la fenêtre
     */
    @Override
    public void dispose(){
        if(!Game.gameFinished)
            System.exit(0);
        else
            super.dispose();
    }
    
    /**
     * Renvoie la couleur d'un joueur
     * @param g Correspond au joueur
     * @return Retourne la couleur
     */
    private Color getColorByGamer(Gamer g){
        return colorsText[gamers.indexOf(g)];
    }
    
    /**
     * Fait patienter time milliseconde
     * @param time Correspond au nombre de millisecondes
     */
    public void waiting(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Lorsque le joueur peut jouer
     */
    public void canPlay(){
        new Thread(() -> {
            if(theGamer.gamerCards.size() == (theGamer.myFamilies.size()*6)){
                new Thread(() -> {
                    
                    List<Family> missing = new ArrayList<>();
                    for (Family familie : Game.families) {
                        if (!Gamer.finishFamilies.contains(familie)) {
                            missing.add(familie);
                        }
                    }
                    
                    List<Integer> li = Game.matrix.getNeutralMatrixToList(missing);
                    for(int i=0;i<jPanel8.getComponentCount();i++){
                        CardComponent cc = (CardComponent) jPanel8.getComponent(i);
                        cc.setStatus(li.get(i));
                    }
                }).start();
            }
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/discreaseCliqued.png"))); // NOI18N
            jLabel2.setToolTipText("C'est à vous de jouer");
            this.canPlay = true;
        }).start();
    }
    
    /**
     * Lorsque le joueur ne peut plus jouer
     */
    public void cannotPlay(){
        new Thread(() -> {
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitCliqued.png"))); // NOI18N
            jLabel2.setToolTipText("Ce n'est pas à votre tour de jouer");
            this.canPlay = false;
        }).start();
    }
    
    /**
     * Rafraichit la matrice
     */
    public synchronized void refreshCards() {
        new Thread(() -> {
            List<Integer> li = Game.matrix.getMatrixToList();
            for(int i=0;i<jPanel8.getComponentCount();i++){
                CardComponent cc = (CardComponent) jPanel8.getComponent(i);
                cc.setStatus(li.get(i));
            }
        }).start();
    }
    
    /**
     * Ajoute un texte dans la console
     * @param msg Correspond au texte
     * @param c Correspond à la couleur
     */
    private synchronized void appendToTextPane(String msg, Color c){
        if(c.equals(colorsText[0])){
            appendToPaneBOLD(msg, c);
        }else{
            appendToPane(msg, c);
        }
    }
    
    /**
     * Ajoute du texte dans la console
     * @param msg Correspond au message
     * @param c Correspond à la couleur
     */
    private synchronized void appendToPane(String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Consolas");
        aset = sc.addAttribute(aset, StyleConstants.Bold, false);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg+"\n");
    }
    
    /**
     * Ajoute du texte en gras dans la console
     * @param msg Correspond au message
     * @param c Correspond à la couleur
     */
    private synchronized void appendToPaneBOLD(String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Consolas");
        aset = sc.addAttribute(aset, StyleConstants.Bold, true);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg+"\n");
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tp = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        copyright = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Infos.SOFT_NAME);
        setExtendedState(6);
        setIconImage(Infos.SOFT_ICON);
        getContentPane().setLayout(new java.awt.BorderLayout());

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setMinimumSize(new java.awt.Dimension(1, 571));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(1151, 571));
        jPanel6.setLayout(new java.awt.BorderLayout(10, 0));

        jPanel7.setMinimumSize(new java.awt.Dimension(350, 1));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(350, 571));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(73, 106, 98));
        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.GridLayout(7, 6, 10, 10));
        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel14.setOpaque(false);
        jPanel14.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        tp.setBackground(new java.awt.Color(0, 0, 0));
        tp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tp.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tp);

        jPanel14.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 175));

        jPanel4.setOpaque(false);
        jPanel4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel4ComponentResized(evt);
            }
        });
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(73, 106, 98));
        jPanel9.setMinimumSize(new java.awt.Dimension(100, 153));
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9, java.awt.BorderLayout.LINE_START);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitCliqued.png"))); // NOI18N
        jLabel2.setToolTipText("Ce n'est pas à votre tour de jouer");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel2, java.awt.BorderLayout.LINE_END);

        copyright.setForeground(new java.awt.Color(255, 255, 255));
        copyright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        copyright.setText("Copyright");
        copyright.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        copyright.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(copyright, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel4ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel4ComponentResized
        int height = evt.getComponent().getHeight();
        int width = (height * 244 / 358);
        jPanel9.setSize(width, height);
        jPanel9.setPreferredSize(new Dimension(width, height));
        jPanel9.setMinimumSize(new Dimension(width, height));
        jPanel9.setMaximumSize(new Dimension(width, height));
    }//GEN-LAST:event_jPanel4ComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel copyright;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane tp;
    // End of variables declaration//GEN-END:variables
}