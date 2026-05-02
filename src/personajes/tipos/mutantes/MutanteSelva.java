package personajes.tipos.mutantes;

import estrategia.EstrategiaCombate;
import personajes.tipos.Mutante;

public class MutanteSelva extends Mutante {

    public MutanteSelva(EstrategiaCombate estrategia) {
        super("Mutante de la Selva", 11, 9, 12, null, estrategia); 
    }

    @Override
    protected void prepararTurno() {
        if (estaVivo()) {
            curar(5); 
            System.out.println(getNombre() + " absorbe energia de la selva (+5 vida).");
        }
    }
}
