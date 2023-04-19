package org.example;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Pronostico {
    private String participante;
    private String equipo1;
    private String equipo2;
    private String resultado;
}
