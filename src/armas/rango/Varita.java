package armas.rango;

import armas.Arma;

public class Varita extends Arma {
    public Varita() {
        super("Varita", 18, 15, 8);
    }

    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 5);
    }
}