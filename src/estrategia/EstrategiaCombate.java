package estrategia;

import personajes.Personaje;

//Define una estrategia de combate que adopta un enemigo
public interface EstrategiaCombate {

    int decidirAccion(Personaje yo, Personaje oponente);

    String getNombre();
}