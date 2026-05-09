package ataques;

import personajes.Personaje;

public class AtaqueAgresivo extends AtaqueDecorator {

    public AtaqueAgresivo(Ataque ataqueDecorado) {
        super(ataqueDecorado);
    }

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return super.ejecutar(atacante, defensor) + 8;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " agresivo";
    }
}
