/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.ihm;



import java.util.ArrayList;
import java.util.List;
import org.Infos;
import org.ihm.components.Frame;
import org.metier.Family;
import org.metier.salon.Gamer;



/**
 * Correspond à la fenêtre des résultats
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 */
public class Score extends Frame {

    
    
    /**
     * Crée la fenêtre des scores
     * @param gamers Correspond à la liste des joueurs
     */
    public Score(List<Gamer> gamers) {
        initComponents();
        console.append(" SCORES\n------\n\n");
        for(int i=0;i<gamers.size();i++){
            Gamer g = gamers.get(i);
            console.append(" "+g.toString()+": ("+g.myFamilies.size()+" famille"+((g.myFamilies.size()>1) ? "s" : "")+")\n");
            for(int j=0;j<g.myFamilies.size();j++){
                console.append(" \t - "+g.myFamilies.get(j)+"\n");
            }
        }
        
        console.append("\n\n");
        
        int maxScore = 0;
        List<Gamer> gs = new ArrayList<>();
        for (Gamer gamer : gamers) {
            List<Family> families = gamer.myFamilies;
            maxScore = Math.max(maxScore, families.size());
        }
        for (Gamer gamer : gamers) {
            List<Family> families = gamer.myFamilies;
            if (families.size() == maxScore) {
                gs.add(gamer);
            }
        }
        console.append(" LE"+((gs.size()>1) ? "S" : "")+" GAGNANT"+((gs.size()>1) ? "S" : "")+" "+((gs.size()>1) ? "SONT" : "EST")+":\n");
        for (Gamer g : gs) {
            console.append(" \t - " + g + "\n");
        }
    }

    
    
    /**
     * Ferme l'application
     */
    @Override
    public void dispose(){
        System.exit(0);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();

        setTitle("Score de l'application");
        setIconImage(Infos.SOFT_ICON);

        console.setBackground(new java.awt.Color(0, 0, 0));
        console.setColumns(20);
        console.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        console.setForeground(new java.awt.Color(255, 255, 255));
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea console;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
