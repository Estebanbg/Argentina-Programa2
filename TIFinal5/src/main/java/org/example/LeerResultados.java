package org.example;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.util.ArrayList;
import java.util.List;


public class LeerResultados {

    public static List<Partido> obtenerGanadores(String csvArchivo) {
        List<Partido> ganadores = new ArrayList<>();
        String[] fila;
        int golesEquipo1, golesEquipo2;
        String equipo1, equipo2, ganador;

        try (CSVReader reader = new CSVReader(new FileReader(csvArchivo))) {
            reader.skip(1); // saltar la primera fila (encabezados de columna)

            while ((fila = reader.readNext()) != null) {
                // extraer información de la fila actual
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
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al leer el archivo CSV: se esperaba un número entero pero se encontró otra cosa.");
        }

        return ganadores;
    }


}
