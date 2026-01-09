package com.example.motor_reglas_priorizacion.domain.model;

/**
 * Enum que representa los tipos de solicitud disponibles.
 * Cada tipo tiene una prioridad inherente diferente.
 */
public enum TipoSolicitud {
    INCIDENTE,      // Mayor prioridad
    REQUERIMIENTO,  // Prioridad media
    CONSULTA        // Menor prioridad
}
