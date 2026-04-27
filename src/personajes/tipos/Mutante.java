package personajes.tipos;

import armas.Arma;
import estrategia.EstrategiaCombate;
import personajes.Enemigo;

//Tipo base Mutante (se cura antes de cada turno)

public abstract class Mutante extends Enemigo {

    public Mutante(String nombre, int fuerza, int resistencia, int agilidad,
                   Arma arma, EstrategiaCombate estrategia) {
        super(nombre, fuerza, resistencia, agilidad, arma, estrategia);
    }

    @Override
    protected void prepararTurno() {
        if (estaVivo()) {
            curar(3);
        }
    }
}