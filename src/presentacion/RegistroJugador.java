package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logicadenegocios.Jugador;
import utilidad.Email;

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
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    btnGuardar.addActionListener(e -> presionarGuardar());
    btnRegresar.addActionListener(e -> presionaregresar());
  }

  private void presionarGuardar() {
    if (tfNombre.getText().isEmpty() || tfCedula.getText().isEmpty() || tfEmail.getText().isEmpty()) {
      javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
      return;
    }
    if (Email.esEmailValido(tfEmail.getText())) {
      javax.swing.JOptionPane.showMessageDialog(this, "El email no es valido");
      return;
    }
    try {
      Jugador jugador = new logicadenegocios.Jugador(tfNombre.getText(),
          Integer.parseInt(tfCedula.getText()), tfEmail.getText());
      jugador.guardarJugador();
      javax.swing.JOptionPane.showMessageDialog(this, "Jugador guardado exitosamente");
      new MenuPrincipal();
      dispose();
    } catch (RuntimeException e) {
      javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }

  private void presionaregresar() {
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    menuPrincipal.setVisible(true);
    dispose();
  }
}
