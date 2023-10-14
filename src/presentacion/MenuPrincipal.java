package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPrincipal extends JFrame {

  private JPanel pnlPrincipal;
  private JPanel pnlComponentes;
  private JButton btnIniciarPartida;
  private JLabel lblMenu;
  private JButton btnRegistrarJugador;
  private JButton btnMostrarEstadisticas;


  public MenuPrincipal() {
    super("Menu Principal");
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(600, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponents();
    setVisible(true);
  }

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MenuPrincipal();
      }
    });
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    btnRegistrarJugador.addActionListener(e -> presionarBtnRegistrarJugador());
    btnIniciarPartida.addActionListener(e -> presionarBtnIniciarPartida());
  }

  private void presionarBtnIniciarPartida() {
    new InicioPartida();
    dispose();
  }

  private void presionarBtnRegistrarJugador() {
    new RegistroJugador();
    dispose();
  }
}
