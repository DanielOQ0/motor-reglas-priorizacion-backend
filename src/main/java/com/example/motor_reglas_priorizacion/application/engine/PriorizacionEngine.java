package com.example.motor_reglas_priorizacion.application.engine;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import com.example.motor_reglas_priorizacion.domain.rules.ReglaPriorizacion;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Motor de priorización que aplica todas las reglas configuradas para calcular
 * la prioridad de una solicitud.
 *
 */
@Service
public class PriorizacionEngine {

    private final List<ReglaPriorizacion> reglas;

    /**
     * Constructor con inyección automática de todas las reglas registradas como @Component.
     */
    public PriorizacionEngine(List<ReglaPriorizacion> reglas) {
        this.reglas = reglas;
    }

    /**
     * Calcula la prioridad total de una solicitud aplicando todas las reglas.
     * 
     * @param solicitud la solicitud a evaluar
     * @return puntaje total de prioridad (0-100)
     */
    public double calcularPrioridad(Solicitud solicitud) {
        return reglas.stream()
                .mapToDouble(regla -> regla.calcularPuntaje(solicitud) * regla.getPeso())
                .sum();
    }

}

