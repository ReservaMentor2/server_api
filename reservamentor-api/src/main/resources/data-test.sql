INSERT INTO usuario (usuarioid, nombre, apellido, correo, contrasenia, nacionalidad, telefono, rol)
VALUES (1, 'Carlos', 'López', 'carlos.lopez@mail.com', 'password123', 'Mexicana', '123456789', 'ESTUDIANTE'),
       (2, 'Ana', 'Martínez', 'ana.martinez@mail.com', 'password456', 'Argentina', '987654321', 'MENTOR');

-- Inserciones en la tabla Estudiante
INSERT INTO estudiante (estudianteid, usuarioid)
VALUES (1, 1),
       (2, 2);

-- Inserciones en la tabla Mentor
INSERT INTO mentor (mentorid, usuarioid, valoracionPromedio, tarifaHora, biografia)
VALUES (1, 2, 4.5, 200, 'Mentor de programación con 5 años de experiencia.'),
       (2, 1, 4.8, 150, 'Mentor especializado en bases de datos.');

-- Inserciones en la tabla Asignatura
INSERT INTO asignatura (asignaturaid, nombre, descripcion)
VALUES (1, 'Programación', 'Introducción a la programación en Python.'),
       (2, 'Bases de datos', 'Fundamentos de diseño de bases de datos relacionales.');

-- Inserciones en la tabla Asignatura_Mentor
INSERT INTO asignatura_Mentor (asignaturaid, mentorid)
VALUES (1, 1),
       (2, 2);

-- Inserciones en la tabla Certificacion
INSERT INTO certificacion (certificacionid, nombre, organizacion, descripcion, duracionMeses)
VALUES (1, 'Certificación en Python', 'Udemy', 'Curso avanzado de Python.', 3),
       (2, 'Certificación en SQL', 'Coursera', 'Fundamentos de SQL y bases de datos.', 2);

-- Inserciones en la tabla Mentor_Certificacion
INSERT INTO mentor_Certificacion (mentorid, certificacionid, fechaObtencion)
VALUES (1, 1, '2021-05-10'),
       (2, 2, '2022-03-20');

-- Inserciones en la tabla Eventos
INSERT INTO eventos (evento_id, titulo, descripcion, fecha, esVirtual, ubicacion)
VALUES (1, 'Taller de Programación', 'Taller introductorio de programación en Python.', '2024-09-25', true, 'Zoom'),
       (2, 'Conferencia de Bases de Datos', 'Discusión sobre las últimas tendencias en bases de datos.', '2024-10-10', false, 'Universidad Nacional');

-- Inserciones en la tabla Asistencia_Evento
INSERT INTO asistencia_Evento (mentorid, eventoid, asistenciaConfirmada)
VALUES (1, 1, true),
       (2, 2, false);


-- Inserciones en la tabla turno
INSERT INTO turno (turnoid, turno)
VALUES (1, 1),
       (2, 2);

-- Inserciones en la tabla SesionMentoria
INSERT INTO sesionMentoria (sesionMentoriaid, mentorid, estudianteid, asignaturaid, dia, horaInicio, horaFinal, webLink, turnoid, precio)
VALUES (1, 1, 1, 1, '2024-09-15', '10:00:00', '11:00:00', 'https://zoom.us/meeting/123', 1, 100.00),
       (2, 2, 2, 2, '2024-09-20', '12:00:00', '13:00:00', 'https://zoom.us/meeting/456', 2, 120.00);

-- Inserciones en la tabla Favorito
INSERT INTO favorito (favoritoid, estudianteid, mentorid)
VALUES (1, 1, 1),
       (2, 2, 2);

-- Inserciones en la tabla Valoracion
INSERT INTO valoracion (valoracionid, estudianteid, mentorid, comentario, estrellas)
VALUES (1, 1, 1, 'Excelente mentor, muy claro en sus explicaciones.', 5),
       (2, 2, 2, 'Buen mentor, aunque puede mejorar su puntualidad.',4);

-- Datos para la tabla 'disponibilidad'
INSERT INTO disponibilidad (id, dia, horainit, horafin, mentorid) VALUES
                                                                        (1, 'Lunes', '09:00:00', '12:00:00', 1),
                                                                        (2, 'Martes', '14:00:00', '17:00:00', 2);

