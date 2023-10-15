package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logicadenegocios.Configuracion;
import logicadenegocios.Juego;
import logicadenegocios.Jugador;
import utilidad.FrecuenciaConfiguracion;
import utilidad.NumeroApariciones;

public class ConexionBaseDatos {

  private static String url = "jdbc:sqlite:database.sqlite";

  public static void insertarJugador(Jugador pJugador) {
    try {
      Connection conexion = DriverManager.getConnection(url);

      Statement stmt = conexion.createStatement();

      String sql =
          "INSERT INTO Jugador (cedula, nombre, email)" + "VALUES (" + pJugador.getCedula() + ", '"
              + pJugador.getNombre() + "', '" + pJugador.getEmail() + "');";

      stmt.executeUpdate(sql);

      stmt.close();
      conexion.close();

    } catch (SQLException e) {
      throw new RuntimeException("Error al insertar el jugador");
    }
  }

  public static ArrayList<Jugador> obtenerJugadores() {
    ArrayList<Jugador> jugadores = new ArrayList<>();

    try {
      Connection conexion = DriverManager.getConnection(url);
      Statement stmt = conexion.createStatement();

      String sql = "SELECT * FROM Jugador;";
      ResultSet result = stmt.executeQuery(sql);

      while (result.next()) {
        int cedula = result.getInt("cedula");
        String nombre = result.getString("nombre");
        String email = result.getString("email");
        jugadores.add(new Jugador(nombre, cedula, email));
      }
      stmt.close();
      conexion.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return jugadores;
  }

  private static int obtenerIdConfiguracion(Configuracion pConfiguracion) {
    int id = 0;
    try {
      Connection conexion = DriverManager.getConnection(url);

      Statement stmt = conexion.createStatement();

      String sql = "SELECT id FROM Configuracion "
          + "WHERE " + "configuracion = '" + pConfiguracion.toString() + "';";

      id = stmt.executeQuery(sql).getInt("id");

      stmt.close();
      conexion.close();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return id;
  }


  private static void insertarNumerosCantados(int pIdJuego, ArrayList<Integer> pNumerosCantados) {
    try {
      Connection conexion = DriverManager.getConnection(url);

      Statement stmt = conexion.createStatement();

      for (int numero : pNumerosCantados) {
        String sql =
            "INSERT INTO NumerosCantados (numero, juego_id)" +
                "VALUES (" + numero + ", " + pIdJuego + ");";

        stmt.executeUpdate(sql);
      }

      stmt.close();
      conexion.close();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void insertarJuego(Juego pJuego) {
    try {
      Connection conexion = DriverManager.getConnection(url);

      Statement stmt = conexion.createStatement();

      int idConfiguracion = obtenerIdConfiguracion(pJuego.getConfiguracion());

      String sql =
          "INSERT INTO Juego (fecha, hora, configuracion_id)" +
              "VALUES ('" + pJuego.getFecha() + "', '" + pJuego.getHora() + "', " + idConfiguracion
              + ");";

      stmt.executeUpdate(sql);

      ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid();");
      int idJuego = rs.getInt(1);

      stmt.close();
      conexion.close();

      insertarNumerosCantados(idJuego, pJuego.getNumerosCantados());

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static ArrayList<NumeroApariciones> obtenerTopNumeros() {
    ArrayList<NumeroApariciones> topNumeros = new ArrayList<>();
    try {
      Connection conexion = DriverManager.getConnection(url);
      Statement stmt = conexion.createStatement();

      String sql = "SELECT numero, COUNT(numero) AS apariciones " +
          "FROM NumerosCantados " +
          "GROUP BY numero " +
          "ORDER BY apariciones " +
          "DESC LIMIT 10;";
      ResultSet result = stmt.executeQuery(sql);

      while (result.next()) {
        int numero = result.getInt("numero");
        int apariciones = result.getInt("apariciones");
        topNumeros.add(new NumeroApariciones(numero, apariciones));
      }
      stmt.close();
      conexion.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return topNumeros;
  }

  public static ArrayList<FrecuenciaConfiguracion> obtenerFrecuenciaConfiguracion() {
    ArrayList<FrecuenciaConfiguracion> frecuenciaConfiguracion = new ArrayList<>();
    try {
      Connection conexion = DriverManager.getConnection(url);
      Statement stmt = conexion.createStatement();

      String sql = "SELECT C.configuracion, COUNT(J.id) AS frecuencia " +
          "FROM Configuracion C " +
          "LEFT JOIN Juego J ON C.id = J.configuracion_id " +
          "GROUP BY C.configuracion;";
      ResultSet result = stmt.executeQuery(sql);

      while (result.next()) {
        String configuracion = result.getString("configuracion");
        int frecuencia = result.getInt("frecuencia");
        frecuenciaConfiguracion.add(
            new FrecuenciaConfiguracion(Configuracion.toConfiguracion(configuracion), frecuencia));
      }

      stmt.close();
      conexion.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return frecuenciaConfiguracion;
  }

}
