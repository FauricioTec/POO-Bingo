package logicadenegocios;

public class Casilla {

  private int numero;
  private boolean estaMarcada;

  /**
   * Constructor de la clase Casilla
   *
   * @param pNumero Numero de la casilla
   */
  public Casilla(int pNumero) {
    numero = pNumero;
    estaMarcada = false;
  }

  public String toString() {
    return "Casilla: " + numero + " " + estaMarcada;
  }

  public int getNumero() {
    return numero;
  }

  public boolean getEstaMarcada() {
    return estaMarcada;
  }

  public void setEstaMarcada(boolean pEstaMarcada) {
    estaMarcada = pEstaMarcada;
  }

}
