package personajes.tipos.mutantes;

import armas.defensa.Escudo;
import estrategia.EstrategiaCombate;
import personajes.tipos.Mutante;

public class MutanteMar extends Mutante {

    public MutanteMar(EstrategiaCombate estrategia) {
        super("Mutante del Mar", 10, 15, 8, new Escudo(), estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 15; 
    }
}
