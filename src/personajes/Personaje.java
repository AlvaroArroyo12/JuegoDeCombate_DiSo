package personajes;

import armas.Arma;
import personajes.estados.Contexto;
import personajes.estados.EstadoPersonaje;

// Clase abstracta base para todos los personajes del juego.
public abstract class Personaje {

    private String nombre;
    private int fuerza;
    private int resistencia;
    private int agilidad;
    private int vida;
    private int vidaMaxima;
    private Arma arma;
    private Contexto contexto;

    public Personaje(String nombre, int fuerza, int resistencia, int agilidad, Arma arma) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.agilidad = agilidad;
        this.vidaMaxima = 100;
        this.vida = vidaMaxima;
        this.arma = arma;
        this.contexto = new Contexto(this);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void recibirDanio(int danio) {
        if (danio <= 0) return;
        this.vida = Math.max(0, this.vida - danio);
        contexto.logicaTransicionDeEstados();
    }

    public void curar(int cantidad) {
        if (cantidad <= 0 || !estaVivo()) return;
        this.vida = Math.min(vidaMaxima, this.vida + cantidad);
        contexto.logicaTransicionDeEstados();
    }

    public int getPorcentajeVida() {
        if (vidaMaxima == 0) return 0;
        return (vida * 100) / vidaMaxima;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getFuerza() { return fuerza; }
    public void setFuerza(int fuerza) { this.fuerza = fuerza; }

    public int getResistencia() { return resistencia; }
    public void setResistencia(int resistencia) { this.resistencia = resistencia; }

    public int getAgilidad() { return agilidad; }
    public void setAgilidad(int agilidad) { this.agilidad = agilidad; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vidaMaxima, vida)); }

    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }

    public Arma getArma() { return arma; }
    public void setArma(Arma arma) { this.arma = arma; }

    public Contexto getContexto() { return contexto; }

    /** Devuelve el estado activo actual. */
    public EstadoPersonaje getEstado() { return contexto.getEstadoPersonaje(); }

    /** Fuerza ajustada por el estado actual (ej: Herido aplica -20%). */
    public int getFuerzaEfectiva() {
        return contexto.getEstadoPersonaje().modificarFuerza(getFuerza());
    }

    /** Evalua la transicion de estado al inicio de cada turno. */
    public void evaluarEstadoTurno() {
        contexto.logicaTransicionDeEstados();
    }

    @Override
    public String toString() {
        String est = contexto.getEstadoPersonaje().getNombre();
        String arm = (arma != null) ? arma.getNombre() : "sin arma";
        return nombre + " | vida: " + vida + "/" + vidaMaxima
                + " | fuerza:" + fuerza + " resistencia:" + resistencia + " agilidad:" + agilidad
                + " | " + est + " | " + arm;
    }
}
