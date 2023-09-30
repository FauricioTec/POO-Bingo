package logicadenegocios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Clase que se encarga de generar la imagen del carton
 */
public class ImagenCarton extends JPanel {

  private final Graphics2D g2d;
  int width;
  int height;
  BufferedImage imagen;

  public ImagenCarton(Casilla[][] pCasillas, String pIdCarton) {
    width = 500;
    height = 600;
    imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    g2d = imagen.createGraphics();

    dibujar(pCasillas);
  }


  private void dibujar(Casilla[][] pCasillas) {
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fillRect(0, 0, width, height);

    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, 24));
    g2d.drawString("BINGO", width / 2 - 30, 30);

    dibujarCasillas(pCasillas);
  }

  /**
   * Metodo que dibuja las casillas del carton
   *
   * @param pCasillas Casillas del carton
   */
  private void dibujarCasillas(Casilla[][] pCasillas) {
    int x = 10;
    int y = 50;
    int width = 95;
    int height = 100;
    g2d.setColor(Color.BLACK);
    for (int fila = 0; fila < 5; fila++) {
      for (int columna = 0; columna < 5; columna++) {
        g2d.drawRect(x, y, width, height);
        g2d.drawString(String.valueOf(pCasillas[fila][columna].getNumero()), x + 35, y + 60);
        x += width;
      }
      x = 10;
      y += height;
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
