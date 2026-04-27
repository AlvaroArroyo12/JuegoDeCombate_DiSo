package armas.rango;

import armas.Arma;

public class Bomba extends Arma {
    public Bomba() {
        super("Bomba", 35, 3, 0);
    }

    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 1);
    }
}