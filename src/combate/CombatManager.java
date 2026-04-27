package combate;

import personajes.Enemigo;
import personajes.Jugador;
import personajes.Personaje;

import java.util.Random;
import java.util.Scanner;

//Gestiona la logica de combate entre jugador y enemigo
//El GameController delega aqui

public class CombatManager {

    private Scanner scanner;
    private Random random;

    public CombatManager(Scanner scanner) {
        this.scanner = scanner;
        this.random = new Random();
    }

    //Ejecuta un combate por turnos entre el jugador y un enemigo.
    //Devuelve true si gana el jugador, false si pierde.

    public boolean ejecutarCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  COMBATE: " + jugador.getNombre() + " vs " + enemigo.getNombre());
        System.out.println("══════════════════════════════════════");

        //Decidir quien empieza al azar
        boolean turnoJugador = random.nextBoolean();
        System.out.println("Empieza: " + (turnoJugador ? jugador.getNombre() : enemigo.getNombre()));

        int turno = 1;
        while (jugador.estaVivo() && enemigo.estaVivo()) {
            System.out.println("\n--- Turno " + turno + " ---");

            if (turnoJugador) {
                turnoDelJugador(jugador, enemigo);
            } else {
                turnoDelEnemigo(enemigo, jugador);
            }

            turnoJugador = !turnoJugador;
            turno++;
        }

        //Resultado
        if (jugador.estaVivo()) {
            int puntos = 10 + enemigo.getFuerza() + enemigo.getResistencia();
            jugador.sumarPuntos(puntos);
            System.out.println("\n" + jugador.getNombre() + " ha ganado el combate! (+" + puntos + " pts)");
            return true;
        } else {
            System.out.println("\n" + enemigo.getNombre() + " ha derrotado a " + jugador.getNombre() + "...");
            return false;
        }
    }

    //Muestra las opciones al jugador y ejecuta la accion elegida.
    private void turnoDelJugador(Jugador jugador, Enemigo enemigo) {
        //Aplicar efecto del estado actual
        if (jugador.getEstado() != null) {
            jugador.getEstado().aplicarEfecto();
            if (!jugador.getEstado().puedeActuar()) {
                System.out.println(jugador.getNombre() + " no puede actuar! (" + jugador.getEstado().getNombre() + ")");
                return;
            }
        }

        System.out.println("\n" + jugador);
        System.out.println("Enemigo: " + enemigo);
        System.out.println("\nElige accion:");
        System.out.println("  1) Atacar");
        System.out.println("  2) Defender");
        System.out.println("  3) Curar");
        System.out.println("  4) Reparar arma");
        System.out.println("  5) Pasar turno");

        int opcion = leerOpcion(1, 5);

        switch (opcion) {
            case 1:
                ejecutarAtaque(jugador, enemigo);
                break;
            case 2:
                ejecutarDefensa(jugador);
                break;
            case 3:
                ejecutarCuracion(jugador);
                break;
            case 4:
                ejecutarReparacion(jugador);
                break;
            case 5:
                System.out.println(jugador.getNombre() + " pasa el turno.");
                break;
        }
    }

    //El enemigo decide su accion usando el Template Method + Strategy.
    private void turnoDelEnemigo(Enemigo enemigo, Jugador jugador) {
        // Aplicar efecto del estado actual
        if (enemigo.getEstado() != null) {
            enemigo.getEstado().aplicarEfecto();
            if (!enemigo.getEstado().puedeActuar()) {
                System.out.println(enemigo.getNombre() + " no puede actuar! (" + enemigo.getEstado().getNombre() + ")");
                return;
            }
        }

        int accion = enemigo.decidirSiguienteAccion(jugador);

        switch (accion) {
            case 0:
                ejecutarAtaque(enemigo, jugador);
                break;
            case 1:
                ejecutarDefensa(enemigo);
                break;
            case 2:
                ejecutarCuracion(enemigo);
                break;
            default:
                System.out.println(enemigo.getNombre() + " pasa el turno.");
                break;
        }
    }

    //Acciones de combate

    private void ejecutarAtaque(Personaje atacante, Personaje defensor) {
        Calculador calc = Calculador.getInstance();
        int danio = calc.calcularDanio(atacante, defensor);

        // Gastar uso del arma
        if (atacante.getArma() != null && atacante.getArma().getUtilidad() > 0) {
            atacante.getArma().setUtilidad(atacante.getArma().getUtilidad() - 1);
        }

        defensor.recibirDanio(danio);
        System.out.println(atacante.getNombre() + " ataca a " + defensor.getNombre()
                + " con " + (atacante.getArma() != null ? atacante.getArma().getNombre() : "punos")
                + " -> " + danio + " de danio"
                + " [" + defensor.getNombre() + ": " + defensor.getVida() + "/" + defensor.getVidaMaxima() + " vida]");
    }

    private void ejecutarDefensa(Personaje personaje) {
        Calculador calc = Calculador.getInstance();
        int bonus = calc.calcularBonusDefensa(personaje);
        personaje.setResistencia(personaje.getResistencia() + bonus);
        System.out.println(personaje.getNombre() + " se defiende (+" + bonus + " resistencia)");
    }

    private void ejecutarCuracion(Personaje personaje) {
        Calculador calc = Calculador.getInstance();
        int curacion = calc.calcularCuracion(personaje);
        int vidaAntes = personaje.getVida();
        personaje.curar(curacion);
        int vidaDespues = personaje.getVida();
        System.out.println(personaje.getNombre() + " se cura +" + (vidaDespues - vidaAntes)
                + " vida [" + vidaDespues + "/" + personaje.getVidaMaxima() + "]");
    }

    private void ejecutarReparacion(Personaje personaje) {
        if (personaje.getArma() != null) {
            personaje.getArma().reparar();
            System.out.println(personaje.getNombre() + " repara " + personaje.getArma().getNombre()
                    + " [usos: " + personaje.getArma().getUtilidad() + "]");
        } else {
            System.out.println(personaje.getNombre() + " no tiene arma que reparar.");
        }
    }

    //Utilidades
    private int leerOpcion(int min, int max) {
        int opcion = -1;
        while (opcion < min || opcion > max) {
            System.out.print("> ");
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            if (opcion < min || opcion > max) {
                System.out.println("Opcion no valida. Elige entre " + min + " y " + max + ".");
            }
        }
        return opcion;
    }
}