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
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    btnRegistrarJugador.addActionListener(e -> presionarBtnRegistrarJugador());
    btnIniciarPartida.addActionListener(e -> presionarBtnIniciarPartida());
    btnMostrarEstadisticas.addActionListener(e -> presionarBtnMostrarEstadisticas());
  }

  private void presionarBtnIniciarPartida() {
    InicioPartida inicioPartida = new InicioPartida();
    inicioPartida.setVisible(true);
    dispose();
  }

  private void presionarBtnRegistrarJugador() {
    RegistroJugador registroJugador = new RegistroJugador();
    registroJugador.setVisible(true);
    dispose();
  }

  private void presionarBtnMostrarEstadisticas() {
    MenuEstadisticas menuEstadisticas = new MenuEstadisticas();
    menuEstadisticas.setVisible(true);
    dispose();
  }
}
