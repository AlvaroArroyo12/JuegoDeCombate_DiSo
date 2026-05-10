package mundos;

import estrategia.EstrategiaAgresiva;
import estrategia.EstrategiaDefensiva;
import estrategia.EstrategiaEquilibrada;
import personajes.tipos.Guerrero;
import personajes.tipos.Mago;
import personajes.tipos.Mutante;
import personajes.tipos.guerreros.GuerreroMar;
import personajes.tipos.magos.MagoMar;
import personajes.tipos.mutantes.MutanteMar;

//implementacion del abstract factory
public class MundoMar implements MundoFactory {

    @Override
    public Guerrero crearGuerrero() {
        return new GuerreroMar(new EstrategiaAgresiva());
    }

    @Override
    public Mago crearMago() {
        return new MagoMar(new EstrategiaDefensiva());
    }

    @Override
    public Mutante crearMutante() {
        return new MutanteMar(new EstrategiaEquilibrada());
    }

    @Override
    public String getNombreMundo() {
        return "Profundidades del Mar";
    }
}
