package code.app;

import javax.swing.*;

/**
 * Clase que permite la creacion de un juego pacman ya sea jugador u observador
 */
public class PacManGame extends JFrame {
    /**
     * Constructor de clase para jugador
     * @param x
     * @param id
     */
    public PacManGame(java.lang.Integer x, java.lang.String id) {
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);


        if (x == 0){
            add(new Player());
        }
        else {
            add(new Observer(id));
        }

        setLocationRelativeTo(null);
    }

    /**
     * Cosntructor de clase para observador
     * @param x
     */
    public PacManGame(java.lang.Integer x) {
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);

        if (x == 0){
            add(new Player());
        }

        setLocationRelativeTo(null);
    }



}

