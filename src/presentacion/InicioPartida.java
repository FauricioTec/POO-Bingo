package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import logicadenegocios.Partida;

public class InicioPartida extends JFrame {

  private JPanel pnlPrincipal;
  private JTextField tfCantidad;
  private JLabel lblCantidad;
  private JButton regresarButton;
  private JButton generarButton;
  private JLabel lblTitulo;

  public InicioPartida() {
    super("Iniciar Partida");
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(400, 200);
    setLocationRelativeTo(null);
    initComponents();
    setResizable(false);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    regresarButton.addActionListener(e -> presionarBtnRegresar());
    generarButton.addActionListener(e -> presionarBtnGenerar());
  }

  void presionarBtnGenerar() {
    try {
      int cantidad = Integer.parseInt(tfCantidad.getText());
      Partida partida = new Partida(cantidad);
      MenuPartida menuPartida = new MenuPartida(partida);
      menuPartida.setVisible(true);
      dispose();
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(null, "La cantidad debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  void presionarBtnRegresar() {
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    menuPrincipal.setVisible(true);
    dispose();
  }
}
