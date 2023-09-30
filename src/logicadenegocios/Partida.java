package logicadenegocios;

import java.util.ArrayList;

public class Partida {

  private final Configuracion configuracion;
  private final ArrayList<Jugador> jugadores;
  private final String premio;
  private final ArrayList<Carton> cartonesGanadores;
  private ArrayList<Carton> cartones;

  /**
   * Constructor de la clase Partida
   *
   * @param pConfiguracion    Configuracion de la partida
   * @param pJugadores        Jugadores que participan en la partida
   * @param pPremio           Monto del premio
   * @param pCantidadCartones Cantidad de cartones que se van a generar
   */
  public Partida(Configuracion pConfiguracion, ArrayList<Jugador> pJugadores, String pPremio,
      int pCantidadCartones) {
    configuracion = pConfiguracion;
    jugadores = pJugadores;
    premio = pPremio;
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
    str += "{\n";
    str += "  configuracion: " + configuracion + ",\n";
    str += "  jugadores: " + jugadores + ",\n";
    str += "  premio: " + premio + ",\n";
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
  public void asignarJugadorACarton(String pCedula, String pIdCarton) {
    Jugador jugadorEncontrado = buscarJugadorPorCedula(pCedula);
    Carton cartonEncontrado = buscarCartonPorId(pIdCarton);

    if (jugadorEncontrado != null && cartonEncontrado != null
        && cartonEncontrado.getJugador() == null) {
      // TODO: Enviar correo al jugador con la imagen del carton
      cartonEncontrado.setJugador(jugadorEncontrado);
    }
  }


  /**
   * Metodo que busca un jugador por su cedula
   *
   * @param pCedula Cedula del jugador
   * @return Jugador encontrado
   */
  private Jugador buscarJugadorPorCedula(String pCedula) {
    for (Jugador jugador : jugadores) {
      if (jugador.getCedula().equals(pCedula)) {
        return jugador;
      }
    }
    return null;
  }


  /**
   * Metodo que busca un carton por su id
   *
   * @param pIdCarton Id del carton
   * @return Carton encontrado
   */
  private Carton buscarCartonPorId(String pIdCarton) {
    for (Carton carton : cartones) {
      if (carton.getId().equals(pIdCarton)) {
        return carton;
      }
    }
    return null;
  }

  /**
   * Metodo que genera un numero random con un rango, que marca las casillas de los cartones, y
   * retorna el numero generado
   *
   * @return Numero generado
   */
  public int girarTombola() {
    int numero = (int) (Math.random() * 75) + 1;
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
    // TODO: Enviar correo al jugador ganador con la informacion del premio, si el carton tiene jugador

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

  public ArrayList<Carton> getCartonesGanadores() {
    return cartonesGanadores;
  }
}