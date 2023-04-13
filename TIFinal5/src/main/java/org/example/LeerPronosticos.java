package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class LeerPronosticos {
    private String archivo;

    public LeerPronosticos(String archivo) {
        this.archivo = archivo;
    }

    public Map<String, List<Pronostico>> obtenerPronosticos() {
        Map<String, List<Pronostico>> pronosticosPorParticipante = new HashMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
            reader.skip(1);
            String[] fila;
            while ((fila = reader.readNext()) != null) {
                String participante = fila[0];
                String equipo1 = fila[1];
                String equipo2 = fila[5];
                String resultado = "";
                if (fila[3].equals("X")) {
                    resultado = "Empate";
                } else if (fila[4].equals("X")) {
                    resultado = equipo2;
                } else if (fila[2].equals("X")) {
                    resultado = equipo1;
                }
                Pronostico pronostico = new Pronostico(participante, equipo1, equipo2, resultado);
                if (!pronosticosPorParticipante.containsKey(participante)) {
                    pronosticosPorParticipante.put(participante, new ArrayList<>());
                }
                pronosticosPorParticipante.get(participante).add(pronostico);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return pronosticosPorParticipante;
    }
}
