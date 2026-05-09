package ataques;

import personajes.Personaje;

public class AtaquePoderoso extends AtaqueDecorator {

    public AtaquePoderoso(Ataque ataqueDecorado) {
        super(ataqueDecorado);
    }

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return super.ejecutar(atacante, defensor) + 10;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " poderoso";
    }
}
