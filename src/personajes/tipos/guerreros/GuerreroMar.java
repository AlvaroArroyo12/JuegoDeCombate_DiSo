package personajes.tipos.guerreros;

import armas.melee.Martillo;
import estrategia.EstrategiaCombate;
import personajes.tipos.Guerrero;

public class GuerreroMar extends Guerrero {

    public GuerreroMar(EstrategiaCombate estrategia) {
        super("Guerrero del Mar", 16, 14, 6, new Martillo(), estrategia);
    }
}
