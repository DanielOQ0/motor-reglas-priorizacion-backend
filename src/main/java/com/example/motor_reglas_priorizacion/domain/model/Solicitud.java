package com.example.motor_reglas_priorizacion.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad que representa una solicitud en el sistema.
 */
@Entity
@Table(name = "solicitud")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo 'tipo' es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSolicitud tipo;

    @NotNull(message = "El campo 'prioridadManual' es obligatorio")
    @Min(value = 1, message = "La prioridad manual debe ser al menos 1")
    @Max(value = 5, message = "La prioridad manual debe ser como máximo 5")
    @Column(name = "prioridad_manual", nullable = false)
    private Integer prioridadManual;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @NotBlank(message = "El campo 'usuario' es obligatorio")
    @Size(max = 100, message = "El usuario no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String usuario;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Column(length = 500)
    private String descripcion;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }
}

