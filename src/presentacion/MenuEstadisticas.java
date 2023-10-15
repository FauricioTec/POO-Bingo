package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuEstadisticas extends JFrame {

  private JPanel pnlPrincipal;
  private JPanel pnlComponentes;
  private JButton btnGraficaBarras;
  private JButton btnGraficaCircular;
  private JButton btnWordCloud;
  private JLabel lblEstadisticas;
  private JButton btnRegresar;

  public MenuEstadisticas() {
    super("Estadisticas");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponentes();
  }

  private void initComponentes() {
    setContentPane(pnlPrincipal);
    btnGraficaBarras.addActionListener(e -> presionarBtnGraficaBarras());
    btnGraficaCircular.addActionListener(e -> presionarBtnGraficaCircular());
    btnRegresar.addActionListener(e -> presionarBtnRegresar());
  }

  private void presionarBtnGraficaBarras() {
    GraficaBarras graficaBarras = new GraficaBarras();
    graficaBarras.setVisible(true);
    dispose();
  }

  private void presionarBtnGraficaCircular() {
    GraficaCircular graficaCircular = new GraficaCircular();
    graficaCircular.setVisible(true);
    dispose();
  }

  private void presionarBtnRegresar() {
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    menuPrincipal.setVisible(true);
    dispose();
  }
}
