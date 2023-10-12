import java.io.IOException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import logicadenegocios.Configuracion;
import logicadenegocios.Jugador;
import logicadenegocios.Partida;

public class Main {

  public static void main(String[] args) throws MessagingException, IOException {
    ArrayList<Jugador> jugadores = new ArrayList<>();
    jugadores.add(new Jugador("Juan", "123456789", "fauricio.gr@gmail.com"));
    try {
      jugadores.add(new Jugador("Juan", "123456789", "@gmail.com"));
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    jugadores.add(new Jugador("Maria", "123456788", "@gmail.com"));

    Configuracion configuracion = Configuracion.JUGAR_EN_X;
    Partida partida = new Partida(configuracion, jugadores, "1000 colones", 10);
    System.out.println(partida);

    partida.enviarCartonesAJugador(jugadores.get(0).getCedula(), 5);

    while (!partida.comprobarCartonesGanadores()) {
      partida.girarTombola();
    }

    System.out.println(partida.getCartonesGanadores());
  }
}