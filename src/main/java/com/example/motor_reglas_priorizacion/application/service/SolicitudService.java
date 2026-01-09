package com.example.motor_reglas_priorizacion.application.service;

import com.example.motor_reglas_priorizacion.application.dto.SolicitudRequest;
import com.example.motor_reglas_priorizacion.application.dto.SolicitudResponse;
import com.example.motor_reglas_priorizacion.application.engine.PriorizacionEngine;
import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import com.example.motor_reglas_priorizacion.infrastructure.exception.SolicitudNotFoundException;
import com.example.motor_reglas_priorizacion.infrastructure.repository.SolicitudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación para gestionar solicitudes.
 */
@Service
@Transactional
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final PriorizacionEngine priorizacionEngine;

    public SolicitudService(SolicitudRepository solicitudRepository, PriorizacionEngine priorizacionEngine) {
        this.solicitudRepository = solicitudRepository;
        this.priorizacionEngine = priorizacionEngine;
    }

    /**
     * Crea una nueva solicitud.
     */
    public SolicitudResponse crear(SolicitudRequest request) {
        Solicitud solicitud = Solicitud.builder()
                .tipo(request.getTipo())
                .prioridadManual(request.getPrioridadManual())
                .usuario(request.getUsuario())
                .descripcion(request.getDescripcion())
                .build();

        Solicitud guardada = solicitudRepository.save(solicitud);
        double prioridadCalculada = priorizacionEngine.calcularPrioridad(guardada);

        return SolicitudResponse.fromEntity(guardada, prioridadCalculada);
    }

    /**
     * Obtiene todas las solicitudes.
     */
    @Transactional(readOnly = true)
    public List<SolicitudResponse> listarTodas() {
        return solicitudRepository.findAll().stream()
                .map(solicitud -> {
                    double prioridad = priorizacionEngine.calcularPrioridad(solicitud);
                    return SolicitudResponse.fromEntity(solicitud, prioridad);
                })
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las solicitudes ordenadas por prioridad calculada (descendente).
     */
    @Transactional(readOnly = true)
    public List<SolicitudResponse> listarPriorizadas() {
        return solicitudRepository.findAll().stream()
                .map(solicitud -> {
                    double prioridad = priorizacionEngine.calcularPrioridad(solicitud);
                    return SolicitudResponse.fromEntity(solicitud, prioridad);
                })
                .sorted(Comparator.comparing(SolicitudResponse::getPrioridadCalculada).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una solicitud por su ID.
     */
    @Transactional(readOnly = true)
    public SolicitudResponse obtenerPorId(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNotFoundException(id));

        double prioridadCalculada = priorizacionEngine.calcularPrioridad(solicitud);
        return SolicitudResponse.fromEntity(solicitud, prioridadCalculada);
    }
}

