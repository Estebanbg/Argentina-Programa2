package TrabajoIntegrador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class InsertarDatosMySQL {
    public static void almacenarPuntajes(List<String> participantes, Map<String, Integer> puntosPorParticipante) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticosdeportivos", "root",  "admin");
            String insertSql = "INSERT INTO puntajes (nombre, puntaje) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertSql);
            for (String participante : participantes) {
                int puntos = puntosPorParticipante.get(participante);
                stmt.setString(1, participante);
                stmt.setInt(2, puntos);
                stmt.executeUpdate();

            }
            System.out.println("Puntajes insertados correctamente.");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar los puntajes.");
            e.printStackTrace();
        }
    }






    public static void almacenarResultados(List<Partido> partidos) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticosdeportivos", "root",  "admin");
            String insertSql = "INSERT INTO resultado_partidos (equipo1, equipo2, ganador) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertSql);
            for (Partido partido : partidos) {
                stmt.setString(1, partido.getEquipo1());
                stmt.setString(2, partido.getEquipo2());
                stmt.setString(3, partido.getGanador());
                stmt.executeUpdate();

            }
            System.out.println("Resultados insertados correctamente.");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar los resultados.");
            e.printStackTrace();
        }
    }
    public static void almacenarPronosticos(List<Pronostico> pronosticos) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticosdeportivos", "root",  "admin");
            String insertSql = "INSERT INTO pronosticos (participante, equipo1, equipo2, prediccionganador) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertSql);
            for (Pronostico pronostico : pronosticos) {
                stmt.setString(1, pronostico.getParticipante());
                stmt.setString(2, pronostico.getEquipo1());
                stmt.setString(3, pronostico.getEquipo2());
                stmt.setString(4, pronostico.getPrediccion());
                stmt.executeUpdate();

            }
            System.out.println("Pronósticos insertados correctamente.");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar los pronósticos.");
            e.printStackTrace();
        }
    }

}
