package personajes.estados;

/**
 * Interfaz del patron State.
 * Los estados concretos (Vivo, Herido, Paralizado, Muerto) implementan esta interfaz
 * y deciden en evaluarTransicion() a que estado pasar segun el porcentaje de vida.
 */
public interface EstadoPersonaje {

    //Evalua si el personaje debe cambiar de estado y lo hace si procede. 
    void evaluarTransicion();

    //Aplica efectos propios del estado al inicio de cada turno. 
    void aplicarEfecto();

    // Devuelve true si el personaje puede realizar acciones en este estado. 
    boolean puedeActuar();

    // Nombre del estado para mostrar por consola. 
    String getNombre();

    /**
     * Modificador de fuerza efectiva para este estado.
     * Por defecto no modifica nada; Herido lo sobreescribe para reducirla.
     */
    default int modificarFuerza(int fuerzaBase) {
        return fuerzaBase;
    }
}
