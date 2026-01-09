package com.example.motor_reglas_priorizacion.infrastructure.exception;

/**
 * Excepción lanzada cuando no se encuentra una solicitud.
 */
public class SolicitudNotFoundException extends RuntimeException {

    public SolicitudNotFoundException(Long id) {
        super("Solicitud no encontrada con ID: " + id);
    }

    public SolicitudNotFoundException(String message) {
        super(message);
    }
}

