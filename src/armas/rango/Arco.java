package armas.rango;

import armas.Arma;

public class Arco extends Arma {
    public Arco() {
        super("Arco", 15, 12, 10);
    }

    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 4);
    }
}