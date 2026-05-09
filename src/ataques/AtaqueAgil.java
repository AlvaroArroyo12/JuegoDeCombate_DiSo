package ataques;

import personajes.Personaje;

public class AtaqueAgil extends AtaqueDecorator {

    public AtaqueAgil(Ataque ataqueDecorado) {
        super(ataqueDecorado);
    }

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return super.ejecutar(atacante, defensor) + 5;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " agil";
    }
}
