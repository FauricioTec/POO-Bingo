package logicadenegocios;

import datos.ConexionBaseDatos;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import utilidad.Email;

public class Juego {

  private final Partida partida;
  private final ArrayList<Integer> cedulasGanadores;

  private final ArrayList<Integer> numerosCantados;

  private final ArrayList<String> idCartonesGanadores;
  private final String premio;
  private final Configuracion configuracion;
  private final LocalDate fecha;
  private final LocalTime hora;


  public Juego(Partida pPartida, String pPremio, Configuracion pConfiguracion) {
    partida = pPartida;
    premio = pPremio;
    configuracion = pConfiguracion;
    numerosCantados = new ArrayList<>();
    cedulasGanadores = new ArrayList<>();
    idCartonesGanadores = new ArrayList<>();
    fecha = LocalDate.now();
    hora = LocalTime.now();
  }

  public Juego(ArrayList<Integer> pNumerosCantados, Configuracion pConfiguracion, LocalDate pFecha,
      LocalTime pHora) {
    numerosCantados = pNumerosCantados;
    configuracion = pConfiguracion;
    fecha = pFecha;
    hora = pHora;
    partida = null;
    premio = null;
    cedulasGanadores = null;
    idCartonesGanadores = null;
  }

  public void guardarJuego() {
    ConexionBaseDatos.insertarJuego(this);
  }

  /**
   * Metodo que genera un numero random con un rango, que marca las casillas de los cartones, y
   * retorna el numero generado
   */
  public void cantarNumero() {
    int numero = (int) (Math.random() * 75) + 1;
    while (numerosCantados.contains(numero)) {
      numero = (int) (Math.random() * 75) + 1;
    }
    for (Carton carton : partida.getCartones()) {
      carton.marcarNumero(numero);
    }
    numerosCantados.add(numero);
  }

  /**
   * Metodo que notifica a los ganadores de la partida
   *
   * @throws RuntimeException si no se pudo enviar el correo a algun ganador
   */
  public void notificarGanadores() {
    int cantidadErroresEnvio = 0;
    Map<String, Carton> cartonesMap = new HashMap<>();

    for (Carton carton : partida.getCartones()) {
      cartonesMap.put(carton.getId(), carton);
    }

    for (String idCartonGanador : idCartonesGanadores) {
      Carton carton = cartonesMap.get(idCartonGanador);

      if (carton != null && carton.tieneJugador()) {
        try {
          Email.enviarCorreoGanador(carton.getId(), premio, carton.getJugador().getEmail());
        } catch (IOException | javax.mail.MessagingException e) {
          cantidadErroresEnvio++;
        }
      }
    }

    if (cantidadErroresEnvio > 0) {
      throw new RuntimeException(
          "No se pudo enviar el correo a " + cantidadErroresEnvio + " ganadores");
    }
  }

  /**
   * Metodo que comprueba si hay cartones ganadores en la partida, y los agrega a la lista de
   * cartones ganadores
   *
   * @return Booleano que indica si hubo ganadores
   */
  public boolean hayGanador() {
    boolean huboGanador = false;
    for (Carton carton : partida.getCartones()) {
      if (comprobarPatron(carton)) {
        idCartonesGanadores.add(carton.getId());
        if (carton.tieneJugador()) {
          cedulasGanadores.add(carton.getJugador().getCedula());
        }
        huboGanador = true;
      }
    }
    return huboGanador;
  }

  /**
   * Metodo que comprueba si un carton tiene el patron de la partida
   *
   * @param pCarton Carton a comprobar
   * @return Booleano que indica si el carton tiene el patron
   */
  private boolean comprobarPatron(Carton pCarton) {
    return switch (configuracion) {
      case JUGAR_EN_X -> pCarton.tienePatronEnX();
      case CUATRO_ESQUINAS -> pCarton.tienePatronCuatroEsquinas();
      case CARTON_LLENO -> pCarton.tieneCartonLleno();
      case JUGAR_EN_Z -> pCarton.tienePatronEnZ();
    };
  }

  public int contarJugadores() {
    TreeSet<Integer> cedulas = new TreeSet<>();
    for (Carton carton : partida.getCartones()) {
      if (carton.tieneJugador()) {
        cedulas.add(carton.getJugador().getCedula());
      }
    }
    return cedulas.size();
  }

  public String toString() {
    String str = "";
    str += "{\n";
    str += "  partida: " + partida + ",\n";
    str += "  cedulasGanadores: " + cedulasGanadores + ",\n";
    str += "  numerosCantados: " + numerosCantados + ",\n";
    str += "  idCartonesGanadores: " + idCartonesGanadores + ",\n";
    str += "  premio: " + premio + ",\n";
    str += "  configuracion: " + configuracion + ",\n";
    str += "  fecha: " + fecha + ",\n";
    str += "  hora: " + hora + ",\n";
    str += "}";
    return str;
  }

  public ArrayList<Integer> getCedulasGanadores() {
    return cedulasGanadores;
  }

  public int contarCartones() {
    return partida.getCartones().size();
  }

  public ArrayList<String> getCartonesGanadores() {
    return idCartonesGanadores;
  }

  public String getPremio() {
    return premio;
  }

  public Configuracion getConfiguracion() {
    return configuracion;
  }

  public ArrayList<Integer> getNumerosCantados() {
    return numerosCantados;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public LocalTime getHora() {
    return hora;
  }
}
