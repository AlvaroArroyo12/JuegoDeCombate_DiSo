package personajes.tipos.mutantes;

import armas.defensa.Armadura;
import estrategia.EstrategiaCombate;
import personajes.tipos.Mutante;

public class MutanteDesierto extends Mutante {

    public MutanteDesierto(EstrategiaCombate estrategia) {
        super("Mutante del Desierto", 14, 11, 9, new Armadura(), estrategia);
    }

    @Override
    protected void prepararTurno() {
        if (estaVivo()) {
            curar(1); 
            System.out.println(getNombre() + " se regenera lentamente bajo el sol (+1 vida).");
        }
    }
}
