package code.app;

/**
 * Clase Factory de fantasmas
 */
public class SimpleGhostFactory implements GhostFactory {
    /**
     * Metodo para crear Blinky
     * @return Blinky
     */
    @Override
    public Ghost createBlinky() {
        return new Blinky();
    }

    /**
     * Metodo para crear Pinky
     * @return Pinky
     */
    @Override
    public Ghost createPinky() {
        return new Pinky();
    }

    /**
     * Metodo para crear Inky
     * @return Inky
     */
    @Override
    public Ghost createInky() {
        return new Inky();
    }

    /**
     * Metodo para crear Clyde
     * @return Clyde
     */
    @Override
    public Ghost createClyde() {
        return new Clyde();
    }

    /**
     * Metodo para crear BlueGhost
     * @return BlueGhost
     */
    @Override
    public Ghost createBlueGhost(){return new BlueGhost();}
}

