/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by LAMACHE, Juin 2018
 */
package org.metier.util;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <div><div style="color: #FF0000; font-style: italic">Cette classe permet de manipuler facilement une image</div><div style="color: #000080; font-style: italic">This class makes it easy to manipulate an image</div></div>
 * @author Joseph BRIGUET
 * @author Ben YOUSSOUFA
 * @author Fabien Tressaille
 * @author Damien ARROUDJ
 * @version 1.0
 */
public class Pictures implements Cloneable{
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond à l'axe des abscisses</div><div style="color: #000080; font-style: italic">Corresponds to the abscissa axis</div></div>
     */
    public static String AXIS_X = "x";
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond à l'axe des ordonnées</div><div style="color: #000080; font-style: italic">Corresponds to the ordinate axis</div></div>
     */
    public static String AXIS_Y = "y";
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond au côté: largeur</div><div style="color: #000080; font-style: italic">Corresponds to the side: width</div></div>
     */
    public static String SIDE_WIDTH = "width";
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond au côté: hauteur</div><div style="color: #000080; font-style: italic">Corresponds to the side: height</div></div>
     */
    public static String SIDE_HEIGHT = "height";
    
    private BufferedImage bufIMG;
    private float offset = 10;
    private float scaleFactor = 1.0f;

//CONSTRUCTORS
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond au constructeur par défaut</div><div style="color: #000080; font-style: italic">Corresponds to the default constructor</div></div>
     */
    public Pictures() {
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond au constructeur par défaut</div><div style="color: #000080; font-style: italic">Corresponds to the default constructor</div></div>
     * @param pic <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un object Pictures</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the Pictures object]</div></div>
     */
    public Pictures(Pictures pic){
        Pictures p = pic.clone();
        this.bufIMG = p.getBufferedImage();
    }
	
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond au constructeur par défaut</div><div style="color: #000080; font-style: italic">Corresponds to the default constructor</div></div>
     * @param path <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond au chemin d'image à charger</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the image path to load]</div></div>
     */
    public Pictures(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            this.bufIMG = img;
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Correspond au constructeur par défaut</div><div style="color: #000080; font-style: italic">Corresponds to the default constructor</div></div>
     * @param url <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'URL de l'image à charger</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the image URL to load]</div></div>
     */
    public Pictures(URL url){
        try {
            BufferedImage img = ImageIO.read(url);
            this.bufIMG = img;
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Construit une Pictures avec une Image</div><div style="color: #000080; font-style: italic">Built a Pictures with Image</div></div>
     * @param img <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à une Image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to an Image]</div></div>
     */
    public Pictures(Image img){
        this.bufIMG = toBufferedImage(img);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Construit une Pictures avec un BufferedImage</div><div style="color: #000080; font-style: italic">Built a Pictures with a BufferedImage</div></div>
     * @param bufIMG <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un BufferedImage</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to a BufferedImage]</div></div>
     */
    public Pictures(BufferedImage bufIMG){
        this.bufIMG = bufIMG;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Construit une Pictures avec une ImageIcon</div><div style="color: #000080; font-style: italic">Built a Pictures with an ImageIcon</div></div>
     * @param imgICO <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à une ImageIcon</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to an ImageIcon]</div></div>
     */
    public Pictures(ImageIcon imgICO){
        this.bufIMG = ImageIcontoBufferedImage(imgICO);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Construit une Pictures avec une Icon</div><div style="color: #000080; font-style: italic">Built a Pictures with an Icon</div></div>
     * @param ico <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à une Icon</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to an Icon]</div></div>
     */
    public Pictures(Icon ico){
        this.bufIMG = toBufferedImage(iconToImage(ico));
    }
    
    
    
//GETTERS & SETTERS
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie un BufferedImage de l'objet Pictures (Accesseur)</div><div style="color: #000080; font-style: italic">Returns a BufferedImage (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne un BufferedImage de l'objet Pictures</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns a BufferedImage]</div></div>
     */
    public BufferedImage getBufferedImage(){
        return this.bufIMG;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Change la Pictures (Mutateur)</div><div style="color: #000080; font-style: italic">Changes the Pictures (Setter)</div></div>
     * @param bufIMG <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un BufferedImage</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to a BufferedImage]</div></div>
     */
    public void setBufferedImage(BufferedImage bufIMG){
        this.bufIMG = bufIMG;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie une Image de l'objet Pictures (Accesseur)</div><div style="color: #000080; font-style: italic">Returns an Image (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne une Image de l'objet Pictures</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns an Image]</div></div>
     */
    public Image getImage(){
        return toImage(bufIMG);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Change la Pictures (Mutateur)</div><div style="color: #000080; font-style: italic">Changes the Pictures (Setter)</div></div>
     * @param img <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à une Image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to an Image]</div></div>
     */
    public void setImage(Image img){
        this.bufIMG = toBufferedImage(img);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie une ImageIcon de l'objet Pictures (Accesseur)</div><div style="color: #000080; font-style: italic">Returns an ImageIcon (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne une ImageIcon de l'objet Pictures</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns an ImageIcon]</div></div>
     */
    public ImageIcon getImageIcon(){
        return (bufIMG != null) ? new ImageIcon(bufIMG) : null;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Change la Pictures (Mutateur)</div><div style="color: #000080; font-style: italic">Changes the Pictures (Setter)</div></div>
     * @param imgICO <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à une ImageIcon</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to an ImageIcon]</div></div>
     */
    public void setImageIcon(ImageIcon imgICO){
        this.bufIMG = ImageIcontoBufferedImage(imgICO);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie une Icon de l'objet Pictures (Accesseur)</div><div style="color: #000080; font-style: italic">Returns an Icon (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne une Icon de l'objet Pictures</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns an Icon]</div></div>
     */
    public Icon getIcon(){
        return (bufIMG != null) ? (Icon)new ImageIcon(bufIMG) : null;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Change la Pictures (Mutateur)</div><div style="color: #000080; font-style: italic">Changes the Pictures (Setter)</div></div>
     * @param ico <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à une Icone</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to an Icon]</div></div>
     */
    public void setIcon(Icon ico){
        this.bufIMG = (ico != null) ? toBufferedImage(iconToImage(ico)) : null;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Change la Pictures (Mutateur)</div><div style="color: #000080; font-style: italic">Changes the Pictures (Setter)</div></div>
     * @param url <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'URL de l'image à charger</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the image URL to load]</div></div>
     */
    public void setURL(URL url){
        try {
            BufferedImage img = ImageIO.read(url);
            this.bufIMG = img;
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie la largeur de Picture (Accesseur)</div><div style="color: #000080; font-style: italic">Returns the width of Picture (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne la largeur de Picture</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns the width of Picture]</div></div>
     */
    public int getWidth(){
        return (bufIMG!=null) ? bufIMG.getWidth() : -1;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie la hauteur de Picture (Accesseur)</div><div style="color: #000080; font-style: italic">Returns the height of Picture (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne la hauteur de Picture</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns the height of Picture]</div></div>
     */
    public int getHeight(){
        return (bufIMG!=null) ? bufIMG.getHeight(): -1;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie la Dimension de l'écran</div><div style="color: #000080; font-style: italic">Returns the screen size</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne la Dimension de l'écran</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns the screen size]</div></div>
     */
    public static Dimension getScreenSize(){
        return (Dimension)Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Renvoie la dimension de l'image (Accesseur)</div><div style="color: #000080; font-style: italic">Returns the size of the image (Getter)</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne la dimension de l'image</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns the size of the image]</div></div>
     */
    public Dimension getSize(){
        return (bufIMG!=null) ? new Dimension(bufIMG.getWidth(), bufIMG.getHeight()) : null;
    }
    
    
    
//METHODE PUBLIC
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Crée une capture d'écran</div><div style="color: #000080; font-style: italic">Creates a screenshot</div></div>
     */
    public void screenCapture(){
        try {
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            this.bufIMG = image;
        } catch (AWTException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Crée une capture d'écran d'un composant</div><div style="color: #000080; font-style: italic">Create a screenshot of a component</div></div>
     * @param c <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un Component</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to a Component]</div></div>
     */
    public void screenCapture(Component c){
        BufferedImage buf = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
        c.paint(buf.getGraphics());
        this.bufIMG = buf;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Supperpose deux images&#46; Image1 se trouve en arrière plan</div><div style="color: #000080; font-style: italic">Superimpose two images&#46; Image1 is in the background</div></div>
     * @param image1 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'image 1</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the picture 1]</div></div>
     * @param image2 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'image 2</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the picture 2]</div></div>
     */
    public void superimpose(BufferedImage image1, BufferedImage image2){
        superimpose(image1, image2, (Math.max(image1.getWidth(), image2.getWidth())-image1.getWidth())/2, (Math.max(image1.getHeight(), image2.getHeight())-image1.getHeight())/2, (Math.max(image1.getWidth(), image2.getWidth())-image2.getWidth())/2, (Math.max(image1.getHeight(), image2.getHeight())-image2.getHeight())/2);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Supperpose deux images&#46; Image1 se trouve en arrière plan</div><div style="color: #000080; font-style: italic">Superimpose two images&#46; Image1 is in the background</div></div>
     * @param image1 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'image 1</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the picture 1]</div></div>
     * @param image2 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'image 2</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the picture 2]</div></div>
     * @param x1 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la position de l'image 1 sur l'axe des abscisses</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the position of the image 1 on the horizontal axis]</div></div>
     * @param y1 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la position de l'image 1 sur l'axe des ordonnées</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the position of the image 1 on the ordinate axis]</div></div>
     * @param x2 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la position de l'image 2 sur l'axe des abscisses</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the position of the image 2 on the horizontal axis]</div></div>
     * @param y2 <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la position de l'image 2 sur l'axe des ordonnées</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the position of the image 2 on the ordinate axis]</div></div>
     */
    public void superimpose(BufferedImage image1, BufferedImage image2, int x1, int y1, int x2, int y2){
        BufferedImage buffer = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2=buffer.createGraphics();
 
        g2.drawImage(image1, x1, y1, null);
        
        g2.drawImage(image2, x2, y2, null);
        g2.dispose();
 
        this.bufIMG = buffer;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Inverse l'image par rapport à l'axe donné</div><div style="color: #000080; font-style: italic">Reverse image relative to the given axis</div></div>
     * @param axis <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à l'axe</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the axis]</div></div>
     */
    public void mirror(String axis){
        if(axis.equals(AXIS_X) && bufIMG != null){
            int width = bufIMG.getWidth();
            int height = bufIMG.getHeight();
            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for(int h=0;h<height;h++){
                for(int w=0;w<width;w++){
                    bimage.setRGB(w, height-(h+1), bufIMG.getRGB(w, h));
                }
            }
            this.bufIMG = bimage;
        }
        if(axis.equals(AXIS_Y) && bufIMG != null){
            int width = bufIMG.getWidth();
            int height = bufIMG.getHeight();
            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for(int h=0;h<height;h++){
                for(int w=0;w<width;w++){
                    bimage.setRGB(width-(w+1), h, bufIMG.getRGB(w, h));
                }
            }
            this.bufIMG = bimage;
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Tourne l'image sur elle-même</div><div style="color: #000080; font-style: italic">Rotate image on itself</div></div>
     * @param degrees <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond aux degrées de rotation</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to degrees of rotation]</div></div>
     */
    public void rotate(int degrees){
        if(degrees<0){
            int quotient = degrees/360;
            int number1 = quotient * 360;
            degrees = degrees - number1;
            int number = degrees * -1;
            degrees = 360-number;
        }
        
        if(degrees>=360){
            int quotient = degrees/360;
            int number = quotient * 360;
            degrees = degrees - number;
        }

        Rectangle[] dim = getRectangleByAngleRotation(degrees);
        AffineTransform at = new AffineTransform();
        
        if(degrees>=0 && degrees<=90)   at.translate(dim[1].getWidth(), 0);
        if(degrees>90 && degrees<=180)  at.translate(dim[0].getWidth(), dim[2].getHeight());
        if(degrees>180 && degrees<=270) at.translate(dim[2].getWidth(), dim[0].getHeight());
        if(degrees>270 && degrees<360) at.translate(0, dim[1].getHeight());
        
        at.rotate(Math.toRadians(degrees));
        
        BufferedImage bimage = new BufferedImage((int)dim[0].getWidth(), (int)dim[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = (Graphics2D) bimage.getGraphics();
        g2d.drawImage(bufIMG, at, null);
        this.bufIMG = bimage;
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Retaille l'image proportionnellement à un côté donné</div><div style="color: #000080; font-style: italic">Resize the picture proportionally to a given side</div></div>
     * @param size <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la taille de la largeur ou de la hauteur</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponding to the size of the width or height]</div></div>
     * @param side <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la constante de côté: width or height</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the constant side: width or height]</div></div>
     */
    public void resize(int size, String side){
        if(side.equals(SIDE_WIDTH) && bufIMG != null){
            int newHeight = (size * bufIMG.getHeight()) / bufIMG.getWidth();
            resize(size, newHeight);
        }
        if(side.equals(SIDE_HEIGHT) && bufIMG != null){
            int newWidth = (size * bufIMG.getWidth()) / bufIMG.getHeight();
            resize(newWidth, size);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Retaille l'image</div><div style="color: #000080; font-style: italic">Resizes Pictures</div></div>
     * @param width <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la nouvelle largeur</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the new width]</div></div>
     * @param height <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la nouvelle hauteur</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the new height]</div></div>
     */
    public void resize(int width, int height){
        if((width>=0 && height>=0)&&(bufIMG != null)) this.bufIMG = resize(bufIMG, width, height);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Rogne la Pictures</div><div style="color: #000080; font-style: italic">Crops the Pictures</div></div>
     * @param x <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la postion x de l'image en haut à gauche de la nouvelle image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the position x of the top left image of the new image]</div></div>
     * @param y <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la postion y de l'image en haut à gauche de la nouvelle image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the position y of the top left image of the new image]</div></div>
     * @param width <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la largeur de la nouvelle image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the width of the new image]</div></div>
     * @param height <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la hauteur de la nouvelle image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the height of the new image]</div></div>
     */
    public void crop(int x, int y, int width, int height){
        if(bufIMG!=null) this.bufIMG = bufIMG.getSubimage(x, y, width, height);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Rogne la Pictures</div><div style="color: #000080; font-style: italic">Crops the Pictures</div></div>
     * @param left <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond au nombre de pixels enlevés à gauche</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the number of pixels to left removed]</div></div>
     * @param right <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond au nombre de pixels enlevés à droite</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the number of pixels to right removed]</div></div>
     * @param top <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond au nombre de pixels enlevés en haut</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the number of pixels to top removed]</div></div>
     * @param bottom <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond au nombre de pixels enlevés en bas</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the number of pixels to bottom removed]</div></div>
     */
    public void cropBySide(int left, int right, int top, int bottom){
        if(bufIMG!=null){
            int width = getWidth() - left - right;
            int height = getHeight() - top - bottom;
            if(width<0)width = 0;
            if(height<0)height = 0;
            crop(left, top, width, height);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Rend flou la Pictures</div><div style="color: #000080; font-style: italic">Blurs the Pictures</div></div>
     * @param matrixBlurred <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à la matrice de flou&#46; Le point central représente le pixel visé et les autres correspondent aux décallages&#46;</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the blur matrix&#46; The central point is the target pixel and the other correspond to décallages&#46;]</div></div>
     */
    public void blurred(float[] matrixBlurred){
        if(matrixBlurred!=null){
            if(matrixBlurred.length!=9)System.err.println("matrixBlurred.length != 9");
            if((bufIMG != null)&&(matrixBlurred.length==9)) this.bufIMG = rendreFlou(bufIMG, matrixBlurred);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Rend flou la Pictures avec une matrice déjà pré-enregistré</div><div style="color: #000080; font-style: italic">Blurring the Pictures with an already pre-registered matrix</div></div>
     */
    public void blurred(){
        float[] matrix = {0.1f, 0.1f, 0.1f, 0.1f, 0.2f, 0.1f, 0.1f, 0.1f, 0.1f};
        blurred(matrix);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Tranforme la forme de l'image en ellipse</div><div style="color: #000080; font-style: italic">Transforms the image shape to ellipse</div></div>
     */
    public void ellipseImage(){
        int w = bufIMG.getWidth();
        int h = bufIMG.getHeight();
        BufferedImage bimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        Shape shape = new java.awt.geom.Ellipse2D.Float(0, 0, w - 1, h - 1);
        bGr.setClip(shape);
        bGr.drawImage(toImage(bufIMG), 0, 0, null);
        bGr.dispose();
        this.bufIMG = bimage;
    }
	
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Charge une Pictures</div><div style="color: #000080; font-style: italic">Load a Pictures</div></div>
     * @param path <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à son chemin</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to its path]</div></div>
     */
    public void load(String path){
        try {
            BufferedImage img = ImageIO.read(new File(path));
            this.bufIMG = img;
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Enregistre une Pictures en &#46;png</div><div style="color: #000080; font-style: italic">Save a Pictures in &#46;png</div></div>
     * @param path <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à son chemin</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to its path]</div></div>
     */
    public void savePNG(String path){
        if(bufIMG!=null) save(bufIMG, path, "png");
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Enregistre une Pictures en &#46;jpg</div><div style="color: #000080; font-style: italic">Save a Pictures in &#46;jpg</div></div>
     * @param path <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à son chemin</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to its path]</div></div>
     */
    public void saveJPEG(String path){
        if(bufIMG!=null) save(bufIMG, path, "jpg");
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Enregistre une Pictures en &#46;gif</div><div style="color: #000080; font-style: italic">Save a Pictures in &#46;gif</div></div>
     * @param path <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à son chemin</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to its path]</div></div>
     */
    public void saveGIF(String path){
        if(bufIMG!=null) save(bufIMG, path, "gif");
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Augmente la luminosité de la Pictures</div><div style="color: #000080; font-style: italic">Increase the brightness of the Pictures</div></div>
     */
    public void brightnessUP(){
        if (offset < 255) offset = offset+5.0f;
        new RescaleOp(scaleFactor, offset, null).filter(bufIMG, bufIMG);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Augmente la luminosité de la Pictures</div><div style="color: #000080; font-style: italic">Decrease the brightness of the Pictures</div></div>
     */
    public void brightnessDOWN(){
        if (offset > 0) offset = offset-5.0f;
        new RescaleOp(scaleFactor, offset, null).filter(bufIMG, bufIMG);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Augmente le contraste de la Pictures</div><div style="color: #000080; font-style: italic">Increase the contrast of the Pictures</div></div>
     */
    public void contrastUP(){
        if (scaleFactor < 2) scaleFactor = scaleFactor+0.1f;
        new RescaleOp(scaleFactor, offset, null).filter(bufIMG, bufIMG);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Augmente la luminosité de la Pictures</div><div style="color: #000080; font-style: italic">Decrease the contrast of the Pictures</div></div>
     */
    public void contrastDOWN(){
        if (scaleFactor > 0) scaleFactor = scaleFactor-0.1f;
        new RescaleOp(scaleFactor, offset, null).filter(bufIMG, bufIMG);
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Sature ou pas en rouge la Pictures</div><div style="color: #000080; font-style: italic">Saturates or not in red the Pictures</div></div>
     * @param percent <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un poucentage [-100; 100]</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to a percentage [-100; 100]]</div></div>
     */
    public void setRed(int percent){
        if((percent<=100 && percent>=-100) && bufIMG != null){
            int width = bufIMG.getWidth();
            int height = bufIMG.getHeight();
            for(int h=0;h<height;h++){
                for(int w=0;w<width;w++){
                    Color couleur = new Color(bufIMG.getRGB(w, h), true);
                    int green = couleur.getGreen();
                    int blue = couleur.getBlue();
                    int alpha = couleur.getAlpha();
                    
                    bufIMG.setRGB(w, h, new Color(((percent * 255)/100), green, blue, alpha).getRGB());
                }
            }
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Sature ou pas en vert la Pictures</div><div style="color: #000080; font-style: italic">Saturates or not in green the Pictures</div></div>
     * @param percent <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un poucentage [-100; 100]</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to a percentage [-100; 100]]</div></div>
     */
    public void setGreen(int percent){
        if((percent<=100 && percent>=-100) && bufIMG != null){
            int width = bufIMG.getWidth();
            int height = bufIMG.getHeight();
            for(int h=0;h<height;h++){
                for(int w=0;w<width;w++){
                    Color couleur = new Color(bufIMG.getRGB(w, h), true);
                    int red = couleur.getRed();
                    int blue = couleur.getBlue();
                    int alpha = couleur.getAlpha();
                    
                    bufIMG.setRGB(w, h, new Color(red, ((percent * 255)/100), blue, alpha).getRGB());
                }
            }
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Sature ou pas en bleu la Pictures</div><div style="color: #000080; font-style: italic">Saturates or not in blue the Pictures</div></div>
     * @param percent <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond à un poucentage [-100; 100]</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to a percentage [-100; 100]]</div></div>
     */
    public void setBlue(int percent){
        if((percent<=100 && percent>=-100) && bufIMG != null){
            int width = bufIMG.getWidth();
            int height = bufIMG.getHeight();
            for(int h=0;h<height;h++){
                for(int w=0;w<width;w++){
                    Color couleur = new Color(bufIMG.getRGB(w, h), true);
                    int red = couleur.getRed();
                    int green = couleur.getGreen();
                    int alpha = couleur.getAlpha();
                    
                    bufIMG.setRGB(w, h, new Color(red, green, ((percent * 255)/100), alpha).getRGB());
                }
            }
        }
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Charge une image des ressources du projet</div><div style="color: #000080; font-style: italic">Load an image of project resources</div></div>
     * @param ressourcesPath <div><div style="color: #008000; font-weight: bold; font-style: italic; display: inline-block">Correspond au chemin de l'image</div> <div style="color: #9400D3; font-style: italic; display: inline-block">[Corresponds to the image path]</div></div>
     */
    public void loadRessourceImage(String ressourcesPath){
        setImage((Image)Toolkit.getDefaultToolkit().getImage(ressourcesPath));
    }
    
    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Redéfinit la méthode clone()</div><div style="color: #000080; font-style: italic">Returns the result of the method clone()</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne le résultat de la méthode clone()</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns the result of the method clone()]</div></div>
     */
    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public Pictures clone() {
	Object o = null;
	try {
            o = super.clone();
	} catch(CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
	}
	return (Pictures) o;
    }

    /**
     * <div><div style="color: #FF0000; font-weight: bold; font-style: italic">Redéfinit la méthode toString()</div><div style="color: #000080; font-style: italic">Returns the result of the method toString()</div></div>
     * @return <div><div style="color: #6495ED; font-weight: bold; font-style: italic; display: inline-block">Retourne le résultat de la méthode toString()</div> <div style="color: #808080; font-style: italic; display: inline-block">[Returns the result of the method toString()]</div></div>
     */
    @Override
    public String toString() {
        return "Pictures{" + "bufIMG=" + bufIMG + '}';
    }
    
    
    
//METHODE PRIVATE
    /**
     * Renvoie la taille que doit avoir l'image lorsque l'image est tourné de angle°
     */
    private Rectangle[] getRectangleByAngleRotation(double angle){
        int width;
        int height;
        width = bufIMG.getWidth();
        height = bufIMG.getHeight();
        
        
        if(angle>=180 && angle<360){
            angle -= 180;
        }
        
        double top_left = Math.sin(Math.toRadians(angle))*height;
        double right_top = Math.sin(Math.toRadians(angle))*width;
        double top_right = Math.sqrt(Math.pow(width, 2)-Math.pow(right_top, 2));
        double right_bottom = Math.sqrt(Math.pow(height, 2)-Math.pow(top_left, 2));
        
        int newWidth = (int) (top_left + top_right);
        int newHeight = (int) (right_top + right_bottom);
        
        Rectangle rec = new Rectangle(newWidth, newHeight);
        Rectangle rec1 = new Rectangle((int)top_left, (int)right_top);
        Rectangle rec2 = new Rectangle((int)top_right, (int)right_bottom);
        Rectangle[] recs = {rec, rec1, rec2};
        
        return recs;
    }
    
    /**
     * Enregistre un bufferedImage en png ou jpeg ou gif
     */
    private void save(BufferedImage bi, String path, String ext){
        try {
            ImageIO.write(bi, ext, new File(path));
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Rend flou une image en fonction d'une matrice
     */
    private static BufferedImage rendreFlou(BufferedImage bufferedImage, float[] matrix){
        double rac = Math.sqrt(matrix.length);
        int rac1 = (int) rac;
        double rac2 = (double)rac1;
        if(rac != rac2)return bufferedImage;
        else{
            BufferedImageOp op = new ConvolveOp(new Kernel(rac1,rac1,matrix)); 
            BufferedImage nouvelleImage = op.filter(bufferedImage, null);
            return nouvelleImage;
        }
    }
    
    /**
     * Retaille une image
     */
    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        if(img != null){
            int w = img.getWidth();  
            int h = img.getHeight();  
            BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
            Graphics2D g = dimg.createGraphics();  
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
            g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
            g.dispose();  
            return dimg;
        }else return null;
    }
    
    /**
     * donne une image d'une icon
     */
    private static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) return ((ImageIcon) icon).getImage();
        else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }
    
    /**
     * donne un bufferedImage d'une ImageIcon
     */
    private static BufferedImage ImageIcontoBufferedImage(ImageIcon imgICO){
        if(imgICO!=null){
            BufferedImage bi = new BufferedImage(
            imgICO.getIconWidth(),
            imgICO.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            // paint the Icon to the BufferedImage.
            imgICO.paintIcon(null, g, 0,0);
            g.dispose();
            return bi;
        }else return null;
    }
    
    /**
     * Donne un bufferedImage d'une image
     */
    private static BufferedImage toBufferedImage(Image img){
        if(img!=null){
            if (img instanceof BufferedImage) return (BufferedImage) img;

            // Create a buffered image with transparency
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();

            // Return the buffered image
            return bimage;
        }else return null;
    }
    
    /**
     * Donne une image d'un bufferedImage
     */
    private static Image toImage(BufferedImage bufIMG){
        return (bufIMG != null) ? new ImageIcon(bufIMG).getImage() : null;
    }
}
