package logicadenegocios;

import java.util.TreeSet;

public class Jugador {

  private static TreeSet<String> cedulas = new TreeSet<String>();
  private String nombre;
  private String cedula;
  private String email;

  public Jugador(String pNombre, String pCedula, String pEmail) {
    if (cedulas.contains(pCedula)) {
      throw new RuntimeException("Ya existe un jugador con esa cedula");
    }
    nombre = pNombre;
    cedula = pCedula;
    cedulas.add(pCedula);
    email = pEmail;
  }

  public static TreeSet<String> getCedulas() {
    return cedulas;
  }

  public static void setCedulas(TreeSet<String> cedulas) {
    Jugador.cedulas = cedulas;
  }

  public String toString() {
    return "Jugador: {\n  nombre: " + nombre + "\n  cedula: " + cedula + "\n  email: " + email
        + "\n}";
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
