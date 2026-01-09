package com.example.motor_reglas_priorizacion.infrastructure.repository;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Solicitud.
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}
