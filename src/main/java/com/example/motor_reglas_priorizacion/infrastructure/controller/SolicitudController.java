package com.example.motor_reglas_priorizacion.infrastructure.controller;

import com.example.motor_reglas_priorizacion.application.dto.SolicitudRequest;
import com.example.motor_reglas_priorizacion.application.dto.SolicitudResponse;
import com.example.motor_reglas_priorizacion.application.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar solicitudes.
 */
@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    /**
     * POST /api/solicitudes - Crear una nueva solicitud
     */
    @PostMapping
    public ResponseEntity<SolicitudResponse> crear(@Valid @RequestBody SolicitudRequest request) {
        SolicitudResponse response = solicitudService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/solicitudes - Listar todas las solicitudes
     */
    @GetMapping
    public ResponseEntity<List<SolicitudResponse>> listarTodas() {
        List<SolicitudResponse> solicitudes = solicitudService.listarTodas();
        return ResponseEntity.ok(solicitudes);
    }

    /**
     * GET /api/solicitudes/priorizadas - Listar solicitudes ordenadas por prioridad calculada (descendente)
     */
    @GetMapping("/priorizadas")
    public ResponseEntity<List<SolicitudResponse>> listarPriorizadas() {
        List<SolicitudResponse> solicitudes = solicitudService.listarPriorizadas();
        return ResponseEntity.ok(solicitudes);
    }
}

