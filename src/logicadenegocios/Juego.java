package logicadenegocios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Juego {

  private Partida partida;
  private ArrayList<String> cedulasGanadores;

  private ArrayList<Integer> numerosCantados;

  private ArrayList<String> idCartonesGanadores;
  private String premio;
  private Configuracion configuracion;


  public Juego(Partida pPartida,  String pPremio, Configuracion pConfiguracion) {
    partida = pPartida;
    premio = pPremio;
    configuracion = pConfiguracion;
    numerosCantados = new ArrayList<>();
    cedulasGanadores = new ArrayList<>();
    idCartonesGanadores = new ArrayList<>();
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
   * Metodo que agrega un carton ganador a la lista de cartones ganadores
   *
   * @param pCarton Carton ganador
   */
  private void annadirCartonGanador(Carton pCarton)
      throws javax.mail.MessagingException, IOException {
    idCartonesGanadores.add(pCarton.getId());
    if (pCarton.tieneJugador()) {
      cedulasGanadores.add(pCarton.getJugador().getCedula());
      Email.enviarCorreoGanador(pCarton.getId(), premio, pCarton.getJugador().getEmail());
    }
  }

  /**
   * Metodo que comprueba si hay cartones ganadores en la partida, y los agrega a la lista de
   * cartones ganadores
   *
   * @return Booleano que indica si hubo ganadores
   */
  public boolean hayGanador() throws javax.mail.MessagingException, IOException {
    boolean huboGanador = false;
    for (Carton carton : partida.getCartones()) {
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

  public int contarJugadores() {
    TreeSet<String> cedulas = new TreeSet<>();
    for (Carton carton : partida.getCartones()) {
      if (carton.tieneJugador()) {
        cedulas.add(carton.getJugador().getCedula());
      }
    }
    return cedulas.size();
  }

  public ArrayList<String> getCedulasGanadores() {
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
}
