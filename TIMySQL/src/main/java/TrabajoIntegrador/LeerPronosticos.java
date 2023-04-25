package TrabajoIntegrador;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerPronosticos {


    public static List<Pronostico> obtenerPronosticos(String pronosticoscsv) {
        List<Pronostico> pronosticos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pronosticoscsv))) {
            // saltar la primera línea (encabezados de columna)
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                // extraer información de la línea actual
                String[] fila = linea.split(",");
                String participante = fila[0];
                String equipo1 = fila[1];
                String equipo2 = fila[5];
                String prediccion = "";
                if (fila[3].equals("X")) {
                    prediccion = "Empate";
                } else if (fila[4].equals("X")) {
                    prediccion = equipo2;
                } else if (fila[2].equals("X")) {
                    prediccion = equipo1;
                }

                // guardar el pronóstico en la lista de pronósticos
                Pronostico pronostico = new Pronostico(participante, equipo1, equipo2, prediccion);
                pronosticos.add(pronostico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pronosticos;
    }


}
