package combate;

import personajes.Enemigo;
import personajes.Jugador;
import personajes.Personaje;

import ataques.Ataque;
import ataques.AtaqueAgil;
import ataques.AtaqueAgresivo;
import ataques.AtaqueBasico;
import ataques.AtaqueDefensivo;
import ataques.AtaqueEquilibrado;
import ataques.AtaquePoderoso;

import estrategia.EstrategiaAgresiva;
import estrategia.EstrategiaDefensiva;
import estrategia.EstrategiaEquilibrada;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

//Gestiona la logica de combate entre jugador y enemigo. El GameController delega aquí.
public class CombatManager {

    private Scanner scanner;
    private Random random;
    //Bonus de defensa pendiente de revertir al inicio del siguiente turno de cada personaje.
    private final Map<Personaje, Integer> bonusDefensaActivo = new HashMap<>();

    public CombatManager(Scanner scanner) {
        this.scanner = scanner;
        this.random = new Random();
    }

    //Ejecuta un combate por turnos. Devuelve true si gana el jugador.
    public boolean ejecutarCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  COMBATE: " + jugador.getNombre() + " vs " + enemigo.getNombre());
        System.out.println("══════════════════════════════════════");

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

    //El jugador elige su acción. Se evalua el estado al inicio del turno.
    private void turnoDelJugador(Jugador jugador, Enemigo enemigo) {
        restaurarDefensa(jugador);
        jugador.evaluarEstadoTurno();
        jugador.getEstado().aplicarEfecto();
        if (!jugador.getEstado().puedeActuar()) {
            System.out.println(jugador.getNombre() + " no puede actuar! (" + jugador.getEstado().getNombre() + ")");
            return;
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
            case 1: ejecutarAtaque(jugador, enemigo);  break;
            case 2: ejecutarDefensa(jugador);           break;
            case 3: ejecutarCuracion(jugador);          break;
            case 4: ejecutarReparacion(jugador);        break;
            case 5: System.out.println(jugador.getNombre() + " pasa el turno."); break;
        }
    }

    //El enemigo delega en decidirSiguienteAccion(), que ya gestiona estado y estrategia.
    private void turnoDelEnemigo(Enemigo enemigo, Jugador jugador) {
        restaurarDefensa(enemigo);
        int accion = enemigo.decidirSiguienteAccion(jugador);

        switch (accion) {
            case 0: ejecutarAtaque(enemigo, jugador); break;
            case 1: ejecutarDefensa(enemigo); break;
            case 2: ejecutarCuracion(enemigo); break;
            default: System.out.println(enemigo.getNombre() + " pasa el turno."); break;
        }
    }

    private Ataque construirAtaque(Personaje atacante) {
        Ataque ataque = new AtaqueBasico();

        if (atacante.getFuerza() >= 15) {
            ataque = new AtaquePoderoso(ataque);
        }
        if (atacante.getAgilidad() >= 12) {
            ataque = new AtaqueAgil(ataque);
        }

        if (atacante instanceof Enemigo) {
            Enemigo enemigo = (Enemigo) atacante;
            if      (enemigo.getEstrategia() instanceof EstrategiaAgresiva)    ataque = new AtaqueAgresivo(ataque);
            else if (enemigo.getEstrategia() instanceof EstrategiaDefensiva)   ataque = new AtaqueDefensivo(ataque);
            else if (enemigo.getEstrategia() instanceof EstrategiaEquilibrada) ataque = new AtaqueEquilibrado(ataque);
        }
        return ataque;
    }

    private void ejecutarAtaque(Personaje atacante, Personaje defensor) {
        Ataque ataque = construirAtaque(atacante);
        int danio = ataque.ejecutar(atacante, defensor);

        if (atacante.getArma() != null && atacante.getArma().getUtilidad() > 0) {
            atacante.getArma().setUtilidad(atacante.getArma().getUtilidad() - 1);
        }

        defensor.recibirDanio(danio);
        System.out.println(atacante.getNombre() + " realiza " + ataque.getDescripcion()
                + " contra " + defensor.getNombre()
                + " con " + (atacante.getArma() != null ? atacante.getArma().getNombre() : "punos")
                + " -> " + danio + " de danio"
                + " [" + defensor.getNombre() + ": " + defensor.getVida() + "/" + defensor.getVidaMaxima() + " vida]");
    }

    private void restaurarDefensa(Personaje personaje) {
        Integer bonus = bonusDefensaActivo.remove(personaje);
        if (bonus != null) {
            personaje.setResistencia(personaje.getResistencia() - bonus);
        }
    }

    private void ejecutarDefensa(Personaje personaje) {
        Calculador calc = Calculador.getInstance();
        int bonus = calc.calcularBonusDefensa(personaje);
        personaje.setResistencia(personaje.getResistencia() + bonus);
        bonusDefensaActivo.merge(personaje, bonus, Integer::sum);
        System.out.println(personaje.getNombre() + " se defiende (+" + bonus + " resistencia temporal)");
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
