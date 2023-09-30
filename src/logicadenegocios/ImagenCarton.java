package logicadenegocios;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

/**
 * Clase que se encarga de generar la imagen del carton
 */
public class ImagenCarton {

  private final Graphics2D g2d;
  int width;
  int height;
  BufferedImage imagen;

  public ImagenCarton(Carton pCarton) {
    width = 500;
    height = 600;
    imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    g2d = imagen.createGraphics();

    dibujar(pCarton.getCasillas(), pCarton.getId());
  }


  private void dibujar(Casilla[][] pCasillas, String pId) {
    g2d.setColor(Color.BLUE);
    g2d.fillRect(0, 0, width, height);

    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 45));
    String str = "BINGO";
    int strPosX = (width / 2) - (g2d.getFontMetrics().stringWidth(str) / 2);
    int strPosY = 55;
    g2d.drawString(str, strPosX, strPosY);
    g2d.setFont(new Font("Arial", Font.BOLD, 30));

    dibujarCasillas(pCasillas);

    // añadir el id a la parte inferior derecha
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 15));
    str = pId;
    strPosX = width - g2d.getFontMetrics().stringWidth(str) - 20;
    strPosY = height - 7;
    g2d.drawString(str, strPosX, strPosY);
  }

  /**
   * Metodo que dibuja una casilla
   *
   * @param pCasilla Casilla que se va a dibujar
   * @param pPosX    Posicion en X de la casilla
   * @param pPosY    Posicion en Y de la casilla
   */
  private void dibujarCasilla(Casilla pCasilla, int pPosX, int pPosY, int pAncho, int pAlto) {
    g2d.setColor(Color.YELLOW);
    Stroke oldStroke = g2d.getStroke(); // Store the current stroke
    Stroke newStroke = new BasicStroke(4);
    g2d.setStroke(newStroke);
    g2d.drawRect(pPosX, pPosY, pAncho, pAlto);
    g2d.setStroke(oldStroke);
    if (pCasilla.getEstaMarcada()) {
      g2d.setColor(Color.GREEN);
    } else {
      g2d.setColor(Color.WHITE);
    }
    g2d.fillRect(pPosX + 1, pPosY + 1, pAncho - 1, pAlto - 1);
    g2d.setColor(Color.BLACK);
    int strPosX = (pAncho / 2) - (g2d.getFontMetrics().stringWidth(String.valueOf(pCasilla.getNumero())) / 2);
    int strPosY = (pAlto / 2) + (g2d.getFontMetrics().getHeight() / 2);
    g2d.drawString(String.valueOf(pCasilla.getNumero()), pPosX + strPosX, pPosY + strPosY);
    g2d.setColor(Color.BLACK);
    g2d.drawRect(pPosX, pPosY, pAncho, pAlto);
  }


  /**
   * Metodo que dibuja las casillas del carton
   *
   * @param pCasillas Casillas del carton
   */
  private void dibujarCasillas(Casilla[][] pCasillas) {
    int anchoCasilla = 90;
    int altoCasilla = 100;
    int xInicial = ((width / 2) - (450 / 2)); // 450 = 90 * 5
    int yInicial = 75;
    for (int fila = 0, yPos = yInicial; fila < 5; fila++, yPos += altoCasilla) {
      for (int columna = 0, xPos = xInicial; columna < 5; columna++, xPos += anchoCasilla) {
        dibujarCasilla(pCasillas[fila][columna], xPos, yPos, anchoCasilla, altoCasilla);
      }
    }
  }

  public void guardarImagen(String pDireccion, String pIdCarton) {
    try {
      javax.imageio.ImageIO.write(imagen, "png", new java.io.File(pDireccion + pIdCarton + ".png"));
    } catch (java.io.IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
