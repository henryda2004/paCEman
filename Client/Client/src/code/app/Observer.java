package code.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import java.net.Socket;

/**
 * Clase Observador
 */
public class Observer extends JPanel implements ActionListener {

    GhostFactory ghostFactory = new SimpleGhostFactory();
    FruitFactory fruitFactory = new SimpleFruitFactory();
    private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private java.lang.Boolean inGame = true;
    private java.lang.Boolean dying = false;
    private final java.lang.Integer BLOCK_SIZE = 24;
    private final java.lang.Integer N_BLOCKS = 15;
    private final java.lang.Integer SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final java.lang.Integer MAX_GHOSTS = 12;
    private final java.lang.Integer MAX_PILLS = 2;
    private final java.lang.Integer MAX_FRUITS = 5;
    private final java.lang.Integer PACMAN_SPEED = 3;
    static java.lang.Integer N_GHOSTS = 6;
    static java.lang.Integer N_PILLS = 2;
    static java.lang.Integer N_FRUITS = 3;
    static java.lang.Integer lives, score;
    static java.lang.Integer[] dx, dy;
    private java.lang.Integer[] ghost_x, ghost_y, ghost_dx, ghost_dy, type, ghostSpeed;
    private java.lang.Integer[] pill_x, pill_y;
    private java.lang.Integer[] fruit_x, fruit_y, fruit_type, fruit_points;
    private Image heart;
    private Image up, down, left, right;
    private java.lang.Integer pacman_x, pacman_y, pacmand_x, pacmand_y;
    private java.lang.Integer req_dx, req_dy;
    java.lang.Boolean pastilla;
    private java.lang.Integer currentLevel = 1; // Inicialmente, estás en el nivel 1
    private java.lang.Integer id_Observer;
    private java.lang.Short[] levelData = {
            19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 24, 16, 16, 16, 20,
            25, 24, 24, 24, 24, 24, 16, 16, 16, 20, 0, 17, 16, 16, 20,
            0,  0,  0,  0,  0,  0, 17, 16, 16, 20, 0, 17, 16, 16, 20,
            19, 18, 18, 18, 18, 18, 16, 16, 16, 20, 0, 17, 16, 24, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 18, 16, 20, 0, 21,
            17, 16, 16, 16, 16, 16, 24, 24, 24, 24, 16, 16, 20, 0, 21,
            17, 16, 16, 16, 16, 20, 0, 0, 0, 0, 17, 16, 20, 0, 21,
            17, 16, 16, 16, 16, 16, 18, 18, 18, 18, 16, 16, 20, 0, 21,
            17, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 16, 20, 0, 21,
            21, 0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 20, 0, 21,
            17, 18, 18, 18, 18, 18, 18, 18, 16, 16, 16, 16, 16, 18, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };

    private final java.lang.Short level2Data[] = {
            19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 24, 24, 24, 16, 20,
            25, 24, 24, 24, 24, 24, 24, 16, 16, 20, 0, 0, 0, 17, 20,
            0,  0,  0,  0,  0,  0, 0, 17, 16, 20, 0, 19, 18, 16, 20,
            19, 18, 22, 0, 19, 18, 18, 16, 16, 20, 0, 17, 16, 24, 20,
            17, 16, 20, 0, 17, 16, 16, 16, 16, 16, 18, 16, 20, 0, 21,
            17, 16, 20, 0, 17, 16, 24, 24, 24, 24, 16, 16, 20, 0, 21,
            17, 16, 16, 18, 16, 20, 0, 0, 0, 0, 17, 16, 20, 0, 21,
            17, 16, 16, 16, 16, 16, 22, 0, 19, 18, 16, 16, 20, 0, 21,
            17, 24, 24, 24, 24, 24, 28, 0, 17, 16, 16, 16, 20, 0, 21,
            21, 0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 20, 0, 21,
            17, 18, 22, 0, 19, 18, 18, 18, 16, 16, 24, 24, 28, 0, 21,
            17, 16, 20, 0, 17, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21,
            17, 16, 16, 18, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 20,
            25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };
    private int points;
    private final java.lang.Integer validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final java.lang.Integer maxSpeed = 6;
    private java.lang.Integer currentSpeed = 3;
    private java.lang.Short[] screenData;
    private Timer timer;
    SocketCliente socketCliente = new SocketCliente();

