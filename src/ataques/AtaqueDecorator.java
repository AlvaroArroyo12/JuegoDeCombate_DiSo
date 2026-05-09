package ataques;

import personajes.Personaje;

public abstract class AtaqueDecorator implements Ataque {

    protected Ataque ataqueDecorado;

    public AtaqueDecorator(Ataque ataqueDecorado) {
        this.ataqueDecorado = ataqueDecorado;
    }

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return ataqueDecorado.ejecutar(atacante, defensor);
    }

    @Override
    public String getDescripcion() {
        return ataqueDecorado.getDescripcion();
    }
}
