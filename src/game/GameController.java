package game;

import armas.Arma;
import armas.melee.*;
import armas.rango.*;
import armas.defensa.*;
import combate.CombatManager;
import mundos.MundoFactory;
import personajes.Enemigo;
import personajes.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Patron Facade + Singleton.
 * Punto de entrada unico al juego. Oculta la complejidad de los subsistemas
 * (creacion de personajes, gestion de mundos, combates) tras una interfaz simple.
 *
 * Tambien es Singleton: solo puede existir una instancia del controlador.
 */
public class GameController {

    private static GameController instancia;

    private Scanner scanner;
    private Jugador jugador;
    private CombatManager combatManager;
    private List<MundoFactory> mundos;
    private boolean juegoActivo;

    private GameController() {
        this.scanner = new Scanner(System.in);
        this.combatManager = new CombatManager(scanner);
        this.mundos = new ArrayList<>();
        this.juegoActivo = false;
    }

    public static GameController getInstance() {
        if (instancia == null) {
            instancia = new GameController();
        }
        return instancia;
    }

    //Inicia el juego completo: creacion de personaje, mundos y combates.
    public void iniciarJuego() {
        System.out.println("========================================");
        System.out.println("          JUEGO DE COMBATE              ");
        System.out.println("========================================");

        crearJugador();
        juegoActivo = true;

        for (int i = 0; i < mundos.size() && juegoActivo; i++) {
            jugarMundo(mundos.get(i), i + 1);
        }

        mostrarResultadoFinal();
        cerrar();
    }

    /**
     * Registra un mundo/escenario en el juego.
     * Persona B llamara a este metodo para anadir sus fabricas.
     */
    public void registrarMundo(MundoFactory mundo) {
        mundos.add(mundo);
    }

    private void crearJugador() {
        System.out.println("\n--- CREACION DE PERSONAJE ---");

        System.out.print("Nombre de tu personaje: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) nombre = "Heroe";

        System.out.println("\nReparte tus atributos (tienes 80 puntos entre fuerza, resistencia y agilidad).");
        int puntosDisponibles = 80;

        System.out.print("Fuerza (1-" + (puntosDisponibles - 2) + "): ");
        int fuerza = leerNumero(1, puntosDisponibles - 2);
        puntosDisponibles -= fuerza;

        System.out.print("Resistencia (1-" + (puntosDisponibles - 1) + "): ");
        int resistencia = leerNumero(1, puntosDisponibles - 1);
        puntosDisponibles -= resistencia;

        int agilidad = puntosDisponibles;
        System.out.println("Agilidad: " + agilidad + " (puntos restantes)");

        Arma arma = elegirArma();

        jugador = new Jugador(nombre, fuerza, resistencia, agilidad, arma);
        System.out.println("\nPersonaje creado: " + jugador);
    }

    private Arma elegirArma() {
        System.out.println("\nElige tu arma:");
        System.out.println("  1) Espada   (danio:20, usos:10, agi:5)");
        System.out.println("  2) Hacha    (danio:25, usos:8,  agi:2)");
        System.out.println("  3) Martillo (danio:30, usos:6,  agi:0)");
        System.out.println("  4) Arco     (danio:15, usos:12, agi:10)");
        System.out.println("  5) Varita   (danio:18, usos:15, agi:8)");
        System.out.println("  6) Bomba    (danio:35, usos:3,  agi:0)");
        System.out.println("  7) Escudo   (danio:5,  usos:20, agi:0)");
        System.out.println("  8) Armadura (danio:2,  usos:25, agi:0)");

        int opcion = leerNumero(1, 8);

        switch (opcion) {
            case 1: return new Espada();
            case 2: return new Hacha();
            case 3: return new Martillo();
            case 4: return new Arco();
            case 5: return new Varita();
            case 6: return new Bomba();
            case 7: return new Escudo();
            case 8: return new Armadura();
            default: return new Espada();
        }
    }

    //Juega un mundo completo: genera enemigos con la fabrica y los combate uno a uno.
    private void jugarMundo(MundoFactory fabrica, int numeroMundo) {
        System.out.println("\n========================================");
        System.out.println("  MUNDO " + numeroMundo + ": " + fabrica.getNombreMundo());
        System.out.println("========================================");

        List<Enemigo> enemigos = generarEnemigos(fabrica, numeroMundo);

        for (int i = 0; i < enemigos.size(); i++) {
            System.out.println("\nCombate " + (i + 1) + "/" + enemigos.size());

            boolean victoria = combatManager.ejecutarCombate(jugador, enemigos.get(i));

            if (!victoria) {
                juegoActivo = false;
                return;
            }

            //Recuperar algo de vida entre combates
            if (i < enemigos.size() - 1) {
                jugador.curar(20);
                System.out.println(jugador.getNombre() + " descansa y recupera algo de vida. ["
                        + jugador.getVida() + "/" + jugador.getVidaMaxima() + "]");
            }
        }

        System.out.println("\n" + jugador.getNombre() + " ha superado " + fabrica.getNombreMundo() + "!");
    }

    //Genera la lista de enemigos para un mundo. Más enemigos en mundos avanzados.
    private List<Enemigo> generarEnemigos(MundoFactory fabrica, int numeroMundo) {
        List<Enemigo> enemigos = new ArrayList<>();

        int guerreros = 1 + numeroMundo;
        int magos = numeroMundo;
        int mutantes = Math.max(1, numeroMundo - 1);

        for (int i = 0; i < guerreros; i++) enemigos.add(fabrica.crearGuerrero());
        for (int i = 0; i < magos; i++) enemigos.add(fabrica.crearMago());
        for (int i = 0; i < mutantes; i++) enemigos.add(fabrica.crearMutante());

        return enemigos;
    }

    private void mostrarResultadoFinal() {
        System.out.println("\n========================================");
        if (juegoActivo) {
            System.out.println("  ENHORABUENA! HAS COMPLETADO TODOS LOS MUNDOS!");
        } else {
            System.out.println("  HAS SIDO DERROTADO...");
        }
        System.out.println("  Puntuacion final: " + jugador.getPuntuacion() + " puntos");
        System.out.println("========================================");
    }

    private void cerrar() {
        scanner.close();
    }

    private int leerNumero(int min, int max) {
        int num = -1;
        while (num < min || num > max) {
            System.out.print("> ");
            try {
                num = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                num = -1;
            }
            if (num < min || num > max) {
                System.out.println("Introduce un numero entre " + min + " y " + max + ".");
            }
        }
        return num;
    }

    public Jugador getJugador() { return jugador; }
    public boolean isJuegoActivo() { return juegoActivo; }
}