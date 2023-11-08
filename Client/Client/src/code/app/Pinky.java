package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Pinky fantasma derivado de Ghost
 */
public class Pinky implements Ghost{

    private Image ghostImage;

    /**
     * Constructor de Pinky
     */
    public Pinky(){
        ghostImage = new ImageIcon("images\\pinky.gif").getImage();
    }

    /**
     * Metodo para dibujarlo en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(ghostImage, x, y, null);
    }

    /**
     * Metodo para obtener la imagen de Pinky
     * @return
     */
    @Override
    public Image getGhostImage() {
        return ghostImage;
    }


}
