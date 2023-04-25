package TrabajoIntegrador;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        // Obtener la ruta absoluta del directorio actual de trabajo
        String rutaActual = new File("").getAbsolutePath();

        // Construir la ruta al archivo resultados.csv
        String resultadoscsv = rutaActual + File.separator + "data(csv)" + File.separator + "resultados.csv";
        List<Partido> resultados = LeerResultados.obtenerGanadores(resultadoscsv);

        // Construir la ruta al archivo pronosticos.csv
        String pronosticoscsv = rutaActual + File.separator + "data(csv)" + File.separator + "pronosticos.csv";
        List<Pronostico> pronosticos = LeerPronosticos.obtenerPronosticos(pronosticoscsv);


        List<String> participantes = new ArrayList<>();
        Map<String, Integer> puntosPorParticipante = new HashMap<>();
        for (Pronostico pronostico : pronosticos) {
            String participante = pronostico.getParticipante();
            if (!participantes.contains(participante)) {
                participantes.add(participante);
                puntosPorParticipante.put(participante, 0);
            }
            int puntos = puntosPorParticipante.get(participante);
            for (Partido resultado : resultados) {
                if (resultado.getEquipo1().equals(pronostico.getEquipo1()) &&
                        resultado.getEquipo2().equals(pronostico.getEquipo2()) &&
                        resultado.getGanador().equals(pronostico.getPrediccion())) {
                    puntos++;
                    break;
                }
            }
            puntosPorParticipante.put(participante, puntos);
        }

        for (String participante : participantes) {
            int puntos = puntosPorParticipante.get(participante);
            System.out.println(participante + ": " + puntos + " puntos ");
        }
        System.out.println("\n");

        InsertarDatosMySQL.almacenarPuntajes(participantes, puntosPorParticipante);
        InsertarDatosMySQL.almacenarResultados(resultados);
        InsertarDatosMySQL.almacenarPronosticos(pronosticos);


    }

}
