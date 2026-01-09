package com.example.motor_reglas_priorizacion.domain.rules;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;

/**
 * Interfaz que define el contrato para las reglas de priorización.
 * Cada implementación representa una regla específica del motor de reglas.
 */
public interface ReglaPriorizacion {

    /**
     * Calcula el puntaje de la solicitud según esta regla.
     * @param solicitud la solicitud a evaluar
     * @return valor entre 0 y 100
     */
    double calcularPuntaje(Solicitud solicitud);

    /**
     * Peso de esta regla en el cálculo final.
     * @return valor entre 0.0 y 1.0
     */
    double getPeso();

    /**
     * Nombre descriptivo de la regla.
     * @return nombre de la regla
     */
    String getNombre();
}

