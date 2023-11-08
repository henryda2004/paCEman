package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Pastilla
 */
public class Pill {
    private Image pillImage;

    /**
     * Constructor de la clase
     */
    public Pill(){
        pillImage = new ImageIcon("images\\pill.png").getImage();
    }

    /**
     * Metodo de dibujo
     * @param g2d
     * @param x
     * @param y
     */
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(pillImage, x, y, null);
    }

}
