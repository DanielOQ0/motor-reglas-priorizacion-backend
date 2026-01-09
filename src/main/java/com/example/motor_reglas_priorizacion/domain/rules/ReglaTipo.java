package com.example.motor_reglas_priorizacion.domain.rules;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import com.example.motor_reglas_priorizacion.domain.model.TipoSolicitud;
import org.springframework.stereotype.Component;

/**
 * Regla de priorización basada en el tipo de solicitud.
 *
 */
@Component
public class ReglaTipo implements ReglaPriorizacion {

    private static final double PESO = 0.40;
    private static final String NOMBRE = "Regla por Tipo de Solicitud";

    @Override
    public double calcularPuntaje(Solicitud solicitud) {
        TipoSolicitud tipo = solicitud.getTipo();
        
        return switch (tipo) {
            case INCIDENTE -> 100.0;
            case REQUERIMIENTO -> 50.0;
            case CONSULTA -> 25.0;
        };
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

