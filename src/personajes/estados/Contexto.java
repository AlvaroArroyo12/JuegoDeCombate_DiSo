package personajes.estados;

import personajes.Personaje;
import personajes.estados.types.Gaslighteado;
import personajes.estados.types.Muerto;
import personajes.estados.types.Vivo;

public class Contexto {

    private EstadoPersonaje estadoPersonaje;
    private final Personaje personaje;

    private final Vivo vivoState;
    private final Gaslighteado gaslighteadoState;
    private final Muerto muertoState;

    public Contexto(Personaje personaje) {
        this.personaje         = personaje;
        this.vivoState         = new Vivo(this);
        this.gaslighteadoState = new Gaslighteado(this);
        this.muertoState       = new Muerto();
        this.estadoPersonaje   = vivoState;
    }

    /**
     * Punto de entrada para evaluar transiciones.
     * Delega al estado actual; la logica de transicion vive en cada estado.
     */
    public void logicaTransicionDeEstados() {
        estadoPersonaje.evaluarTransicion();
    }

    //Cambia de estado e imprime el mensaje solo si realmente cambia. 
    public void setEstadoPersonaje(EstadoPersonaje nuevo) {
        if (nuevo != estadoPersonaje) {
            estadoPersonaje = nuevo;
            System.out.println("  [Estado] " + personaje.getNombre() + " -> " + nuevo.getNombre());
        }
    }

    public EstadoPersonaje getEstadoPersonaje() { return estadoPersonaje; }

    public int    getPorcentajeVida()  { return personaje.getPorcentajeVida(); }
    public String getNombrePersonaje() { return personaje.getNombre(); }

    public Vivo         getVivo()         { return vivoState; }
    public Gaslighteado getGaslighteado() { return gaslighteadoState; }
    public Muerto       getMuerto()       { return muertoState; }
}
