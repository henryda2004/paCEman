package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase de fantasma Inky derivada de fantasma
 */
public class Inky implements Ghost{

    private Image ghostImage;

    /**
     * Constructor de clase
     */
    public Inky(){
        ghostImage = new ImageIcon("images\\inky.gif").getImage();
    }

    /**
     * Metodo para dibujar en el lienzo el fantasma
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(ghostImage, x, y, null);
    }

    /**
     * Metodo para obtener imagen de fantasma
     * @return
     */
    @Override
    public Image getGhostImage() {
        return ghostImage;
    }


}
