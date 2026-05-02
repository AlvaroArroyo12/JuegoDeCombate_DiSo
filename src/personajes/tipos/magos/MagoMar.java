package personajes.tipos.magos;

import armas.rango.Varita;
import estrategia.EstrategiaCombate;
import personajes.tipos.Mago;

public class MagoMar extends Mago {

    public MagoMar(EstrategiaCombate estrategia) {
        super("Mago del Mar", 12, 7, 10, new Varita(), estrategia);
    }
}
