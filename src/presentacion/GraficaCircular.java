package presentacion;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import utilidad.FrecuenciaConfiguracion;

public class GraficaCircular extends JFrame {

  private JPanel pnlPrincipal;
  private JButton btnRegresar;
  private JPanel pnlGrafica;

  public GraficaCircular() {
    super("Grafica Circular");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponentes();
  }

  private void initComponentes() {
    setContentPane(pnlPrincipal);
    btnRegresar.addActionListener(e -> presionarBtnRegresar());
    JFreeChart chart = ChartFactory.createPieChart(
        "Historico de Configuraciones",
        createDataset(),
        true,
        true,
        false);
    ChartPanel chartPanel = new ChartPanel(chart);
    pnlGrafica.add(chartPanel);

  }

  private static PieDataset createDataset() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    ArrayList<FrecuenciaConfiguracion> frecuenciaConfiguraciones = FrecuenciaConfiguracion.obtenerFrecuenciaConfiguracion();
    for (FrecuenciaConfiguracion frecuenciaConfiguracion : frecuenciaConfiguraciones) {
      dataset.setValue(frecuenciaConfiguracion.getConfiguracion().toString(), frecuenciaConfiguracion.getFrecuencia());
    }
    return dataset;
  }

  private void presionarBtnRegresar() {
    MenuEstadisticas menuEstadisticas = new MenuEstadisticas();
    menuEstadisticas.setVisible(true);
    dispose();
  }
}
