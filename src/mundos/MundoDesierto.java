package mundos;

import estrategia.EstrategiaAgresiva;
import estrategia.EstrategiaEquilibrada;
import personajes.tipos.Guerrero;
import personajes.tipos.Mago;
import personajes.tipos.Mutante;
import personajes.tipos.guerreros.GuerreroDesierto;
import personajes.tipos.magos.MagoDesierto;
import personajes.tipos.mutantes.MutanteDesierto;

//implementacion del abstract factory 
public class MundoDesierto implements MundoFactory {

    @Override
    public Guerrero crearGuerrero() {
        return new GuerreroDesierto(new EstrategiaAgresiva());
    }

    @Override
    public Mago crearMago() {
        return new MagoDesierto(new EstrategiaAgresiva()); 
    }

    @Override
    public Mutante crearMutante() {
        return new MutanteDesierto(new EstrategiaEquilibrada());
    }

    @Override
    public String getNombreMundo() {
        return "Desierto Abrasador";
    }
}
