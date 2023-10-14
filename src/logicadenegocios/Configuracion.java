package logicadenegocios;

public enum Configuracion {
  JUGAR_EN_X("Jugar en X"),
  CUATRO_ESQUINAS("Cuatro esquinas"),
  CARTON_LLENO("Carton lleno"),
  JUGAR_EN_Z("Jugar en Z");

  private final String nombre;

  Configuracion(String pNombre) {
    nombre = pNombre;
  }

  public static Configuracion toConfiguracion(String pNombre) {
    for (Configuracion configuracion : Configuracion.values()) {
      if (configuracion.nombre.equals(pNombre)) {
        return configuracion;
      }
    }
    return null;
  }

  public String toString() {
    return nombre;
  }
}
