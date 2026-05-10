import game.GameController;
import mundos.MundoSelva;
import mundos.MundoMar;
import mundos.MundoDesierto;

public class Main {

    public static void main(String[] args) {
        GameController controller = GameController.getInstance();

        controller.registrarMundo(new MundoSelva());
        controller.registrarMundo(new MundoMar());
        controller.registrarMundo(new MundoDesierto());

        controller.iniciarJuego();
    }
}
