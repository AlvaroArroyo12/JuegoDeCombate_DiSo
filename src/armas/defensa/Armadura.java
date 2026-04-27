package armas.defensa;

import armas.Arma;

public class Armadura extends Arma {
    public Armadura() {
        super("Armadura", 2, 25, 0);
    }

    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 4);
    }
}