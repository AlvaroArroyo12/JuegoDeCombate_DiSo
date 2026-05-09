package ataques;

import combate.Calculador;
import personajes.Personaje;

public class AtaqueBasico implements Ataque {

    @Override
    public int ejecutar(Personaje atacante, Personaje defensor) {
        return Calculador.getInstance().calcularDanio(atacante, defensor);
    }

    @Override
    public String getDescripcion() {
        return "ataque basico";
    }
}
