package personajes.tipos.mutantes;

import estrategia.EstrategiaCombate;
import personajes.tipos.Mutante;

public class MutanteMar extends Mutante {

    public MutanteMar(EstrategiaCombate estrategia) {
        super("Mutante del Mar", 10, 15, 8, null, estrategia); 
    }

    @Override
    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 15; 
    }
}
