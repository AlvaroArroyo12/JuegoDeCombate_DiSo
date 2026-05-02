package personajes.tipos.guerreros;

import armas.melee.Espada;
import estrategia.EstrategiaCombate;
import personajes.tipos.Guerrero;

public class GuerreroSelva extends Guerrero {

    public GuerreroSelva(EstrategiaCombate estrategia) {
        super("Guerrero de la Selva", 12, 8, 15, new Espada(), estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 25; 
    }
}
