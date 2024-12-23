-- Fake Data for Asignatura
INSERT INTO Asignatura (asignaturaid, nombre, descripcion) VALUES
                                                               (1, 'Mathematics', 'Basic and advanced concepts in Mathematics'),
                                                               (2, 'Physics', 'Introduction to mechanics, thermodynamics, and quantum physics'),
                                                               (3, 'Chemistry', 'Study of elements, compounds, and chemical reactions');

-- Fake Data for Roles
INSERT INTO Roles (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'MENTOR'),
                                 (3, 'ESTUDIANTE');


-- Fake Data for Turno
INSERT INTO Turno (turnoID, turno) VALUES
                                       (1, 1),
                                       (2, 2);

-- Fake Data for Usuario
INSERT INTO Usuario (usuarioid, apellido, contrasenia, correo, nacionalidad, nombre, telefono, role_id)
VALUES
    (1, 'Doe', 'password123', 'johndoe@example.com', 'Peru', 'John', '987654321', 1),
    (2, 'Smith', 'password456', 'janesmith@example.com', 'Peru', 'Jane', '987654322', 2),
    (3, 'Garcia', 'passCarlos', 'carlos.garcia@example.com', 'Spain', 'Carlos', '123123123', 1);

-- Fake Data for Mentor
INSERT INTO Mentor (mentorID, usuarioID, valoracionPromedio, tarifaHora, biografia) VALUES
                                                                                        (1, 1, 4.80, 30, 'Experienced Math tutor with 5 years of experience.'),
                                                                                        (2, 3, 4.60, 25, 'Physics teacher with a passion for explaining complex concepts.');

-- Fake Data for Estudiante
INSERT INTO Estudiante (estudianteID, usuarioID) VALUES
    (1, 2);

-- Fake Data for Certificacion
INSERT INTO Certificacion (certificacionID, nombre, organizacion, descripcion, reconocidainternacionalmente, duracionMeses) VALUES
                                                                                                                                (1, 'Data Science Professional', 'Coursera', 'Certification for Data Science specialization', true, 12),
                                                                                                                                (2, 'Advanced Physics', 'MIT', 'Certification for advanced topics in physics', false, 18);

-- Fake Data for Eventos
INSERT INTO Eventos (evento_ID, titulo, descripcion, fecha, esVirtual, ubicacion) VALUES
                                                                                      (1, 'Math Workshop', 'A workshop for advanced calculus', '2024-10-10', true, 'Online'),
                                                                                      (2, 'Physics Seminar', 'Introduction to quantum mechanics', '2024-11-05', false, 'MIT Campus');

-- Fake Data for SesionMentoria
INSERT INTO SesionMentoria (sesionMentoriaID, mentorID, estudianteID, asignaturaID, dia, horaInicio, horaFinal, webLink, turnoID, precio, created_at, titulo)
VALUES
    (1, 1, 1, 1, '2024-09-15', '10:00', '11:00', 'https://zoom.com/session1', 1, 20, NOW(), 'Math Tutoring Session'),
    (2, 2, 1, 2, '2024-09-16', '14:00', '15:00', 'https://zoom.com/session2', 2, 25, NOW(), 'Physics Tutoring Session');

-- Fake Data for Valoracion
INSERT INTO Valoracion (valoracionID, estudianteID, mentorID, comentario, estrellas) VALUES
                                                                                         (1, 1, 1, 'Great session, very helpful!', 5),
                                                                                         (2, 1, 2, 'Clear explanations, would recommend.', 4);

-- Fake Data for Asignatura_Mentor
INSERT INTO Asignatura_Mentor (asignaturaID, mentorID) VALUES
                                                           (1, 1),
                                                           (2, 2);

-- Fake Data for Asistencia_Evento
INSERT INTO Asistencia_Evento (mentorID, eventoID, asistenciaConfirmada) VALUES
                                                                             (1, 1, true),
                                                                             (2, 2, true);

-- Fake Data for Favorito
INSERT INTO Favorito (favoritoID, estudianteID, mentorID) VALUES
    (1, 1, 1);

-- Fake Data for Mentor_Certificacion
INSERT INTO Mentor_Certificacion (mentorID, certificacionID, fechaObtencion) VALUES
                                                                                 (1, 1, '2023-01-15'),
                                                                                 (2, 2, '2022-08-10');
