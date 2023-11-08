package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Clyde es derivada de fantasma
 */
public class Clyde implements Ghost{

    private Image ghostImage;

    /**
     * Constructor de clase Clyde
     */
    public Clyde(){
        ghostImage = new ImageIcon("images\\clyde.gif").getImage();
    }

    /**
     * Metodo que describe la manera de dibujarse en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(ghostImage, x, y, null);
    }

    /**
     * Metodo que permite obtener la imagen del fantasma
     * @return
     */
    @Override
    public Image getGhostImage() {
        return ghostImage;
    }

}
