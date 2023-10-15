package utilidad;

import datos.ConexionBaseDatos;
import java.util.ArrayList;

public class NumeroApariciones {
  private int numero;
  private int apariciones;

  public NumeroApariciones(int pNumero, int pApariciones) {
    numero = pNumero;
    apariciones = pApariciones;
  }

  public int getNumero() {
    return numero;
  }

  public int getApariciones() {
    return apariciones;
  }

  public static ArrayList<NumeroApariciones> obtenerTopNumeroApariciones() {
    return ConexionBaseDatos.obtenerTopNumeros();
  }
}
