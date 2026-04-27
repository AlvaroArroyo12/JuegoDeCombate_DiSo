package personajes;
import armas.Arma;

//Clase del personaje controlado por el usuario
//El usuario elige nombre, atributos y arma antes de la partida

public class Jugador extends Personaje {

    private int puntuacion;

    public Jugador(String nombre, int fuerza, int resistencia, int agilidad, Arma arma) {
        super(nombre, fuerza, resistencia, agilidad, arma);
        this.puntuacion = 0;
    }

    //Suma puntos al ganar un combate.
    public void sumarPuntos(int puntos) {
        if (puntos > 0) {
            this.puntuacion += puntos;
        }
    }

    public int getPuntuacion() { return puntuacion; }

    @Override
    public String toString() {
        return super.toString() + " | pts:" + puntuacion;
    }
}
