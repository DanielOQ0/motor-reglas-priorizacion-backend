package com.example.motor_reglas_priorizacion.domain.rules;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Regla de priorización basada en la antigüedad de la solicitud.
 * Evita que las solicitudes queden olvidadas.
 * 
 * Peso: 35%
 * Lógica:
 *   - Calcular días desde fechaCreacion hasta hoy
 *   - Puntaje = min(diasAntiguedad * 5, 100)
 *   - Máximo 100 puntos (20+ días)
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
        
        // Asegurar que no sea negativo (por si la fecha es futura)
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

