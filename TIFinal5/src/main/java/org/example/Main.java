package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main {

    public static void main(String[] args) {
        String csvArchivo = "resultados.csv";
        List<Partido> resultados = LeerResultados.obtenerGanadores(csvArchivo);

        String archivo = "pronosticos.csv";
        LeerPronosticos pronosticos = new LeerPronosticos(archivo);
        Map<String, List<Pronostico>> pronosticosPorParticipante = pronosticos.obtenerPronosticos();

        Map<String, Integer> puntosPorParticipante = new HashMap<>();
        for (String participante : pronosticosPorParticipante.keySet()) {
            List<Pronostico> listaPronosticos = pronosticosPorParticipante.get(participante);
            if (!listaPronosticos.isEmpty()) {
                int puntos = 0;
                for (Pronostico pronostico : listaPronosticos) {
                    for (Partido resultado : resultados) {
                        if (resultado.getEquipo1().equals(pronostico.getEquipo1()) &&
                                resultado.getEquipo2().equals(pronostico.getEquipo2()) &&
                                resultado.getGanador().equals(pronostico.getResultado())) {
                            puntos++;
                            break;
                        }
                    }
                }
                puntosPorParticipante.put(participante, puntos);
            }
        }

        for (String participante : puntosPorParticipante.keySet()) {
            int puntos = puntosPorParticipante.get(participante);
            System.out.println(participante + ": " + puntos + " puntos");
        }
    }
}