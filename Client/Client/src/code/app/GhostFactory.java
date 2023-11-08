package code.app;

/**
 * Interfaz de la clase fabrica de fantasmas
 */
public interface GhostFactory {
    /**
     * Metodo para crear fantasma Blinky
     * @return
     */
    Ghost createBlinky();

    /**
     * Metodo para crear fantasma Pinky
     * @return
     */
    Ghost createPinky();

    /**
     * Metodo para crear fantasma Inky
     * @return
     */
    Ghost createInky();

    /**
     * Metodo para crear Fantasma Clyde
     * @return
     */
    Ghost createClyde();

    /**
     * Metodo para crear BlueGhost
     * @return
     */
    Ghost createBlueGhost();
}
