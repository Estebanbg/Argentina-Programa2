package TrabajoIntegrador;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LeerResultados {

    public static List<Partido> obtenerGanadores(String resultadoscsv) {
        List<Partido> ganadores = new ArrayList<>();
        String linea;
        int golesEquipo1, golesEquipo2;
        String equipo1, equipo2, ganador;

        try (BufferedReader br = new BufferedReader(new FileReader(resultadoscsv))) {
            // saltar la primera línea (encabezados de columna)
            br.readLine();

            while ((linea = br.readLine()) != null) {
                // extraer información de la línea actual
                String[] fila = linea.split(",");
                equipo1 = fila[1];
                golesEquipo1 = Integer.parseInt(fila[2]);
                golesEquipo2 = Integer.parseInt(fila[3]);
                equipo2 = fila[4];

                // determinar el ganador del partido
                if (golesEquipo1 > golesEquipo2) {
                    ganador = equipo1;
                } else if (golesEquipo2 > golesEquipo1) {
                    ganador = equipo2;
                } else {
                    ganador = "Empate";
                }

                // guardar el resultado del partido en la lista de partidos
                Partido partido = new Partido(equipo1, equipo2, ganador);
                ganadores.add(partido);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al leer el archivo CSV: se esperaba un número entero pero se encontró otra cosa.");
        }

        return ganadores;
    }




}
