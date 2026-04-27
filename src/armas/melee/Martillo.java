package armas.melee;

import armas.Arma;

public class Martillo extends Arma {
    public Martillo() {
        super("Martillo", 30, 6, 0);
    }

    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 2);
    }
}