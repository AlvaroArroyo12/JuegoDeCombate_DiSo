package personajes.tipos;

import armas.Arma;
import estrategia.EstrategiaCombate;
import personajes.Enemigo;

//Tipo base Mago (agil pero poco resistente)
public abstract class Mago extends Enemigo {

    public Mago(String nombre, int fuerza, int resistencia, int agilidad,
                Arma arma, EstrategiaCombate estrategia) {
        super(nombre, fuerza, resistencia, agilidad, arma, estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        //Los magos se curan antes 
        return getPorcentajeVida() < 30;
    }
}