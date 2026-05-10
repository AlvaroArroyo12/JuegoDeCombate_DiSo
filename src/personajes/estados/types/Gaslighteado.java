package personajes.estados.types;

import personajes.estados.Contexto;
import personajes.estados.EstadoPersonaje;

public class Gaslighteado implements EstadoPersonaje {

    private final Contexto contexto;

    public Gaslighteado(Contexto contexto) {
        this.contexto = contexto;
    }

    @Override
    public void evaluarTransicion() {
        int pct = contexto.getPorcentajeVida();
        if (pct == 0)      contexto.setEstadoPersonaje(contexto.getMuerto());
        else if (pct > 60) contexto.setEstadoPersonaje(contexto.getVivo());
    }

    @Override
    public void aplicarEfecto() {
        System.out.println("  [Estado] El personaje está gaslighteado y actua confundido.");
    }

    @Override
    public boolean puedeActuar() { return true; }

    @Override
    public String getNombre() { return "Gaslighteado"; }

    /** Mientras está gaslighteado, la fuerza efectiva baja un 20%. */
    @Override
    public int modificarFuerza(int fuerzaBase) {
        return (int) (fuerzaBase * 0.8);
    }
}
