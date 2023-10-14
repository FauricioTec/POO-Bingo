package logicadenegocios;

import java.util.ArrayList;

/**
 * Clase que representa un carton
 */
public class Carton {

  private static int cantidadCartones = 0;
  private final String id;

  private Jugador jugador;
  private final Casilla[][] casillas;

  /**
   * Constructor de la clase Carton
   */
  public Carton() {
    id = generarId();
    casillas = new Casilla[5][5];
    generarCasillas();
    ImagenCarton imagenCarton = new ImagenCarton(casillas, id);
    imagenCarton.guardarImagen("cartones\\", id);
  }

  /**
   * Metodo que genera las casillas del carton
   *
   */
  public void generarCasillas() {
    for (int columna = 0; columna < 5; columna++) {
      ArrayList<Integer> numerosGenerados = new ArrayList<>();
      int min = 1 + (columna * 15);
      int max = 15 + (columna * 15);
      for (int fila = 0; fila < 5; fila++) {
        int numero;
        do {
          numero = generarNumeroRandomConRango(min, max);
        } while (numerosGenerados.contains(numero));
        numerosGenerados.add(numero);
        casillas[fila][columna] = new Casilla(numero);
      }
    }
  }

  /**
   * Metodo que marca un numero en el carton
   *
   * @param pNumero Numero que se va a marcar
   */
  public void marcarNumero(int pNumero) {
    for (int fila = 0; fila < 5; fila++) {
      for (int columna = 0; columna < 5; columna++) {
        if (casillas[fila][columna].getNumero() == pNumero) {
          casillas[fila][columna].setEstaMarcada(true);
        }
      }
    }
  }

  /**
   * Metodo que comprueba si el carton tiene el patron de las cuatro esquinas
   *
   * @return Booleano que indica si el carton tiene el patron de las cuatro esquinas
   */
  public boolean tienePatronCuatroEsquinas() {
    return casillas[0][0].getEstaMarcada() && casillas[0][4].getEstaMarcada()
        && casillas[4][0].getEstaMarcada() && casillas[4][4].getEstaMarcada();
  }

  /**
   * Metodo que comprueba si el carton esta lleno
   *
   * @return Booleano que indica si el carton esta lleno
   */
  public boolean tieneCartonLleno() {
    for (int fila = 0; fila < 5; fila++) {
      for (int columna = 0; columna < 5; columna++) {
        if (!casillas[fila][columna].getEstaMarcada()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Metodo que comprueba si el carton tiene el patron en X
   *
   * @return Booleano que indica si el carton tiene el patron en X
   */
  public boolean tienePatronEnX() {
    for (int fila = 0; fila < 5; fila++) {
      if (!casillas[fila][fila].getEstaMarcada()) {
        return false;
      }
      if (!casillas[fila][4 - fila].getEstaMarcada()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Metodo que comprueba si el carton tiene el patron en Z
   *
   * @return Booleano que indica si el carton tiene el patron en Z
   */
  public boolean tienePatronEnZ() {
    for (int columna = 0; columna < 5; columna++) {
      if (!casillas[0][columna].getEstaMarcada()) {
        return false;
      }
    }
    for (int columna = 0; columna < 5; columna++) {
      if (!casillas[4][columna].getEstaMarcada()) {
        return false;
      }
    }
    for (int fila = 0; fila < 5; fila++) {
      if (!casillas[fila][4 - fila].getEstaMarcada()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Metodo que genera un numero random con un rango
   *
   * @param pMin Minimo del rango
   * @param pMax Maximo del rango
   * @return Numero generado
   */
  private int generarNumeroRandomConRango(int pMin, int pMax) {
    int range = (pMax - pMin) + 1;
    return (int) (Math.random() * range) + pMin;
  }

  /**
   * Metodo que genera un Id unico para el carton
   *
   * @return Id generado
   */
  private String generarId() {
    cantidadCartones++;
    return "CTN" + cantidadCartones;
  }

  /**
   * Metodo que retorna un String con la informacion del carton
   *
   * @return String con la informacion del carton
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("{\n");
    str.append("  id: ").append(id).append(",\n");
    str.append("  casillas: [" + "\n");
    for (int fila = 0; fila < 5; fila++) {
      str.append("    [");
      for (int columna = 0; columna < 5; columna++) {
        if (casillas[fila][columna].getEstaMarcada()) {
          str.append("X");
        } else {
          str.append(casillas[fila][columna].getNumero());
        }
        if (columna < 4) {
          str.append(", ");
        }
      }
      if (fila < 4) {
        str.append("],\n");
      } else {
        str.append("]\n");
      }
    }
    str.append("  ],\n");
    str.append("}");
    return str.toString();
  }

  public String getId() {
    return id;
  }

  public Jugador getJugador() {
    return jugador;
  }

  public boolean tieneJugador() {
    return jugador != null;
  }

  public void setJugador(Jugador jugador) {
    this.jugador = jugador;
  }

  public Casilla[][] getCasillas() {
    return casillas;
  }
}
