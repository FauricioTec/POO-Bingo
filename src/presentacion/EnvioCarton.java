package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logicadenegocios.Juego;
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
    btnRegresar.addActionListener(e -> presionarBtnRegresar());
  }

  private void presionarBtnRegresar() {
    new MenuPartida(partida);
    dispose();
  }
}
