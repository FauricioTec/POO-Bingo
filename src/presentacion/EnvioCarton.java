package presentacion;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logicadenegocios.Partida;

public class EnvioCarton extends JFrame {

  private JPanel pnlPrincipal;
  private JLabel lblTitulo;
  private JLabel lblCantidad;
  private JTextField tfCantidad;
  private JLabel lblCedula;
  private JTextField tfCedula;
  private JButton btnEnviar;
  private JButton btnRegresar;
  private Partida partida;

  public EnvioCarton(Partida pPartida) {
    super("Envio de Carton");
    partida = pPartida;
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(400, 250);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponents();
    setVisible(true);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    btnEnviar.addActionListener(e -> presionarBtnEnviar());
    btnRegresar.addActionListener(e -> presionarBtnRegresar());
  }

  private void presionarBtnEnviar() {
    if (tfCantidad.getText().isEmpty() || tfCedula.getText().isEmpty()) {
      javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
      return;
    }
    try {
      partida.enviarCartonesAJugador(Integer.parseInt(tfCantidad.getText()),
          Integer.parseInt(tfCedula.getText()));
      javax.swing.JOptionPane.showMessageDialog(this, "Carton enviado exitosamente");
    } catch (RuntimeException | MessagingException | IOException e) {
      javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
          javax.swing.JOptionPane.ERROR_MESSAGE);
    }
  }

  private void presionarBtnRegresar() {
    MenuPartida menuPartida = new MenuPartida(partida);
    menuPartida.setVisible(true);
    dispose();
  }
}
