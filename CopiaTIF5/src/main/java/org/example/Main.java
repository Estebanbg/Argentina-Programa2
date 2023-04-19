package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main {

    public static void main(String[] args) {
        String resultadoscsv = "resultados.csv";
        List<Partido> resultados = LeerResultados.obtenerGanadores(resultadoscsv);

        String pronosticoscsv = "pronosticos.csv";
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
                        resultado.getGanador().equals(pronostico.getResultado())) {
                    puntos++;
                    break;
                }
            }
            puntosPorParticipante.put(participante, puntos);
        }

        for (String participante : participantes) {
            int puntos = puntosPorParticipante.get(participante);
            System.out.println(participante + ": " + puntos + " puntos");
        }
    }

}