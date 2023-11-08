package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase BlueGhost utilizado para cuando se tiene pastilla y se puede matar a los fantasmas
 */
public class BlueGhost implements Ghost{

    private Image ghostImage;

    /**
     * Constructor de BlueGhost
     */
    public BlueGhost(){
        ghostImage = new ImageIcon("images\\blueghost.png").getImage();
    }

    /**
     * Metodo que describe como se dibuja el fantasma
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
