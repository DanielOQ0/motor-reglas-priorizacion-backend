-- Datos de prueba para la base de datos H2
INSERT INTO solicitud (tipo, prioridad_manual, fecha_creacion, usuario, descripcion)
VALUES
('INCIDENTE', 1, '2026-01-01 10:00:00', 'admin', 'Servidor principal caido'),
('REQUERIMIENTO', 3, '2026-01-05 14:30:00', 'jperez', 'Nuevo reporte mensual'),
('CONSULTA', 4, '2026-01-08 09:15:00', 'mgarcia', 'Como exportar datos?'),
('INCIDENTE', 2, '2026-01-07 16:45:00', 'lrodriguez', 'Error en login');

