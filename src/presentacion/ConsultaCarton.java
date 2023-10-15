package presentacion;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import logicadenegocios.Partida;

public class ConsultaCarton extends JFrame {

  private JPanel lblPrincipal;
  private JLabel lblImagen;
  private JButton btnRegresar;
  private JLabel lblTItulo;
  private JTextField tfCartonId;
  private JButton btnConsultar;
  private JLabel lblCartonId;
  private JPanel pnlImagen;
  private Partida partida;

  public ConsultaCarton(Partida pPartida) {
    super("Consulta de Carton");
    partida = pPartida;
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 600);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponents();
  }

  private void initComponents() {
    setContentPane(lblPrincipal);
    btnRegresar.addActionListener(e -> presionarBtnRegresar());
    btnConsultar.addActionListener(e -> presionarBtnConsultar());
  }

  void presionarBtnConsultar() {
    if (partida.existeCarton(tfCartonId.getText())) {
      ImageIcon imagen = new ImageIcon(".\\cartones\\" + tfCartonId.getText() + ".png");
      Image img = imagen.getImage();
      Image newImg = img.getScaledInstance(400, 420, Image.SCALE_SMOOTH);
      ImageIcon newIcon = new ImageIcon(newImg);
      lblImagen.setIcon(newIcon);
    } else {
      JOptionPane.showMessageDialog(null, "El carton no existe", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  void presionarBtnRegresar() {
    MenuPartida menuPartida = new MenuPartida(partida);
    menuPartida.setVisible(true);
    dispose();
  }
}
