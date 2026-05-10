package personajes;

import armas.Arma;
import estrategia.EstrategiaCombate;

// Clase abstracta que representa a un enemigo; cada enemigo tiene una estrategia de combate.
public abstract class Enemigo extends Personaje {

    private EstrategiaCombate estrategia;

    public Enemigo(String nombre, int fuerza, int resistencia, int agilidad,
                   Arma arma, EstrategiaCombate estrategia) {
        super(nombre, fuerza, resistencia, agilidad, arma);
        this.estrategia = estrategia;
    }

    // Template Method: orquesta la decision de accion del enemigo cada turno. 
    public final int decidirSiguienteAccion(Personaje oponente) {
        prepararTurno();
        evaluarEstadoTurno();

        getEstado().aplicarEfecto();
        if (!getEstado().puedeActuar()) {
            return 3; 
        }

        if (necesitaCurarse()) {
            return 2; 
        }

        return estrategia.decidirAccion(this, oponente);
    }

    protected void prepararTurno() {}

    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 20;
    }

    public EstrategiaCombate getEstrategia() { 
        return estrategia; 
    }
    public void setEstrategia(EstrategiaCombate estrategia) { 
        this.estrategia = estrategia; 
    }

    @Override
    public String toString() {
        String est = (estrategia != null) ? estrategia.getNombre() : "sin estrategia";
        return super.toString() + " | estrategia:" + est;
    }
}
