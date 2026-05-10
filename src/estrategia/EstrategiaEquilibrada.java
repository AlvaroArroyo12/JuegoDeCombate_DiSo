package estrategia;

import personajes.Personaje;

/**
 * Estrategia equilibrada: defiende si esta tocado o el oponente esta fuerte,
 * y ataca en caso contrario.
 */
public class EstrategiaEquilibrada implements EstrategiaCombate {

    @Override
    public int decidirAccion(Personaje yo, Personaje oponente) {
        int miVida = yo.getPorcentajeVida();
        int vidaOponente = oponente.getPorcentajeVida();
        if (miVida < 45 || vidaOponente > 75) return 1; // defender
        return 0; // atacar
    }

    @Override
    public String getNombre() {
        return "Equilibrada";
    }
}
