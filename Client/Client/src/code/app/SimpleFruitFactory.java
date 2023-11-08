package code.app;

/**
 * Clase Factory de Frutas
 */
public class SimpleFruitFactory implements FruitFactory{
    /**
     * Metodo para crear Cherry
     * @param x
     * @param y
     * @param puntaje
     * @return Cherry
     */
    @Override
    public Fruit crearCherry(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje) {
        return new Cherry(x, y, puntaje);
    }

    /**
     * Metodo para crear Banana
     * @param x
     * @param y
     * @param puntaje
     * @return Banana
     */
    @Override
    public Fruit crearBanana(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje) {
        return new Banana(x, y, puntaje);
    }

    /**
     * Metodo para crear Strawberry
     * @param x
     * @param y
     * @param puntaje
     * @return Strawberry
     */
    @Override
    public Fruit crearStrawberry(java.lang.Integer x, java.lang.Integer y, java.lang.Integer puntaje) {
        return new Strawberry(x, y, puntaje);
    }
}
