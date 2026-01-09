package com.example.motor_reglas_priorizacion.application.engine;

import com.example.motor_reglas_priorizacion.domain.model.Solicitud;
import com.example.motor_reglas_priorizacion.domain.model.TipoSolicitud;
import com.example.motor_reglas_priorizacion.domain.rules.ReglaPriorizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriorizacionEngineTest {

    private PriorizacionEngine engine;

    @BeforeEach
    void setUp() {
        ReglaPriorizacion regla1 = new ReglaPriorizacion() {
            @Override
            public double calcularPuntaje(Solicitud solicitud) {
                return 80.0;
            }

            @Override
            public double getPeso() {
                return 0.5;
            }

            @Override
            public String getNombre() {
                return "Regla1";
            }
        };

        ReglaPriorizacion regla2 = new ReglaPriorizacion() {
            @Override
            public double calcularPuntaje(Solicitud solicitud) {
                return 60.0;
            }

            @Override
            public double getPeso() {
                return 0.3;
            }

            @Override
            public String getNombre() {
                return "Regla2";
            }
        };

        engine = new PriorizacionEngine(List.of(regla1, regla2));
    }

    @Test
    void calcularPrioridad_deberiaAplicarTodasLasReglasConSusPesos() {
        Solicitud solicitud = Solicitud.builder()
                .id(1L)
                .tipo(TipoSolicitud.INCIDENTE)
                .prioridadManual(3)
                .fechaCreacion(LocalDateTime.now())
                .usuario("usuario_test")
                .build();

        double prioridad = engine.calcularPrioridad(solicitud);

        // Esperado: (80 * 0.5) + (60 * 0.3) = 40 + 18 = 58
        assertEquals(58.0, prioridad, 0.01);
    }

    @Test
    void calcularPrioridad_conListaVacia_deberiaRetornarCero() {
        PriorizacionEngine engineVacio = new PriorizacionEngine(List.of());
        Solicitud solicitud = Solicitud.builder()
                .id(1L)
                .tipo(TipoSolicitud.REQUERIMIENTO)
                .prioridadManual(1)
                .fechaCreacion(LocalDateTime.now())
                .usuario("usuario_test")
                .build();

        double prioridad = engineVacio.calcularPrioridad(solicitud);

        // Esperado: 0
        assertEquals(0.0, prioridad);
    }

    @Test
    void calcularPrioridad_conUnaRegla_deberiaAplicarSoloEsaRegla() {
        ReglaPriorizacion reglaUnica = new ReglaPriorizacion() {
            @Override
            public double calcularPuntaje(Solicitud solicitud) {
                return 100.0;
            }

            @Override
            public double getPeso() {
                return 1.0;
            }

            @Override
            public String getNombre() {
                return "ReglaUnica";
            }
        };

        PriorizacionEngine engineUnaRegla = new PriorizacionEngine(List.of(reglaUnica));
        Solicitud solicitud = Solicitud.builder()
                .id(1L)
                .tipo(TipoSolicitud.CONSULTA)
                .prioridadManual(5)
                .fechaCreacion(LocalDateTime.now())
                .usuario("usuario_test")
                .build();

        double prioridad = engineUnaRegla.calcularPrioridad(solicitud);

        // Esperado: 100 * 1.0 = 100
        assertEquals(100.0, prioridad, 0.01);
    }
}

