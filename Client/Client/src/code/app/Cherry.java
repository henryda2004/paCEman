package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Cherry derivada de fruta
 */
public class Cherry implements Fruit{
    private java.lang.Integer x;
    private java.lang.Integer y;
    private java.lang.Integer puntaje;
    private Image fruitImage;

    /**
     * Constructor de la clase cherry
     * @param x
     * @param y
     * @param puntaje
     */
    public Cherry(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje) {
        this.x = x;
        this.y = y;
        this.puntaje = puntaje; // Puntaje específico para la cereza

        fruitImage = new ImageIcon("images\\cherry.png").getImage();
    }

    /**
     * Obtiene la Pos X
     * @return x
     */
    @Override
    public java.lang.Integer getX() {
        return x;
    }

    /**
     * Obtiene la Pos Y
     * @return y
     */
    @Override
    public java.lang.Integer getY() {
        return y;
    }

    /**
     * Obtiene el puntaje
     * @return puntaje
     */
    @Override
    public java.lang.Integer getPuntaje() {
        return puntaje;
    }

    /**
     * Metodo que describre como se dibuja en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(fruitImage, x, y, null);
    }

}
