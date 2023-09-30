package logicadenegocios;

import java.util.TreeSet;

public class Jugador {

  private static final TreeSet<String> cedulas = new TreeSet<>();
  private final String nombre;
  private final String cedula;
  private final String email;

  /**
   * Constructor de la clase Jugador
   *
   * @param pNombre Nombre del jugador
   * @param pCedula Cedula del jugador
   * @param pEmail  Email del jugador
   */
  public Jugador(String pNombre, String pCedula, String pEmail) {
    if (cedulas.contains(pCedula)) {
      throw new RuntimeException("Ya existe un jugador con esa cedula");
    }
    nombre = pNombre;
    cedula = pCedula;
    cedulas.add(pCedula);
    email = pEmail;
  }

  /**
   * Metodo que retorna un String con el jugador
   *
   * @return String con la informacion del jugador
   */
  public String toString() {
    String str = "";
    str += "Jugador: {\n";
    str += "  nombre: " + nombre + ",\n";
    str += "  cedula: " + cedula + ",\n";
    str += "  email: " + email + "\n";
    str += "}";
    return str;
  }

  public String getCedula() {
    return cedula;
  }

}
