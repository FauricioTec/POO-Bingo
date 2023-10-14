package logicadenegocios;

import java.io.IOException;
import java.util.ArrayList;

public class Partida {

  private ArrayList<Jugador> jugadores;
  private ArrayList<Carton> cartones;

  /**
   * Constructor de la clase Partida
   *
   * @param pCantidadCartones Cantidad de cartones que se van a generar
   */
  public Partida(int pCantidadCartones) {
    jugadores = new ArrayList<>(); //TODO: Los jugadores deben ser cargados desde la base de datos
    generarCartones(pCantidadCartones);
  }

  /**
   * Metodo que retorna un String con la partida
   *
   * @return String con la informacion de la partida
   */
  public String toString() {
    String str = "";
    str += "{\n";
    str += "  jugadores: " + jugadores + ",\n";
    str += "  cartones: " + cartones + ",\n";
    str += "}";
    return str;
  }

  /**
   * Metodo que prepara el directorio donde se van a guardar los cartones
   *
   * @param pDireccion Direccion del directorio
   */
  public void prepararDirectorio(String pDireccion) throws RuntimeException {
    java.io.File directorio = new java.io.File(pDireccion);
    if (!directorio.exists()) {
      boolean seCreo = directorio.mkdir();
      if (!seCreo) {
        throw new RuntimeException("No se pudo crear el directorio");
      }
    } else {
      java.io.File[] archivos = directorio.listFiles();
      assert archivos != null;
      for (java.io.File archivo : archivos) {
        boolean seElimino = archivo.delete();
        if (!seElimino) {
          throw new RuntimeException("No se pudo vaciar el directorio");
        }
      }
    }
  }

  /**
   * Metodo que genera los cartones de la partida
   *
   * @param pCantidad Cantidad de cartones que se van a generar
   */
  private void generarCartones(int pCantidad) {
    cartones = new ArrayList<>();
    prepararDirectorio("cartones");
    for (int i = 0; i < pCantidad; i++) {
      Carton carton = new Carton();
      cartones.add(carton);
    }
  }

  /**
   * Retorna la cantidad de cartones que hay en la partida sin asignar
   *
   * @return Cantidad de cartones sin asignar
   */
  private int contarCartonesSinAsignar() {
    int cantidad = 0;
    for (Carton carton : cartones) {
      if (carton.getJugador() == null) {
        cantidad++;
      }
    }
    return cantidad;
  }

  /**
   * Metodo que retorna un carton aleatorio de la partida que no tenga jugador
   *
   * @return Carton aleatorio
   */
  private Carton obtenerCartonSinAsignar() {
    ArrayList<Carton> cartonesSinAsignar = new ArrayList<>();
    for (Carton carton : cartones) {
      if (carton.getJugador() == null) {
        cartonesSinAsignar.add(carton);
      }
    }
    int indice = (int) (Math.random() * cartonesSinAsignar.size());
    return cartonesSinAsignar.get(indice);
  }

  /**
   * Metodo que asigna un jugador a un carton
   *
   * @param pCedula   Cedula del jugador
   * @param pCantidad Cantidad de cartones que se le van a asignar
   */
  public void enviarCartonesAJugador(String pCedula, int pCantidad)
      throws javax.mail.MessagingException, IOException {
    if (pCantidad > contarCartonesSinAsignar()) {
      throw new RuntimeException("No hay suficientes cartones sin asignar");
    }
    Jugador jugador = obtenerJugadorPorCedula(pCedula);
    if (jugador == null) {
      throw new RuntimeException("No se encontro el jugador");
    }
    ArrayList<Carton> cartonesJugador = new ArrayList<>();
    for (int i = 0; i < pCantidad; i++) {
      Carton carton = obtenerCartonSinAsignar();
      carton.setJugador(jugador);
      cartonesJugador.add(carton);
    }
    Email.enviarCorreoConCartones(cartonesJugador, jugador.getEmail());
  }

  /**
   * Metodo que busca un jugador por su cedula
   *
   * @param pCedula Cedula del jugador
   * @return Jugador encontrado
   */
  private Jugador obtenerJugadorPorCedula(String pCedula) {
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
  public boolean existeCarton(String pIdCarton) {
    for (Carton carton : cartones) {
      if (carton.getId().equals(pIdCarton)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Carton> getCartones() {
    return cartones;
  }
}