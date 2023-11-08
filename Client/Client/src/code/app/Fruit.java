package code.app;

import java.awt.*;

/**
 * Esta es la interfaz declarada para la creacion de frutas
 */
public interface Fruit {
    /**
     * Obtener Pos X
     * @return
     */
    java.lang.Integer getX();

    /**
     * Obtener Pos Y
     * @return
     */
    java.lang.Integer getY();

    /**
     * Obtener Puntaje
     * @return
     */
    java.lang.Integer getPuntaje();

    /**
     * Metodo para dibujar la fruta en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y);

}
