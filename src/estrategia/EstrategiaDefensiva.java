package estrategia;

import personajes.Personaje;

public class EstrategiaDefensiva implements EstrategiaCombate {

    @Override
    public int decidirAccion(Personaje yo, Personaje oponente) {
        if (yo.getPorcentajeVida() < 20) return 2; // curar
        if (yo.getPorcentajeVida() < 50) return 1; // defender
        return 0; // atacar si tiene más vida 
    }

    @Override
    public String getNombre() {
        return "Defensiva";
    }
}
