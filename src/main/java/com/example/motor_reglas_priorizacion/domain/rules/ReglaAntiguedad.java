package com.example.motor_reglas_priorizacion.domain.rules;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Regla de priorización basada en la antigüedad de la solicitud.
 *
 */
@Component
public class ReglaAntiguedad implements ReglaPriorizacion {

    private static final double PESO = 0.35;
    private static final String NOMBRE = "Regla por Antigüedad";
    private static final int PUNTOS_POR_DIA = 5;
    private static final int PUNTAJE_MAXIMO = 100;

    @Override
    public double calcularPuntaje(Solicitud solicitud) {
        LocalDateTime fechaCreacion = solicitud.getFechaCreacion();
        LocalDateTime ahora = LocalDateTime.now();
        
        long diasAntiguedad = ChronoUnit.DAYS.between(fechaCreacion, ahora);

        diasAntiguedad = Math.max(0, diasAntiguedad);
        
        double puntaje = diasAntiguedad * PUNTOS_POR_DIA;
        
        return Math.min(puntaje, PUNTAJE_MAXIMO);
    }

    @Override
    public double getPeso() {
        return PESO;
    }

    @Override
    public String getNombre() {
        return NOMBRE;
    }
}

