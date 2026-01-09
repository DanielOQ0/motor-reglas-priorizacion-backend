package com.example.motor_reglas_priorizacion.application.dto;

import com.example.motor_reglas_priorizacion.domain.model.TipoSolicitud;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para recibir solicitudes de creacion de una nueva solicitud.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudRequest {

    @NotNull(message = "El campo 'tipo' es obligatorio")
    private TipoSolicitud tipo;

    @NotNull(message = "El campo 'prioridadManual' es obligatorio")
    @Min(value = 1, message = "La prioridad manual debe ser al menos 1")
    @Max(value = 5, message = "La prioridad manual debe ser como maximo 5")
    private Integer prioridadManual;

    @NotBlank(message = "El campo 'usuario' es obligatorio")
    @Size(max = 100, message = "El usuario no puede exceder 100 caracteres")
    private String usuario;

    @Size(max = 500, message = "La descripcion no puede exceder 500 caracteres")
    private String descripcion;
}
