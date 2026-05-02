package personajes.tipos.magos;

import armas.rango.Varita;
import estrategia.EstrategiaCombate;
import personajes.tipos.Mago;

public class MagoDesierto extends Mago {

    public MagoDesierto(EstrategiaCombate estrategia) {
        super("Mago del Desierto", 14, 5, 9, new Varita(), estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 20; 
    }
}
