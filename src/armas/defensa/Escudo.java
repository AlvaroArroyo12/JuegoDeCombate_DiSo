package armas.defensa;

import armas.Arma;

public class Escudo extends Arma {
    public Escudo() {
        super("Escudo", 5, 20, 0);
    }

    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 5);
    }
}