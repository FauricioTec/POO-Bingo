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
   * Metodo que comprueba si hay ganadores en la partida, y los agrega a la lista de ganadores
   *
   * @return Booleano que indica si hubo ganadores
   */
  public boolean comprobarCartonesGanadores() {
    boolean huboGanador = false;
    for (Carton carton : cartones) {
      switch (configuracion) {
        case JUGAR_EN_X -> {
          if (carton.tienePatronEnX()) {
            cartonesGanadores.add(carton);
            huboGanador = true;
          }
        }
        case CUATRO_ESQUINAS -> {
          if (carton.tienePatronCuatroEsquinas()) {
            cartonesGanadores.add(carton);
            huboGanador = true;
          }
        }
        case CARTON_LLENO -> {
          if (carton.tieneCartonLleno()) {
            cartonesGanadores.add(carton);
            huboGanador = true;
          }
        }
        case JUGAR_EN_Z -> {
          if (carton.tienePatronEnZ()) {
            cartonesGanadores.add(carton);
            huboGanador = true;
          }
        }
      }
    }
    return huboGanador;
  }
}