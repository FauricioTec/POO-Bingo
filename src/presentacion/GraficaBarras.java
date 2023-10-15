package presentacion;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import utilidad.NumeroApariciones;

public class GraficaBarras extends JFrame {

  private JPanel pnlGrafica;
  private JButton btnRegresar;
  private JPanel pnlPrincipal;

  public GraficaBarras() {
    super("Grafica de Barras");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    initComponentes();
  }

  private void initComponentes() {
    setContentPane(pnlPrincipal);
    btnRegresar.addActionListener(e -> presionarBtnRegresar());
    JFreeChart chart = ChartFactory.createBarChart(
        "Top 10 Numeros Cantados",
        "Numeros",
        "Cantidad de Veces Cantado",
        createDataset(),
        PlotOrientation.VERTICAL,
        true, true, false);

    ChartPanel chartPanel = new ChartPanel(chart);
    pnlGrafica.add(chartPanel);
  }

  private CategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    ArrayList<NumeroApariciones> numeroApariciones = NumeroApariciones.obtenerTopNumeroApariciones();
    for (NumeroApariciones numeroAparicion : numeroApariciones) {
      dataset.addValue(numeroAparicion.getApariciones(), "Numeros",
          numeroAparicion.getNumero() + "");
    }
    return dataset;
  }

  private void presionarBtnRegresar() {
    MenuEstadisticas menuEstadisticas = new MenuEstadisticas();
    menuEstadisticas.setVisible(true);
    dispose();
  }
}
