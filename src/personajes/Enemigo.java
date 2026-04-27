package personajes;

import armas.Arma;
import estrategia.EstrategiaCombate;

//Clase abstracta que representa a un enemigo, cada enemigo tiene una estrategia 

public abstract class Enemigo extends Personaje {

    private EstrategiaCombate estrategia;

    public Enemigo(String nombre, int fuerza, int resistencia, int agilidad,
                   Arma arma, EstrategiaCombate estrategia) {
        super(nombre, fuerza, resistencia, agilidad, arma);
        this.estrategia = estrategia;
    }

    //Template Method
    public final int decidirSiguienteAccion(Personaje oponente) {
        // Paso 1: preparar turno
        prepararTurno();

        // Paso 2: si el estado no permite actuar, pasar turno
        if (getEstado() != null && !getEstado().puedeActuar()) {
            return 3; //pasar
        }

        // Paso 3: comprobar si necesita curarse
        if (necesitaCurarse()) {
            return 2; //curar
        }

        //Paso 4: delegar en la estrategia asignada
        return estrategia.decidirAccion(this, oponente);
    }

    protected void prepararTurno() {}

    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 20;
    }

    //Getters y Setters

    public EstrategiaCombate getEstrategia() { return estrategia; }
    public void setEstrategia(EstrategiaCombate estrategia) { this.estrategia = estrategia; }

    @Override
    public String toString() {
        String est = (estrategia != null) ? estrategia.getNombre() : "sin estrategia";
        return super.toString() + " | estrategia:" + est;
    }
}