    /**
     * Constructor de clase Observer
     * @param id
     */
    public Observer(String id) {
        id_Observer = Integer.parseInt(id);
        loadImages();
        initVariables();
        String[] args = new java.lang.String[0];
        socketCliente.main(args);
        setFocusable(true);
        socketCliente.enviarMensajeAlServidor("observador " + id_Observer);
        initGame();
    }

    /**
     * Metodo para cargar las imagenes
     */
    private void loadImages() {
        down = new ImageIcon("images\\down.gif").getImage();
        up = new ImageIcon("images\\up.gif").getImage();
        left = new ImageIcon("images\\left.gif").getImage();
        right = new ImageIcon("images\\right.gif").getImage();
        heart = new ImageIcon("images\\heart.png").getImage();
    }

    /**
     * Metodo para inicializar variables
     */
    private void initVariables() {

        screenData = new java.lang.Short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400, 400);
        ghost_x = new java.lang.Integer[MAX_GHOSTS];
        ghost_dx = new java.lang.Integer[MAX_GHOSTS];
        ghost_y = new java.lang.Integer[MAX_GHOSTS];
        ghost_dy = new java.lang.Integer[MAX_GHOSTS];
        type = new java.lang.Integer[MAX_GHOSTS];
        ghostSpeed = new java.lang.Integer[MAX_GHOSTS];
        dx = new java.lang.Integer[4];
        dy = new java.lang.Integer[4];
        pill_x = new java.lang.Integer[MAX_PILLS];
        pill_y = new java.lang.Integer[MAX_PILLS];
        fruit_x = new java.lang.Integer[MAX_FRUITS];
        fruit_y = new java.lang.Integer[MAX_FRUITS];
        fruit_type = new java.lang.Integer[MAX_FRUITS];
        fruit_points = new java.lang.Integer[MAX_FRUITS];
        pastilla = false;
        timer = new Timer(40, this);
        timer.start();
    }

    /**
     * Metodo que evalua el estado de la partida
     * @param g2d
     */
    private void playGame(Graphics2D g2d) {

        if (dying) {

            death();

        } else {

            movePacman();
            drawPacman(g2d);
            if (pastilla == true) {
                eatGhosts(g2d);
            } else {
                moveGhosts(g2d);
            }

            checkFruits(g2d);
            checkPills(g2d);
            checkMaze();
        }
    }

    /**
     * Metodo que dibuja los parametros iniciales
     * @param g
     */
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);
        String p = "Id: " + id_Observer;
        g.drawString(p, SCREEN_SIZE / 2 + 56, SCREEN_SIZE + 16);

        for (java.lang.Integer i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    /**
     * Metodo que verifica el estado de la matriz
     */
    private void checkMaze() {

        java.lang.Integer i = 0;
        java.lang.Boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {
            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    /**
     * Metodo que resta las vidas cuando se muere
     */
    private void death() {

        lives--;

        if (lives == 0) {
            inGame = false;
            JOptionPane.showMessageDialog(this, "¡Perdiste!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        continueLevel();
    }

    /**
     * Metodo que establece el movimiento de los fantasmas y las colisiones
     * @param g2d
     */
    private void moveGhosts(Graphics2D g2d) {

        java.lang.Integer pos;
        java.lang.Integer count;
        for (java.lang.Integer i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            if (type[i] == 1){
                Ghost blinky = ghostFactory.createBlinky();
                blinky.draw(g2d, ghost_x[i]+1, ghost_y[i]+1);
            } else if (type[i] == 2) {
                Ghost pinky = ghostFactory.createPinky();
                pinky.draw(g2d, ghost_x[i]+1, ghost_y[i]+1);
            } else if (type[i] == 3) {
                Ghost inky = ghostFactory.createInky();
                inky.draw(g2d, ghost_x[i]+1, ghost_y[i]+1);
            } else {
                Ghost clyde = ghostFactory.createClyde();
                clyde.draw(g2d, ghost_x[i]+1, ghost_y[i]+1);
            }

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                dying = true;
            }
        }
    }

    /**
     * Metodo que evalua el estado de las pastillas
     * @param g2d
     */
    private void checkPills(Graphics2D g2d) {

        for (java.lang.Integer i = 0; i < N_PILLS; i++) {

            Pill pill = new Pill();
            pill.draw(g2d, pill_x[i], pill_y [i]);

            if (pacman_x > (pill_x[i] - 12) && pacman_x < (pill_x[i] + 12)
                    && pacman_y > (pill_y[i] - 12) && pacman_y < (pill_y[i] + 12)
                    && inGame) {

                pill_x[i] = -100;
                pill_y[i] = -100;
                pastilla = true;

                socketCliente.enviarMensajeAlServidor("pastilla " + id_Observer);
            }
        }
    }

    /**
     * Metodo que evalua el estado de las frutas
     * @param g2d
     */
    private void checkFruits(Graphics2D g2d) {

        for (java.lang.Integer i = 0; i < N_FRUITS; i++) {

            if (fruit_type[i] == 1){
                Fruit fruit = fruitFactory.crearCherry(fruit_x[i], fruit_y[i], fruit_points[i]);
                fruit.draw(g2d, fruit_x[i], fruit_y[i]);
            } else if (fruit_type[i] == 2) {
                Fruit fruit = fruitFactory.crearBanana(fruit_x[i], fruit_y[i], fruit_points[i]);
                fruit.draw(g2d, fruit_x[i], fruit_y[i]);
            } else {
                Fruit fruit = fruitFactory.crearStrawberry(fruit_x[i], fruit_y[i], fruit_points[i]);
                fruit.draw(g2d, fruit_x[i], fruit_y[i]);
            }

            if (pacman_x > (fruit_x[i] - 12) && pacman_x < (fruit_x[i] + 12)
                    && pacman_y > (fruit_y[i] - 12) && pacman_y < (fruit_y[i] + 12)
                    && inGame) {

                fruit_x[i] = -100;
                fruit_y[i] = -100;
                socketCliente.enviarMensajeAlServidor("fruta " + id_Observer + " " + fruit_points[i]);
            }

        }
    }

    /**
     * Metodo que evalua el estado de los fantasmas cuando pueden ser ddestruidos
     * @param g2d
     */
    private void eatGhosts(Graphics2D g2d) {

        java.lang.Integer pos;
        java.lang.Integer count;
        for (java.lang.Integer i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);

            Ghost blue = ghostFactory.createBlueGhost();
            blue.draw(g2d, ghost_x[i]+1, ghost_y[i]+1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                ghost_x[i] = -100;
                ghost_y[i] = -100;
                socketCliente.enviarMensajeAlServidor("fantasma " + id_Observer);
                pastilla = false;
            }
        }
    }

    /**
     * Metodo que describe el movimiento de pacman
     */
    private void movePacman() {

        java.lang.Integer pos;
        java.lang.Short ch;

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                socketCliente.enviarMensajeAlServidor("puntos " + id_Observer + " 1");
                points ++;
                if (points == 199) {
                    socketCliente.enviarMensajeAlServidor("nivel");
                }
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    /**
     * Metodo que dibuja a pacman
     * @param g2d
     */
    private void drawPacman(Graphics2D g2d) {

        if (req_dx == -1) {
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    /**
     * Metodo que dibuja la matriz
     * @param g2d
     */
    private void drawMaze(Graphics2D g2d) {

        java.lang.Short i = 0;
        java.lang.Integer x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));

                if (currentLevel == 1){
                    if ((levelData[i] == 0)) {
                        g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    }
                } else {
                    if ((level2Data[i] == 0)) {
                        g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }


                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
            }
        }
    }

    /**
     * Metodo que inicializa el juego
     */
    private void initGame() {

        lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 0;
        N_PILLS = 0;
        N_FRUITS = 0;
        currentSpeed = 1;
    }

    /**
     * Metodo que inicializa un nivel
     */
    private void initLevel() {
        if (currentLevel == 1){
            java.lang.Integer i;
            for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
                screenData[i] = levelData[i];
            }
            continueLevel();
        } else {
            java.lang.Integer i;
            for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
                screenData[i] = level2Data[i];
            }
            continueLevel();
        }

    }

    /**
     * Metodo que permite continuar el nivel despues de morir
     */
    private void continueLevel() {

        pacman_x = 7 * BLOCK_SIZE;  //start position
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        dying = false;
    }

    /**
     * Metodo que permite agregar un fantasma al lienzo
     * @param x
     * @param y
     * @param ty
     */

    private void addGhost(int x, int y, int ty) {
        java.lang.Integer dx = 1;

        if (N_GHOSTS < MAX_GHOSTS) {
            ghost_x[N_GHOSTS] = x;
            ghost_y[N_GHOSTS] = y;
            ghost_dy[N_GHOSTS] = 0;
            ghost_dx[N_GHOSTS] = dx;
            type[N_GHOSTS] = ty;
            dx = -dx;
            ghostSpeed[N_GHOSTS] = 1;
            N_GHOSTS++;
        }
    }

    /**
     * Metodo que permite agregar una pastilla al lienzo
     * @param x
     * @param y
     */
    private void addPill(java.lang.Integer x, java.lang.Integer y){
        if (N_PILLS < MAX_PILLS) {
            pill_x[N_PILLS] = x;
            pill_y[N_PILLS] = y;
            N_PILLS++;
        }
    }

    /**
     * Metodo que permite agregar una fruta al lienzo
     * @param ty
     * @param x
     * @param y
     * @param puntaje
     */
    private void addFruit(java.lang.Integer ty, java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje){
        if (N_FRUITS < MAX_FRUITS) {
            fruit_x[N_FRUITS] = x;
            fruit_y[N_FRUITS] = y;
            fruit_type[N_FRUITS] = ty;
            fruit_points[N_FRUITS] = puntaje;

            N_FRUITS++;
        }
    }

    /**
     * Metodo que establece el lienzo en la partida
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);


        playGame(g2d);


        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /**
     * Clase TAdapter que describre el movimiento del Observer
     */
    //controls
    class TAdapter extends KeyAdapter {
        /**
         * Metodo que describe el movimiento del Observer
         * @param command
         */
        public void processConsoleCommand(String command) {
            // Procesa los comandos desde la consola y ajusta el movimiento del pacman según sea necesario
            if (command.equalsIgnoreCase("izquierda")) {
                req_dx = -1;
                req_dy = 0;
            } else if (command.equalsIgnoreCase("derecha")) {
                req_dx = 1;
                req_dy = 0;
            } else if (command.equalsIgnoreCase("arriba")) {
                req_dx = 0;
                req_dy = -1;
            } else if (command.equalsIgnoreCase("abajo")) {
                req_dx = 0;
                req_dy = 1;
            } else if (command.equalsIgnoreCase("iniciar")) {
                if (!inGame) {
                    inGame = true;
                    initGame();
                }
            } else if (command.equalsIgnoreCase("detener")) {
                inGame = false;
            }
        }
    }

    /**
     * Metodo que permite interpretar las estructuras enviadas por el servidor
     * @param command
     */
    private void processConsoleCommand(String command) {
        String[] parts = command.split(" ");
        TAdapter tAdapter = new TAdapter();
        if (command.startsWith("Fantasma ")) {
            if (parts.length == 4) {
                java.lang.Integer x = Integer.parseInt(parts[2]) * BLOCK_SIZE;
                java.lang.Integer y = Integer.parseInt(parts[3]) * BLOCK_SIZE;
                String type = parts[1];
                java.lang.Integer ty = getTypeFromGhostName(type);
                addGhost(x, y, ty);
            }
        } else if (command.startsWith("Velocidad ")) {
            if (parts.length == 2) {
                java.lang.Integer cambioVelocidad = Integer.parseInt(parts[1]);
                modifyGhostSpeed(cambioVelocidad);
            }
        } else if (command.startsWith("Pastilla ")) {
            if (parts.length == 3) {
                java.lang.Integer x = Integer.parseInt(parts[1]) * BLOCK_SIZE;
                java.lang.Integer y = Integer.parseInt(parts[2]) * BLOCK_SIZE;
                addPill(x, y);
            }
        } else if (command.startsWith("Fruta ")) {
            if (parts.length == 5) {
                String type = parts[1].toLowerCase();
                java.lang.Integer x = Integer.parseInt(parts[2]) * BLOCK_SIZE;
                java.lang.Integer y = Integer.parseInt(parts[3]) * BLOCK_SIZE;
                java.lang.Integer puntaje = Integer.parseInt(parts[4]);
                java.lang.Integer ty = getTypeFromFruitName(type);
                addFruit(ty, x, y, puntaje);
            }
        } else if (command.startsWith("nivel")) {
            currentLevel = 2;
            initLevel();
        } else if (command.startsWith("puntaje ")) {
            if (parts.length == 2){
                java.lang.Integer n = Integer.parseInt(parts[1]);
                score = n;
            }
        } else if (command.startsWith("Vida ")) {
            if (parts.length == 2){
                java.lang.Integer n = Integer.parseInt(parts[1]);
                score = n;
                lives++;
                repaint();
            }
        } else if (command.startsWith("ID")) {
            if (parts.length == 2){
                java.lang.Integer n = Integer.parseInt(parts[1]);
                id_Observer = n;
            }
        } else if (command.startsWith("derecha")) {
            tAdapter.processConsoleCommand("derecha");
        } else if (command.startsWith("izquierda")) {
            tAdapter.processConsoleCommand("izquierda");
        } else if (command.startsWith("abajo")) {
            tAdapter.processConsoleCommand("abajo");
        } else if (command.startsWith("arriba")) {
            tAdapter.processConsoleCommand("arriba");
        }
    }

    /**
     * Metodo que permite obtener el tipo de fantasma
     * @param name
     * @return
     */
    private java.lang.Integer getTypeFromGhostName(String name) {
        switch (name) {
            case "Blinky":
                return 1;
            case "Pinky":
                return 2;
            case "Inky":
                return 3;
            default:
                return 4;
        }
    }

    /**
     * Metodo que permite modificar la velocidad del fantasma
     * @param cambioVelocidad
     */
    private void modifyGhostSpeed(int cambioVelocidad) {
        for (java.lang.Integer i = 0; i < N_GHOSTS; i++) {
            ghostSpeed[i] += cambioVelocidad;
        }
        System.out.println("Velocidad de los fantasmas modificada.");
    }

    /**
     * Metodo que permite obtener el tipo de fruta
     * @param name
     * @return
     */
    private int getTypeFromFruitName(String name) {
        switch (name) {
            case "cherry":
                return 1;
            case "banana":
                return 2;
            default:
                return 3;
        }
    }

    /**
     * Clase socket para comunicarse con el servidor
     */
    public class SocketCliente {
        private PrintWriter out; // Declarar el PrintWriter como una variable de instancia

        /**
         * Metodo para envier mensaje al servidor
         * @param mensaje
         */
        public void enviarMensajeAlServidor(java.lang.String mensaje) {
            out.println(mensaje);
        }

        /**
         * Metodo para inicializar el socket y su hilo de lectura
         * @param args
         */
        public void main(java.lang.String[] args) {
            try {
                Socket socket = new Socket("localhost", 12345);  // Conéctate al servidor en el puerto 12345

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

                // Crear un hilo para recibir mensajes del servidor
                Thread receiveThread = new Thread(() -> {
                    try {
                        while (true) {
                            String serverResponse = in.readLine();
                            if (serverResponse == null) {
                                break;
                            }

                            processConsoleCommand(serverResponse);


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                receiveThread.start();

                // Enviar mensajes al servidor
                Thread sendThread = new Thread(() -> {
                    try {
                        String userInput;
                        while (true) {
                            userInput = consoleInput.readLine();
                            out.println(userInput);
                            if (userInput.equalsIgnoreCase("exit")) {
                                break;
                            }
                        }
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                sendThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que permite iterar sobre el estado de la partida
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}