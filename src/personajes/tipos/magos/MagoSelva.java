package personajes.tipos.magos;

import armas.rango.Varita;
import estrategia.EstrategiaCombate;
import personajes.tipos.Mago;

public class MagoSelva extends Mago {

    public MagoSelva(EstrategiaCombate estrategia) {
        super("Mago de la Selva", 9, 6, 14, new Varita(), estrategia);
    }

    @Override
    protected boolean necesitaCurarse() {
        return getPorcentajeVida() < 35; 
    }
}
