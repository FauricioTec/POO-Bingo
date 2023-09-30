import java.util.ArrayList;
import logicadenegocios.Configuracion;
import logicadenegocios.Jugador;
import logicadenegocios.Partida;

public class Main {

  public static void main(String[] args) {
    ArrayList<Jugador> jugadores = new ArrayList<>();
    jugadores.add(new Jugador("Juan", "123456789", "@gmail.com"));
    try {
      jugadores.add(new Jugador("Juan", "123456789", "@gmail.com"));
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    jugadores.add(new Jugador("Maria", "123456788", "@gmail.com"));

    Configuracion configuracion = Configuracion.JUGAR_EN_Z;
    Partida partida = new Partida(configuracion, jugadores, "1000 colones", 10);
    System.out.println(partida);

    while (!partida.comprobarCartonesGanadores()) {
      partida.girarTombola();
    }

    System.out.println(partida);
  }
}