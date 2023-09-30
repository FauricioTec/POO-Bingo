package logicadenegocios;

import java.util.ArrayList;

public class Partida {

  private final Configuracion configuracion;
  private final ArrayList<Jugador> jugadores;
  private final double montoPremio;
  private final ArrayList<Carton> cartonesGanadores;
  private ArrayList<Carton> cartones;

  /**
   * Constructor de la clase Partida
   *
   * @param pConfiguracion    Configuracion de la partida
   * @param pJugadores        Jugadores que participan en la partida
   * @param pMontoPremio      Monto del premio
   * @param pCantidadCartones Cantidad de cartones que se van a generar
   */
  public Partida(Configuracion pConfiguracion, ArrayList<Jugador> pJugadores, double pMontoPremio,
      int pCantidadCartones) {
    configuracion = pConfiguracion;
    jugadores = pJugadores;
    montoPremio = pMontoPremio;
    generarCartones(pCantidadCartones);
    cartonesGanadores = new ArrayList<>();
  }

  /**
   * Metodo que retorna un String con la partida
   *
   * @return String con la informacion de la partida
   */
  public String toString() {
    String str = "";
    str += "Partida: {\n";
    str += "  configuracion: " + configuracion + ",\n";
    str += "  jugadores: " + jugadores + ",\n";
    str += "  montoPremio: " + montoPremio + ",\n";
    str += "  cartones: " + cartones + ",\n";
    str += "  cartonesGanadores: " + cartonesGanadores + "\n";
    str += "}";
    return str;
  }

  /**
   * Metodo que genera los cartones de la partida
   *
   * @param pCantidad Cantidad de cartones que se van a generar
   */
  private void generarCartones(int pCantidad) {
    cartones = new ArrayList<>();
    for (int i = 0; i < pCantidad; i++) {
      cartones.add(new Carton());
    }
  }

  /**
   * Metodo que asigna un jugador a un carton
   *
   * @param pCedula   Cedula del jugador
   * @param pIdCarton Id del carton
   */
  void asignarJugadorACarton(String pCedula, String pIdCarton) {
    for (Jugador jugador : jugadores) {
      if (jugador.getCedula().equals(pCedula)) {
        for (Carton carton : cartones) {
          if (carton.getId().equals(pIdCarton)) {
            carton.setJugador(jugador);
            return;
          }
        }
      }
    }
  }

  /**
   * Metodo que genera un numero random con un rango
   *
   * @param pMin Minimo del rango
   * @param pMax Maximo del rango
   * @return Numero generado
   */
  int generarNumeroRandomConRango(int pMin, int pMax) {
    int range = (pMax - pMin) + 1;
    return (int) (Math.random() * range) + pMin;
  }

  /**
   * Metodo que genera un numero random con un rango, que marca las casillas de los cartones, y
   * retorna el numero generado
   *
   * @return Numero generado
   */
  public int girarTombola() {
    int numero = generarNumeroRandomConRango(1, 75);
    for (Carton carton : cartones) {
      carton.marcarNumero(numero);
    }
    return numero;
  }

  /**
   * Metodo que agrega un carton ganador a la lista de cartones ganadores
   *
   * @param pCarton Carton ganador
   */
  private void annadirCartonGanador(Carton pCarton) {
    cartonesGanadores.add(pCarton);
    if (pCarton.getJugador() != null) {
      // TODO: Enviar correo al jugador
      return;
    }
  }

  /**
   * Metodo que comprueba si hay cartones ganadores en la partida, y los agrega a la lista de
   * cartones ganadores
   *
   * @return Booleano que indica si hubo ganadores
   */
  public boolean comprobarCartonesGanadores() {
    boolean huboGanador = false;
    for (Carton carton : cartones) {
      if (comprobarPatron(carton)) {
        annadirCartonGanador(carton);
        huboGanador = true;
      }
    }
    return huboGanador;
  }

  /**
   * Metodo que comprueba si un carton tiene el patron de la partida
   *
   * @param carton Carton a comprobar
   * @return Booleano que indica si el carton tiene el patron
   */
  private boolean comprobarPatron(Carton carton) {
    return switch (configuracion) {
      case JUGAR_EN_X -> carton.tienePatronEnX();
      case CUATRO_ESQUINAS -> carton.tienePatronCuatroEsquinas();
      case CARTON_LLENO -> carton.tieneCartonLleno();
      case JUGAR_EN_Z -> carton.tienePatronEnZ();
    };
  }
}