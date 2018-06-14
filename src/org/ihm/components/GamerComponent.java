/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.ihm.components;



import java.util.Objects;
import org.metier.salon.Gamer;
import org.metier.salon.IA;



/**
 * Crée un objet gamer
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class GamerComponent extends javax.swing.JPanel {
    
    
    
// <editor-fold defaultstate="collapsed" desc="    //CONSTRUCTOR">
    /**
     * Crée une icone de joueur
     */
    public GamerComponent() {
        initComponents();
        icon.setToolTipText(null);
        name.setText(null);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/ia_unselected.png"))); // NOI18N
    }
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="    //METHODES PUBLICS">
    /**
     * Renvoie l'identité du joueur
     * @return Retourne l'identité du joueur
     */
    public Gamer getGamer() {
        return gamer;
    }
    
    /**
     * Change l'identité du joueur
     * @param gamer Correspond à la nouvelle identité
     */
    public void setGamer(Gamer gamer){
        this.gamer = gamer;
        name.setText(gamer.toString());
        icon.setToolTipText(gamer.toString()+" (Intelligence Artificielle)");
    }
    
    /**
     * Sélectionne ou pas l'icone
     * @param selected True si on veut sélectionner l'icone, sinon false
     */
    public void setSelected(boolean selected){
        if (selected) {
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/client_selected.png"))); // NOI18N
            name.setForeground(new java.awt.Color(255, 255, 255));
        } else {
            if (this.gamer instanceof IA) {
                icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/ia_unselected.png"))); // NOI18N
                name.setForeground(new java.awt.Color(225, 225, 225));
            } else {
                icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/client_unselected.png"))); // NOI18N
                name.setForeground(new java.awt.Color(225, 225, 225));
            }
        }
    }
    
    /**
     * Ajoute un écouteur
     * @param igc Correspond à l'écouteur
     */
    public void addIGamerComponent(IGamerComponent igc){
        this.igc = igc;
    }

    /**
     * Renvoie le résultat de la méthode hashCode()
     * @return Retourne le résultat de la méthode hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.gamer);
        return hash;
    }

    /**
     * Renvoie le résultat de la méthode equals()
     * @param obj Correspond à l'objet à comparer
     * @return Retourne le résultat de la méthode equals()
     */
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
        final GamerComponent other = (GamerComponent) obj;
        return Objects.equals(this.gamer, other.gamer);
    }
// </editor-fold>
    
    
    
    
    
// <editor-fold defaultstate="collapsed" desc="    //METHODE PRIVATE">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(116, 148));
        setMinimumSize(new java.awt.Dimension(116, 148));
        setOpaque(false);

        name.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        name.setForeground(new java.awt.Color(225, 225, 225));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("nom personne");

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/client_unselected.png"))); // NOI18N
        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMouseClicked
        if(igc != null) igc.gamerMouseClicked(this);
    }//GEN-LAST:event_iconMouseClicked
// </editor-fold>

    
    
// <editor-fold defaultstate="collapsed" desc="    //ATTRIBUTS">
    /**
     * Correspond au joueur
     */
    private Gamer gamer;
    
    
    private IGamerComponent igc;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
// </editor-fold>
    
    
    
}