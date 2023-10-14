package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import logicadenegocios.Juego;

public class FrameJuego extends JFrame {

  private JPanel pnlPrincipal;
  private JTextArea taNumerosCantados;
  private JButton btnCantarNumero;
  private JLabel lblCantidadCartones;
  private JLabel lblTitulo;
  private JLabel lblTipoJuego;
  private JLabel lblPremio;
  private JLabel lblNumerosCantados;
  private JLabel lblCantidadJugadores;
  private Juego juego;

  public FrameJuego(Juego pJuego) {
    super("Juego");
    juego = pJuego;
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 400);
    setLocationRelativeTo(null);
    initComponents();
    setResizable(false);
    setVisible(true);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    lblTipoJuego.setText("Tipo de juego: " + juego.getConfiguracion());
    lblPremio.setText("Premio: " + juego.getPremio());
    lblCantidadCartones.setText("Cartones: " + juego.contarCartones());
    lblCantidadJugadores.setText("Jugadores: " + juego.contarJugadores());
    taNumerosCantados.setText(juego.getNumerosCantados().toString());
    taNumerosCantados.setWrapStyleWord(true);
    taNumerosCantados.setLineWrap(true);
    taNumerosCantados.setEditable(false);
    btnCantarNumero.addActionListener(e -> presionarBtnCantarNumero());
  }

  void presionarBtnCantarNumero() {
    try {
      if (juego.hayGanador()) {
        new FinalJuego(juego.getConfiguracion().toString(), juego.getPremio(), juego.getCartonesGanadores());
        dispose();
      } else {
        juego.cantarNumero();
        taNumerosCantados.setText(juego.getNumerosCantados().toString());
      }
    } catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
  }
}
