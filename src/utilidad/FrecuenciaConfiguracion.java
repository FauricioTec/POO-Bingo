package utilidad;

import datos.ConexionBaseDatos;
import java.util.ArrayList;
import logicadenegocios.Configuracion;

public class FrecuenciaConfiguracion {
  Configuracion configuracion;
  int frecuencia;

  public FrecuenciaConfiguracion(Configuracion pConfiguracion, int pFrecuencia) {
    configuracion = pConfiguracion;
    frecuencia = pFrecuencia;
  }

  public static ArrayList<FrecuenciaConfiguracion> obtenerFrecuenciaConfiguracion() {
    return ConexionBaseDatos.obtenerFrecuenciaConfiguracion();
  }

  public Configuracion getConfiguracion() {
    return configuracion;
  }

  public int getFrecuencia() {
    return frecuencia;
  }
}
