package personajes.estados;

/**
 * Interfaz del patron State.
 * Define el contrato para los distintos estados del personaje durante el combate.
 * Los estados concretos (Activo, Herido, Paralizado, Muerto...) implementan esta interfaz.
 */
public interface EstadoPersonaje {

    /** Evalua si el personaje debe cambiar a otro estado segun su vida u otros factores. */
    void evaluarTransicion();

    /** Aplica los efectos propios de este estado (ej: paralizado no puede atacar). */
    void aplicarEfecto();

    /** Devuelve true si el personaje puede realizar acciones en este estado. */
    boolean puedeActuar();

    /** Nombre del estado para mostrar por consola. */
    String getNombre();
}