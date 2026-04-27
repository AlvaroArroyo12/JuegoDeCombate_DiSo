package armas.melee;

import armas.Arma;

public class Hacha extends Arma {
    public Hacha() {
        super("Hacha", 25, 8, 2);
    }
    
    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 2);
    }
}