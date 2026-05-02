package personajes.tipos.guerreros;

import armas.melee.Hacha;
import estrategia.EstrategiaCombate;
import personajes.tipos.Guerrero;

public class GuerreroDesierto extends Guerrero {

    public GuerreroDesierto(EstrategiaCombate estrategia) {
        super("Guerrero del Desierto", 14, 12, 9, new Hacha(), estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 10; 
    }
}
