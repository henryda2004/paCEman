package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Blinky es una clase derivada de la clase fantasma
 */
public class Blinky implements Ghost{

    private Image ghostImage;

    /**
     * Constructor de la clase
     */
    public Blinky(){
        ghostImage = new ImageIcon("images\\blinky.gif").getImage();
    }

    /**
     * Metodo que describe la manera de ponerlo en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(ghostImage, x, y, null);
    }

    /**
     * Obtiene la imagen del fantasma
     * @return
     */
    @Override
    public Image getGhostImage() {
        return ghostImage;
    }


}
