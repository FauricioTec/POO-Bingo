package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroJugador extends JFrame {

  private JPanel pnlPrincipal;
  private JButton btnRegresar;
  private JButton btnGuardar;
  private JTextField tfNombre;
  private JTextField tfCedula;
  private JTextField tfEmail;
  private JLabel lblTitulo;
  private JLabel lblEmail;
  private JLabel lblCedula;
  private JLabel lblNombre;

  public RegistroJugador() {
    super("Registro de Jugador");
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponents();
    setVisible(true);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    btnRegresar.addActionListener(e -> {
      new MenuPrincipal();
      dispose();
    });
  }
}
