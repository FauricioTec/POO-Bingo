package logicadenegocios;

import datos.ConexionBaseDatos;

/**
 * Clase que representa un jugador
 */
public class Jugador {

  private final String nombre;
  private final int cedula;
  private final String email;

  /**
   * Constructor de la clase Jugador
   *
   * @param pNombre Nombre del jugador
   * @param pCedula Cedula del jugador
   * @param pEmail  Email del jugador
   */
  public Jugador(String pNombre, int pCedula, String pEmail) {
    nombre = pNombre;
    cedula = pCedula;
    email = pEmail;
  }

  public void guardarJugador() {
    ConexionBaseDatos.insertarJugador(this);
  }

  /**
   * Metodo que retorna un String con el jugador
   *
   * @return String con la informacion del jugador
   */
  public String toString() {
    String str = "";
    str += "{\n";
    str += "  nombre: " + nombre + ",\n";
    str += "  cedula: " + cedula + ",\n";
    str += "  email: " + email + "\n";
    str += "}";
    return str;
  }

  public String getNombre() {
    return nombre;
  }

  public int getCedula() {
    return cedula;
  }

  public String getEmail() {
    return email;
  }
}
