package estrategia;

import personajes.Personaje;

public class EstrategiaAgresiva implements EstrategiaCombate {

    @Override
    public int decidirAccion(Personaje yo, Personaje oponente) {
        if (yo.getPorcentajeVida() < 20) return 2; // curar 
        return 0; // atacar siempre
    }

    @Override
    public String getNombre() {
        return "Agresiva";
    }
}
