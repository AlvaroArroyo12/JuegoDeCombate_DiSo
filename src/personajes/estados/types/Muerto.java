package personajes.estados.types;

import personajes.estados.EstadoPersonaje;

public class Muerto implements EstadoPersonaje {

    @Override
    public void evaluarTransicion() {
        // Estado final: un personaje muerto no vuelve a otros estados durante el combate.
    }

    @Override
    public void aplicarEfecto() {}

    @Override
    public boolean puedeActuar() { 
        return false; 
    }

    @Override
    public String getNombre() { 
        return "Muerto"; 
    }
}
