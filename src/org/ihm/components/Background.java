/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.ihm.components;



import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.metier.util.Pictures;



/**
 * Crée un panel background
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Background extends javax.swing.JPanel {
    
    
    
    /**
     * Crée un Background
     */
    public Background() {
        initComponents();
    }

    
    
    
    @Override
    public void paintComponent(Graphics g) {
        try {
            Pictures pic = new Pictures(javax.imageio.ImageIO.read(Background.class.getClassLoader().getResourceAsStream("org/ihm/img/background.jpg")));
            pic.resize(this.getWidth(), this.getHeight());
            g.drawImage(pic.getImage(), 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}