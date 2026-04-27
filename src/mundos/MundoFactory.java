package mundos;

import personajes.tipos.Guerrero;
import personajes.tipos.Mago;
import personajes.tipos.Mutante;

//Interfaz del patron Abstract Factory.
//Cada mundo implementa esta interfaz para crear sus propias variantes de enemigos.

public interface MundoFactory {

    Guerrero crearGuerrero();
    Mago crearMago();
    Mutante crearMutante();

    //Nombre del mundo para la consola
    String getNombreMundo();
}
