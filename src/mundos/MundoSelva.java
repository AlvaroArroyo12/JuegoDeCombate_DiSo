package mundos;

import estrategia.EstrategiaAgresiva;
import estrategia.EstrategiaDefensiva;
import personajes.tipos.Guerrero;
import personajes.tipos.Mago;
import personajes.tipos.Mutante;
import personajes.tipos.guerreros.GuerreroSelva;
import personajes.tipos.magos.MagoSelva;
import personajes.tipos.mutantes.MutanteSelva;

//implementación del abstract factory
public class MundoSelva implements MundoFactory {

    @Override
    public Guerrero crearGuerrero() {
        return new GuerreroSelva(new EstrategiaAgresiva());
    }

    @Override
    public Mago crearMago() {
        return new MagoSelva(new EstrategiaDefensiva());
    }

    @Override
    public Mutante crearMutante() {
        return new MutanteSelva(new EstrategiaAgresiva());
    }

    @Override
    public String getNombreMundo() {
        return "Selva Profunda";
    }
}
