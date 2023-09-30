import java.util.ArrayList;
import javax.xml.stream.FactoryConfigurationError;
import logicadenegocios.Carton;
import logicadenegocios.Configuracion;
import logicadenegocios.Jugador;
import logicadenegocios.Partida;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

  public static void main(String[] args) {
    ArrayList<Jugador> jugadores = new ArrayList<>();
    jugadores.add(new Jugador("Juan", "123456789", "@gmail.com"));
    jugadores.add(new Jugador("Pedro", "987654321", "@gmail.com"));
    jugadores.add(new Jugador("Maria", "123456789", "@gmail.com"));

    Configuracion configuracion = Configuracion.JUGAR_EN_Z;
    Partida partida = new Partida(configuracion, jugadores, 1000, 10);
    System.out.println(partida);

    while(!partida.comprobarCartonesGanadores()) {
      partida.girarTombola();
    }

    System.out.println(partida);
  }
}