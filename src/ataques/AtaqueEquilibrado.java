package ataques;

import personajes.Personaje;

public class AtaqueEquilibrado extends AtaqueDecorator {

    public AtaqueEquilibrado(Ataque ataqueDecorado) {
        super(ataqueDecorado);
    }

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return super.ejecutar(atacante, defensor) + 4;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " equilibrado";
    }
}
