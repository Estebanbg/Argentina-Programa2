package org.example;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Partido {
    private final String equipo1;
    private final String equipo2;
    private final String ganador;
}
