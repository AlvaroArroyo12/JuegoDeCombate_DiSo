package ataques;

import personajes.Personaje;

//Interfaz del patron Decorator para las acciones de combate.
//La accion basica se decora con modificadores segun atributos y estrategia.

public interface Ataque {

    int ejecutar(Personaje atacante, Personaje defensor);

    String getDescripcion();
}
