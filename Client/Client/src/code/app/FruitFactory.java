package code.app;

/**
 * Esta es la interfaz de clase de fabrica para las frutas
 */
public interface FruitFactory {
    /**
     * Metodo para crear una fruta de tipo cherry
     * @param x
     * @param y
     * @param puntaje
     * @return
     */
    Fruit crearCherry(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje);

    /**
     * Metodo para crear una fruta de tipo banana
     * @param x
     * @param y
     * @param puntaje
     * @return
     */
    Fruit crearBanana(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje);

    /**
     * Metodo para crear una fruta de tipo strawberry
     * @param x
     * @param y
     * @param puntaje
     * @return
     */
    Fruit crearStrawberry(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje);
}
