package code.app;

import java.awt.*;

/**
 * Interfaz para la creacion de fantasmas
 */
public interface Ghost {
    /**
     * Permite obtener la imagen de ghost
     * @return
     */
    Image getGhostImage();

    /**
     * Metodo para dibujar el fantasma en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y);
}
