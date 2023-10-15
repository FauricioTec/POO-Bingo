package presentacion;

import java.util.PrimitiveIterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logicadenegocios.Partida;

public class MenuPartida extends javax.swing.JFrame {

  private JPanel pnlComponentes;
  private JButton btnIniciarPartida;
  private JLabel lblMenu;
  private JButton btnEnviarCarton;
  private JButton btnMenuPrincipal;
  private JPanel pnlPrincipal;
  private JButton btnConsultarCarton;
  private Partida partida;

  public MenuPartida(Partida pPartida) {
    super("Menu Partida");
    partida = pPartida;
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(600, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponents();
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    btnIniciarPartida.addActionListener(e -> presionarBtnIniciarPartida());
    btnConsultarCarton.addActionListener(e -> presionarBtnConsultarCarton());
    btnEnviarCarton.addActionListener(e -> presionarBtnEnviarCarton());
    btnMenuPrincipal.addActionListener(e -> presionarBtnMenuPrincipal());
  }

  private void presionarBtnIniciarPartida() {
    InicioJuego inicioJuego = new InicioJuego(partida);
    inicioJuego.setVisible(true);
    dispose();
  }

  private void presionarBtnConsultarCarton() {
    ConsultaCarton consultaCarton = new ConsultaCarton(partida);
    consultaCarton.setVisible(true);
    dispose();
  }

  private void presionarBtnEnviarCarton() {
    EnvioCarton envioCarton = new EnvioCarton(partida);
    envioCarton.setVisible(true);
    dispose();
  }

  private void presionarBtnMenuPrincipal() {
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    menuPrincipal.setVisible(true);
    dispose();
  }
}
