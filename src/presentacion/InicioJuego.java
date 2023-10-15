package presentacion;

import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logicadenegocios.Configuracion;
import logicadenegocios.Juego;
import logicadenegocios.Partida;

public class InicioJuego extends JFrame {

  private JPanel pnlPrincipal;
  private JComboBox cbConfiguracion;
  private JTextField tfPremio;
  private JButton btnIniciar;
  private JLabel lblConfiguracion;
  private JLabel lblPremio;
  private JLabel lblTitulo;
  private MenuPartida menuPartida;
  private Partida partida;

  public InicioJuego(Partida pPartida) {
    super("Iniciar Juego");
    partida = pPartida;
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(350, 250);
    setLocationRelativeTo(null);
    initComponents();
    setResizable(false);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    cbConfiguracion.addItem(Configuracion.CUATRO_ESQUINAS.toString());
    cbConfiguracion.addItem(Configuracion.CARTON_LLENO.toString());
    cbConfiguracion.addItem(Configuracion.JUGAR_EN_X.toString());
    cbConfiguracion.addItem(Configuracion.JUGAR_EN_Z.toString());
    btnIniciar.addActionListener(e -> presionarBtnIniciar());
  }

  private void presionarBtnIniciar() {
    Juego juego = new Juego(partida, tfPremio.getText(),
        Configuracion.toConfiguracion(Objects.requireNonNull(cbConfiguracion.getSelectedItem()).toString()));
    new FrameJuego(juego);
    dispose();
  }
}
