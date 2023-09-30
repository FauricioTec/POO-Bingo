package logicadenegocios;

import java.util.TreeSet;

public class Jugador {

  private static final TreeSet<String> cedulas = new TreeSet<>();
  private final String nombre;
  private final String cedula;
  private final String email;

  public Jugador(String pNombre, String pCedula, String pEmail) {
    if (cedulas.contains(pCedula)) {
      throw new RuntimeException("Ya existe un jugador con esa cedula");
    }
    nombre = pNombre;
    cedula = pCedula;
    cedulas.add(pCedula);
    email = pEmail;
  }

  public String toString() {
    return "Jugador: {\n  nombre: " + nombre + "\n  cedula: " + cedula + "\n  email: " + email
        + "\n}";
  }

  public String getCedula() {
    return cedula;
  }

}
