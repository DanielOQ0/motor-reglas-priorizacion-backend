package com.example.motor_reglas_priorizacion.domain.rules;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import org.springframework.stereotype.Component;

/**
 * Regla de priorización basada en la prioridad manual asignada.
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

