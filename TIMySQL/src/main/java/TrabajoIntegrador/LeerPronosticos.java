package TrabajoIntegrador;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerPronosticos {


    public static List<Pronostico> obtenerPronosticos(String pronosticoscsv) {
        List<Pronostico> pronosticos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(pronosticoscsv))) {
            reader.skip(1);
            String[] fila;
            while ((fila = reader.readNext()) != null) {
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
                Pronostico pronostico = new Pronostico(participante, equipo1, equipo2, prediccion);
                pronosticos.add(pronostico);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return pronosticos;
    }


}
