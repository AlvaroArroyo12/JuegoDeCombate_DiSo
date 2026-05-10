package combate;

import personajes.Personaje;

import java.util.Random;

//SINGLETON, unica instancia encargada de calcular el resultado de los ataques
//La formula tiene en cuenta fuerza, arma, agilidad y resistencia
public class Calculador {

    private static Calculador instancia;
    private Random random;

    private Calculador() {
        this.random = new Random();
    }

    public static Calculador getInstance() {
        if (instancia == null) {
            instancia = new Calculador();
        }
        return instancia;
    }

    /*
      Calcula el daño de un ataque.
      Formula:
        danioBase = fuerza + danioArma
        bonusAgilidad = agilidad * 0.15
        reduccion = resistencia del defensor * 0.5
        variacion = +/- 10% aleatorio
        danioFinal = max(1, resultado) -> siempre hace al menos 1
     */
    
    public int calcularDanio(Personaje atacante, Personaje defensor) {
        int fuerza = atacante.getFuerzaEfectiva();
        int danioArma = 0;
        if (atacante.getArma() != null && atacante.getArma().getUtilidad() > 0) {
            danioArma = atacante.getArma().getDanio();
        }

        double danioBase = fuerza + danioArma;
        double bonusAgilidad = atacante.getAgilidad() * 0.15;
        double reduccion = defensor.getResistencia() * 0.5;

        double danioSinVariacion = danioBase + bonusAgilidad - reduccion;

        // Variacion aleatoria del +/- 10%
        double factor = 0.9 + (random.nextDouble() * 0.2);
        int danioFinal = (int) Math.round(danioSinVariacion * factor);

        return Math.max(1, danioFinal);
    }

    //Calcula la cantidad de curacion de un personaje.
    //Personajes mas resistentes se curan mejor.
    
    public int calcularCuracion(Personaje personaje) {
        int base = 15;
        int bonus = (int) (personaje.getResistencia() * 0.2);
        return base + bonus;
    }

    //Calcula el bonus de defensa temporal al elegir defender.
    public int calcularBonusDefensa(Personaje personaje) {
        return 3 + (int) (personaje.getResistencia() * 0.1);
    }
}