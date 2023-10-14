package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    setVisible(true);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    regresarButton.addActionListener(e -> {
      new MenuPrincipal();
      dispose();
    });
    generarButton.addActionListener(e -> {
      try {
        Partida partida = new Partida(Integer.parseInt(tfCantidad.getText()));
        new MenuPartida(partida);
        dispose();
      } catch (NumberFormatException ex) {
        System.out.println("Error: " + ex.getMessage());
      }
    });
  }

}
