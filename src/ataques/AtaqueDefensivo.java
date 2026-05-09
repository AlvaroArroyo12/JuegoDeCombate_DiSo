package ataques;

import personajes.Personaje;

public class AtaqueDefensivo extends AtaqueDecorator {

    public AtaqueDefensivo(Ataque ataqueDecorado) {
        super(ataqueDecorado);
    }

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return Math.max(1, super.ejecutar(atacante, defensor) - 3);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " defensivo";
    }
}
