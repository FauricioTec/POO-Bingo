import java.io.IOException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import logicadenegocios.Configuracion;
import logicadenegocios.Juego;
import logicadenegocios.Jugador;
import logicadenegocios.Partida;

public class Main {

  public static void main(String[] args) throws MessagingException, IOException {
    Partida partida = new Partida(5);

    try {
    partida.enviarCartonesAJugador("123456789", 5);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    System.out.println(partida);

    Juego juego = new Juego(partida, "1000", Configuracion.CUATRO_ESQUINAS);

    while (!juego.hayGanador()) {
      juego.cantarNumero();
    }

    System.out.println("El juego ha terminado");
    System.out.println("Los numeros cantados fueron: " + juego.getNumerosCantados());
    System.out.println("Los cartones ganadores fueron: " + juego.getCartonesGanadores());
    System.out.println("Los jugadores ganadores fueron: " + juego.getCedulasGanadores());
  }
}