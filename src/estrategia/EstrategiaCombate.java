package estrategia;

import personajes.Personaje;

//Interfaz del patrón Strategy
//Define una estrategia de combate que adopta un enemigo

public interface EstrategiaCombate {

    int decidirAccion(Personaje yo, Personaje oponente);

    //Nombre de la estrategia para mostrar por consola. 
    String getNombre();
}