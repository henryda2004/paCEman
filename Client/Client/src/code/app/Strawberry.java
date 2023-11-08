package code.app;

import javax.swing.*;
import java.awt.*;

/**
 * Clase para crear fruta StrawBerry derivada de Fruit
 */
public class Strawberry implements Fruit{
    private java.lang.Integer x;
    private java.lang.Integer y;
    private java.lang.Integer puntaje;
    private Image fruitImage;

    /**
     * Constructor de Strawberry
     * @param x
     * @param y
     * @param puntaje
     */
    public Strawberry(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje) {
        this.x = x;
        this.y = y;
        this.puntaje = puntaje; // Puntaje específico para la cereza

        fruitImage = new ImageIcon("images\\strawberry.png").getImage();
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
     * @return Puntaje
     */
    @Override
    public java.lang.Integer getPuntaje() {
        return puntaje;
    }

    /**
     * Metodo para dibujar en el lienzo la fruta
     * @param g2d
     * @param x
     * @param y
     */
    @Override
    public void draw(Graphics2D g2d, java.lang.Integer x, java.lang.Integer y) {
        g2d.drawImage(fruitImage, x, y, null);
    }


}
