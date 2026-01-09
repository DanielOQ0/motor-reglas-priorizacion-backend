package com.example.motor_reglas_priorizacion.domain.rules;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import org.springframework.stereotype.Component;

/**
 * Regla de priorización basada en la prioridad manual asignada.
 * Respeta la urgencia indicada por el usuario.
 * 
 * Peso: 25%
 * Lógica:
 *   - Puntaje = (6 - prioridadManual) * 20
 *   - Prioridad 1 → 100 puntos
 *   - Prioridad 5 → 20 puntos
 */
@Component
public class ReglaPrioridadManual implements ReglaPriorizacion {

    private static final double PESO = 0.25;
    private static final String NOMBRE = "Regla por Prioridad Manual";
    private static final int BASE_CALCULO = 6;
    private static final int MULTIPLICADOR = 20;

    @Override
    public double calcularPuntaje(Solicitud solicitud) {
        Integer prioridadManual = solicitud.getPrioridadManual();
        
        // Prioridad 1 = (6-1) * 20 = 100
        // Prioridad 5 = (6-5) * 20 = 20
        return (BASE_CALCULO - prioridadManual) * MULTIPLICADOR;
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

