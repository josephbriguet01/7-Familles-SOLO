/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.ihm.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import org.metier.util.Pictures;

/**
 * Crée une fenêtre (il s'agit d'un template pour les autres fenêtre de l'application)
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Frame extends javax.swing.JFrame {

    
    
    private int posX = 0;   //Position X de la souris au clic
    private int posY = 0;   //Position Y de la souris au clic
    private boolean iinit = false;
    private boolean move = false;
    private boolean resize = false;
    private static int KEY_LEFT = 37;
    private static int KEY_TOP = 38;
    private static int KEY_RIGHT = 39;
    private static int KEY_BOTTOM = 40;
    private boolean resizable = true;
    
    
    
    public Frame() {
        constructorFrame(true);
    }
    
    public Frame(boolean undecorated){
        constructorFrame(undecorated);
    }
    
    private void constructorFrame(boolean undecorated){
        setUndecorated(undecorated);
        this.iinit = false;
        initComponents();
        this.iinit = true;
    }
    
    @Override
    public String getTitle(){
        return super.getTitle();
    }

    @Override
    public void setTitle(String title){
        super.setTitle(title);
        jLabel1.setText(title);
    }
    
    @Override
    public Image getIconImage(){
        return super.getIconImage();
    }
    
    @Override
    public void setIconImage(Image image){
        super.setIconImage(image);
        Pictures pic = new Pictures(image);
        pic.resize(14, 14);
        jLabel1.setIcon(pic.getIcon());
    }
    
    @Override
    public LayoutManager getLayout(){
        return jPanel2.getLayout();
    }

    @Override
    public Container getContentPane() {
        if(!iinit){
            return super.getContentPane();
        }else{
            return jPanel2;
        }
    }
    
    @Override
    public Component add(Component cmpnt){
        if(!iinit){
            return super.add(cmpnt);
        }else{
            return jPanel2.add(cmpnt);
        }
    }

    @Override
    public void add(Component cmpnt, Object o) {
        if(!iinit){
            super.add(cmpnt, o);
        }else{
            jPanel2.add(cmpnt, o);
        }
    }

    @Override
    public void setLayout(LayoutManager lm) {
        if(!iinit){
            super.setLayout(lm);
        }else{
            jPanel2.setLayout(lm);
        }
    }

    @Override
    public void setResizable(boolean bln) {
        super.setResizable(bln);
        this.resizable = bln;
        if(!resizable){
            this.resizeMenu.setEnabled(false);
            resize = false;
        }
    }
    
    public void lockControls(boolean bln){
        lockDiscrease(bln);
        lockExit(bln);
    }
    
    public void lockExit(boolean bln){
        this.exit.setEnabled(!bln);
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }
    
    public void lockDiscrease(boolean bln){
        this.discrease.setEnabled(!bln);
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barMenu = new javax.swing.JPopupMenu();
        moveMenu = new javax.swing.JMenuItem();
        resizeMenu = new javax.swing.JMenuItem();
        stopMenu = new javax.swing.JMenuItem();
        background1 = new org.ihm.components.Background();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        discrease = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        moveMenu.setText("Déplacer");
        moveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveMenuActionPerformed(evt);
            }
        });
        barMenu.add(moveMenu);

        resizeMenu.setText("Retailler");
        resizeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resizeMenuActionPerformed(evt);
            }
        });
        barMenu.add(resizeMenu);

        stopMenu.setText("Stopper");
        stopMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopMenuActionPerformed(evt);
            }
        });
        barMenu.add(stopMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        background1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new Color(255, 255, 255, 75));
        jPanel1.setPreferredSize(new java.awt.Dimension(229, 27));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        discrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/discreaseExited.png"))); // NOI18N
        discrease.setToolTipText("Réduire la fenêtre");
        discrease.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        discrease.setPreferredSize(new java.awt.Dimension(17, 17));
        discrease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                discreaseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                discreaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                discreaseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                discreaseMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                discreaseMouseReleased(evt);
            }
        });
        jPanel4.add(discrease);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitExited.png"))); // NOI18N
        exit.setToolTipText("Fermer la fenêtre");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exit.setPreferredSize(new java.awt.Dimension(17, 17));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitMouseReleased(evt);
            }
        });
        jPanel4.add(exit);

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });
        jLabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel1KeyReleased(evt);
            }
        });
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        background1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(background1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveMenuActionPerformed
        this.moveMenu.setEnabled(false);
        this.resizeMenu.setEnabled(false);
        move = true;
        jLabel1.requestFocus();
        jLabel1.requestFocusInWindow();
    }//GEN-LAST:event_moveMenuActionPerformed

    private void stopMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopMenuActionPerformed
        this.moveMenu.setEnabled(true);
        move = false;
        if(resizable){
            this.resizeMenu.setEnabled(true);
            resize = false;
        }
    }//GEN-LAST:event_stopMenuActionPerformed

    private void resizeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resizeMenuActionPerformed
        this.resizeMenu.setEnabled(false);
        this.moveMenu.setEnabled(false);
        resize = true;
        jLabel1.requestFocus();
        jLabel1.requestFocusInWindow();
    }//GEN-LAST:event_resizeMenuActionPerformed

    private void jLabel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel1KeyReleased
        Dimension dimensionScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int positionX = super.getLocationOnScreen().x;
        int positionY = super.getLocationOnScreen().y;
        if(move){
            if(evt.getKeyCode() == KEY_LEFT){
                if(positionX-1 > -1)
                super.setLocation(positionX-1, positionY);
            }
            if(evt.getKeyCode() == KEY_TOP){
                if(positionY-1 > 0-1)
                super.setLocation(positionX, positionY-1);
            }
            if(evt.getKeyCode() == KEY_RIGHT){
                if(positionX+1+super.getSize().width <= dimensionScreen.width)
                super.setLocation(positionX+1, positionY);
            }
            if(evt.getKeyCode() == KEY_BOTTOM){
                if(positionY+1+super.getSize().height <= dimensionScreen.height)
                super.setLocation(positionX, positionY+1);
            }
        }
        if(resize){
            if(evt.getKeyCode() == KEY_LEFT){
                super.setSize(new Dimension(super.getSize().width-1, super.getSize().height));
            }
            if(evt.getKeyCode() == KEY_TOP){
                super.setSize(new Dimension(super.getSize().width, super.getSize().height-1));
            }
            if(evt.getKeyCode() == KEY_RIGHT){
                super.setSize(new Dimension(super.getSize().width+1, super.getSize().height));
            }
            if(evt.getKeyCode() == KEY_BOTTOM){
                super.setSize(new Dimension(super.getSize().width, super.getSize().height+1));
            }
            this.revalidate();
            this.repaint();
        }
    }//GEN-LAST:event_jLabel1KeyReleased

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        if(resizable && evt.getYOnScreen() < 4){
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
            stopMenuActionPerformed(null);
        }
        jLabel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        posX = evt.getX();    //Position X de la souris au clic
        posY = evt.getY();    //Position Y de la souris au clic
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if(resizable && evt.getClickCount() == 2 && evt.getButton() == 1){
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
            stopMenuActionPerformed(null);
        }
        if(evt.getClickCount() == 1 && evt.getButton() == 3){
            barMenu.show(jLabel1, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        int depX = evt.getX() - posX;
        int depY = evt.getY() - posY;
        setLocation(getX()+depX, getY()+depY);
        this.repaint();
        jPanel1.repaint();
        jPanel3.repaint();
        jPanel4.repaint();
        jLabel1.repaint();
        stopMenuActionPerformed(null);
        jLabel1.setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }//GEN-LAST:event_jLabel1MouseDragged

    private void exitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseReleased
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitExited.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_exitMouseReleased

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitCliqued.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_exitMousePressed

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitExited.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_exitMouseExited

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/exitEntered.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        stopMenuActionPerformed(null);
        this.dispose();
    }//GEN-LAST:event_exitMouseClicked

    private void discreaseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discreaseMouseReleased
        discrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/discreaseExited.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_discreaseMouseReleased

    private void discreaseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discreaseMousePressed
        discrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/discreaseCliqued.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_discreaseMousePressed

    private void discreaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discreaseMouseExited
        discrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/discreaseExited.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_discreaseMouseExited

    private void discreaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discreaseMouseEntered
        discrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ihm/img/discreaseEntered.png"))); // NOI18N
        int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        this.setSize(oldWidth-1, oldHeight);
        this.setSize(oldWidth, oldHeight);
    }//GEN-LAST:event_discreaseMouseEntered

    private void discreaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discreaseMouseClicked
        this.setExtendedState(Frame.ICONIFIED);
        stopMenuActionPerformed(null);
    }//GEN-LAST:event_discreaseMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.ihm.components.Background background1;
    private javax.swing.JPopupMenu barMenu;
    private javax.swing.JLabel discrease;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JMenuItem moveMenu;
    private javax.swing.JMenuItem resizeMenu;
    private javax.swing.JMenuItem stopMenu;
    // End of variables declaration//GEN-END:variables
}