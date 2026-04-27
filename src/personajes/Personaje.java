package personajes;

import armas.Arma;
import personajes.estados.EstadoPersonaje;


//Clase abstracta que representa a cualquier personaje del juego
//Tanto el jugador como los enemigos heredan de aqui

public abstract class Personaje {

    private String nombre;
    private int fuerza;
    private int resistencia;
    private int agilidad;
    private int vida;
    private int vidaMaxima;
    private Arma arma;
    private EstadoPersonaje estado;

    public Personaje(String nombre, int fuerza, int resistencia, int agilidad, Arma arma) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.agilidad = agilidad;
        this.vidaMaxima = 100;
        this.vida = vidaMaxima;
        this.arma = arma;
        this.estado = null;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    //Aplica daño al personaje y pide al estado que evalue si hay transicion
    public void recibirDanio(int danio) {
        if (danio <= 0) return;
        this.vida = Math.max(0, this.vida - danio);
        if (estado != null) {
            estado.evaluarTransicion();
        }
    }

    //Recupera vida sin superar el maximo y evalua transicion de estado
    public void curar(int cantidad) {
        if (cantidad <= 0) return;
        this.vida = Math.min(vidaMaxima, this.vida + cantidad);
        if (estado != null) {
            estado.evaluarTransicion();
        }
    }

    //Porcentaje de vida actual (0-100)
    public int getPorcentajeVida() {
        if (vidaMaxima == 0) return 0;
        return (vida * 100) / vidaMaxima;
    }

    //Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre){ 
        this.nombre = nombre; 
    }

    public int getFuerza() { return fuerza; }
    public void setFuerza(int fuerza){ 
        this.fuerza = fuerza; 
    }

    public int getResistencia() { return resistencia; }
    public void setResistencia(int resistencia){ 
        this.resistencia = resistencia; 
    }

    public int getAgilidad() { return agilidad; }
    public void setAgilidad(int agilidad){ 
        this.agilidad = agilidad; 
    }

    public int getVida() { return vida; }
    public void setVida(int vida){ 
        this.vida = Math.max(0, Math.min(vidaMaxima, vida)); 
    }

    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima){ 
        this.vidaMaxima = vidaMaxima; 
    }

    public Arma getArma() { return arma; }
    public void setArma(Arma arma){ 
        this.arma = arma; 
    }

    public EstadoPersonaje getEstado() { return estado; }
    public void setEstado(EstadoPersonaje estado){ 
        this.estado = estado; 
    }

    @Override
    public String toString() {
        String est = (estado != null) ? estado.getNombre() : "---";
        String arm = (arma != null) ? arma.getNombre() : "sin arma";
        return nombre + " | vida: " + vida + "/" + vidaMaxima
                + " | fuerza:" + fuerza + " resistencia:" + resistencia + " agilidad:" + agilidad
                + " | " + est + " | " + arm;
    }
}