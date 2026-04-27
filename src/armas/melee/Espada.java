package armas.melee;

import armas.Arma;

public class Espada extends Arma{
    public Espada(){
        super ("Espada", 20, 10, 5);
    }
    
    @Override
    public void reparar() {
        setUtilidad(getUtilidad() + 3);
    }
}
