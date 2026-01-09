package com.example.motor_reglas_priorizacion.application.dto;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import com.example.motor_reglas_priorizacion.domain.model.TipoSolicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para respuestas que incluyen una solicitud con su prioridad calculada.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudResponse {

    private Long id;
    private TipoSolicitud tipo;
    private Integer prioridadManual;
    private LocalDateTime fechaCreacion;
    private String usuario;
    private String descripcion;
    private Double prioridadCalculada;

    /**
     * Crea un SolicitudResponse a partir de una entidad Solicitud y su prioridad calculada.
     */
    public static SolicitudResponse fromEntity(Solicitud solicitud, double prioridadCalculada) {
        return SolicitudResponse.builder()
                .id(solicitud.getId())
                .tipo(solicitud.getTipo())
                .prioridadManual(solicitud.getPrioridadManual())
                .fechaCreacion(solicitud.getFechaCreacion())
                .usuario(solicitud.getUsuario())
                .descripcion(solicitud.getDescripcion())
                .prioridadCalculada(prioridadCalculada)
                .build();
    }
}

