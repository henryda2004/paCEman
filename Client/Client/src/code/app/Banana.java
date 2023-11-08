package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * La clase Banana es una clase derivada de la clase Fruta
 */
public class Banana implements Fruit{
    private java.lang.Integer x;
    private java.lang.Integer y;
    private java.lang.Integer puntaje;
    private Image fruitImage;

    /**
     * Constructor de la clase
     * @param x
     * @param y
     * @param puntaje
     */
    public Banana(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje) {
        this.x = x;
        this.y = y;
        this.puntaje = puntaje; // Puntaje específico para la cereza

        fruitImage = new ImageIcon("images\\banana.png").getImage();
    }

    /**
     * Obtener Pos X
     * @return x
     */
    @Override
    public java.lang.Integer getX() {
        return x;
    }

    /**
     * Obtener Pos Y
     * @return y
     */
    @Override
    public java.lang.Integer getY() {
        return y;
    }

    /**
     * Obtener Puntaje
     * @return puntaje
     */
    @Override
    public java.lang.Integer getPuntaje() {
        return puntaje;
    }

    /**
     * Funcion que permite dibujar la fruta en el lienzo
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(fruitImage, x, y, null);
    }

}
