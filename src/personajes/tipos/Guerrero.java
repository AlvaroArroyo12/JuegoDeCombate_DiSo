package personajes.tipos;

import armas.Arma;
import estrategia.EstrategiaCombate;
import personajes.Enemigo;

//Tipo base Guerrero (fuerte y resistente pero lento)
public abstract class Guerrero extends Enemigo {

    public Guerrero(String nombre, int fuerza, int resistencia, int agilidad,
                    Arma arma, EstrategiaCombate estrategia) {
        super(nombre, fuerza, resistencia, agilidad, arma, estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        //Los guerreros aguantan mas antes de curarse
        return getPorcentajeVida() < 15;
    }
}