package personajes.estados.types;

import personajes.estados.Contexto;
import personajes.estados.EstadoPersonaje;

public class Vivo implements EstadoPersonaje {

    private final Contexto contexto;

    public Vivo(Contexto contexto) {
        this.contexto = contexto;
    }

    @Override
    public void evaluarTransicion() {
        int pct = contexto.getPorcentajeVida();
        if (pct == 0)       contexto.setEstadoPersonaje(contexto.getMuerto());
        else if (pct <= 60) contexto.setEstadoPersonaje(contexto.getGaslighteado());
    }

    @Override
    public void aplicarEfecto() {}

    @Override
    public boolean puedeActuar() { return true; }

    @Override
    public String getNombre() { return "Vivo"; }
}
