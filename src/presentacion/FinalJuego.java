package presentacion;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FinalJuego extends JFrame {

  private JPanel pnlPrincipal;
  private JTextArea taCartonesGanadores;
  private JLabel lblCartonesGanadores;
  private JButton btnTerminarJuego;
  private JLabel lblTitulo;
  private JLabel lblTipoJuego;
  private JLabel lblPremio;
  private String tipoJuego;
  private String premio;
  private ArrayList<String> cartonesGanadores;


  public FinalJuego(String pTipoJuego, String pPremio, ArrayList<String> pCartonesGanadores) {
    super("Fin de Juego");
    tipoJuego = pTipoJuego;
    premio = pPremio;
    cartonesGanadores = pCartonesGanadores;
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    initComponents();
    setResizable(false);
    setVisible(true);
  }

  private void initComponents() {
    setContentPane(pnlPrincipal);
    lblTipoJuego.setText("Tipo de juego: " + tipoJuego);
    lblPremio.setText("Premio: " + premio);
    taCartonesGanadores.setText(cartonesGanadores.toString());
    taCartonesGanadores.setWrapStyleWord(true);
    taCartonesGanadores.setLineWrap(true);
    taCartonesGanadores.setEditable(false);
    btnTerminarJuego.addActionListener(e -> presionarBtnTerminarJuego());
  }

  void presionarBtnTerminarJuego() {
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    menuPrincipal.setVisible(true);
    dispose();
  }
}
