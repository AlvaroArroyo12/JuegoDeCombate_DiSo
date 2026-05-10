package armas;

//Clase abstracta que representa un arma equipable por un personaje
//Cada arma tiene daño base, utilidad (usos restantes) y un bonus de agilidad
public abstract class Arma {

    private int danio;
    private int utilidad;
    private int agilidad;
    private String nombre;

    public Arma(String nombre, int danio, int utilidad, int agilidad) {
        this.nombre = nombre;
        this.danio = danio;
        this.utilidad = utilidad;
        this.agilidad = agilidad;
    }

    //Repara el arma recuperando parte de su utilidad, cada subtipo define cuanto
    public abstract void reparar();

    //Getters y setters
    public int getDanio() { return danio; }
    public void setDanio(int danio){
        this.danio = danio; 
    }

    public int getUtilidad() { return utilidad; }
    public void setUtilidad(int utilidad){
        this.utilidad = utilidad; 
    }

    public int getAgilidad() { return agilidad; }
    public void setAgilidad(int agilidad){ 
        this.agilidad = agilidad;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre){ 
        this.nombre = nombre; 
    }

    @Override
    public String toString() {
        return nombre + " [daño=" + danio + ", usos=" + utilidad + "]";
    }
}