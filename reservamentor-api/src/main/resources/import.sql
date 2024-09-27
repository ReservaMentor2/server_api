-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-09-12 08:00:51.627

-- tables
-- Table: Asignatura
CREATE TABLE Asignatura (
                            asignaturaID int  NOT NULL,
                            nombre varchar(20)  NOT NULL,
                            descripcion varchar(150)  NOT NULL,
                            CONSTRAINT Asignatura_pk PRIMARY KEY (asignaturaID)
);

-- Table: Asignatura_Mentor
CREATE TABLE Asignatura_Mentor (
                                   asignaturaID int  NOT NULL,
                                   mentorID int  NOT NULL,
                                   CONSTRAINT Asignatura_Mentor_pk PRIMARY KEY (asignaturaID,mentorID)
);

-- Table: Asistencia_Evento
CREATE TABLE Asistencia_Evento (
                                   mentorID int  NOT NULL,
                                   eventoID int  NOT NULL,
                                   asistenciaConfirmada boolean  NOT NULL,
                                   CONSTRAINT Asistencia_Evento_pk PRIMARY KEY (mentorID,eventoID)
);

-- Table: Certificacion
CREATE TABLE Certificacion (
                               certificacionID int  NOT NULL,
                               nombre varchar(100)  NOT NULL,
                               organizacion varchar(100)  NOT NULL,
                               descripcion varchar(500)  NOT NULL,
                               reconocidainternacionalmente boolean NOT NULL,
                               duracionMeses int  NOT NULL,
                               CONSTRAINT Certificacion_pk PRIMARY KEY (certificacionID)
);

-- Table: Estudiante
CREATE TABLE Estudiante (
                            estudianteID int  NOT NULL,
                            usuarioID int  NOT NULL,
                            CONSTRAINT Estudiante_pk PRIMARY KEY (estudianteID)
);

-- Table: Eventos
CREATE TABLE Eventos (
                         evento_ID int  NOT NULL,
                         titulo varchar(100)  NOT NULL,
                         descripcion varchar(500)  NOT NULL,
                         fecha date  NOT NULL,
                         esVirtual boolean  NOT NULL,
                         ubicacion varchar(200)  NOT NULL,
                         CONSTRAINT Eventos_pk PRIMARY KEY (evento_ID)
);

-- Table: Favorito
CREATE TABLE Favorito (
                          favoritoID int  NOT NULL,
                          estudianteID int  NOT NULL,
                          mentorID int  NOT NULL,
                          CONSTRAINT Favorito_pk PRIMARY KEY (favoritoID)
);

-- Table: Mentor
CREATE TABLE Mentor (
                        mentorID int  NOT NULL,
                        usuarioID int  NOT NULL,
                        valoracionPromedio decimal(4,2)  NOT NULL,
                        tarifaHora int  NOT NULL,
                        biografia varchar(500)  NOT NULL,
                        CONSTRAINT Mentor_pk PRIMARY KEY (mentorID)
);

-- Table: Mentor_Certificacion
CREATE TABLE Mentor_Certificacion (
                                      mentorID int  NOT NULL,
                                      certificacionID int  NOT NULL,
                                      fechaObtencion date  NOT NULL,
                                      CONSTRAINT Mentor_Certificacion_pk PRIMARY KEY (mentorID,certificacionID)
);

-- Table: SesionMentoria
CREATE TABLE SesionMentoria (
                                sesionMentoriaID int  NOT NULL,
                                mentorID int  NOT NULL,
                                estudianteID int  NOT NULL,
                                asignaturaID int  NOT NULL,
                                dia date  NOT NULL,
                                horaInicio time  NOT NULL,
                                horaFinal time  NOT NULL,
                                webLink varchar(200)  NOT NULL,
                                turnoID int  NOT NULL,
                                precio decimal  NOT NULL,
                                CONSTRAINT SesionMentoria_pk PRIMARY KEY (sesionMentoriaID)
);

-- Table: Usuario
CREATE TABLE Usuario (
                         usuarioID int  NOT NULL,
                         nombre varchar(100)  NOT NULL,
                         apellido varchar(100)  NOT NULL,
                         correo varchar(100)  NOT NULL,
                         contrasenia varchar(60)  NOT NULL,
                         nacionalidad varchar(20)  NOT NULL,
                         telefono varchar(9)  NOT NULL,
                         rol char  NOT NULL,
                         CONSTRAINT Usuario_pk PRIMARY KEY (usuarioID)
);

-- Table: Valoracion
CREATE TABLE Valoracion (
                            valoracionID int  NOT NULL,
                            estudianteID int  NOT NULL,
                            mentorID int  NOT NULL,
                            comentario varchar(200)  NOT NULL,
                            estrellas int  NOT NULL,
                            CONSTRAINT Valoracion_pk PRIMARY KEY (valoracionID)
);

-- Table: turno
CREATE TABLE turno (
                       turnoID int  NOT NULL,
                       turno int  NOT NULL,
                       CONSTRAINT turno_pk PRIMARY KEY (turnoID)
);

-- foreign keys
-- Reference: Asignatura_Mentor_Asignatura (table: Asignatura_Mentor)
ALTER TABLE Asignatura_Mentor ADD CONSTRAINT Asignatura_Mentor_Asignatura
    FOREIGN KEY (asignaturaID)
        REFERENCES Asignatura (asignaturaID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Asignatura_Mentor_Mentor (table: Asignatura_Mentor)
ALTER TABLE Asignatura_Mentor ADD CONSTRAINT Asignatura_Mentor_Mentor
    FOREIGN KEY (mentorID)
        REFERENCES Mentor (mentorID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Estudiante_Usuario (table: Estudiante)
ALTER TABLE Estudiante ADD CONSTRAINT Estudiante_Usuario
    FOREIGN KEY (usuarioID)
        REFERENCES Usuario (usuarioID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Favorito_Estudiante (table: Favorito)
ALTER TABLE Favorito ADD CONSTRAINT Favorito_Estudiante
    FOREIGN KEY (estudianteID)
        REFERENCES Estudiante (estudianteID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Favorito_Mentor (table: Favorito)
ALTER TABLE Favorito ADD CONSTRAINT Favorito_Mentor
    FOREIGN KEY (mentorID)
        REFERENCES Mentor (mentorID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Mentor_Certificacion_Certificacion (table: Mentor_Certificacion)
ALTER TABLE Mentor_Certificacion ADD CONSTRAINT Mentor_Certificacion_Certificacion
    FOREIGN KEY (certificacionID)
        REFERENCES Certificacion (certificacionID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Mentor_Certificacion_Mentor (table: Mentor_Certificacion)
ALTER TABLE Mentor_Certificacion ADD CONSTRAINT Mentor_Certificacion_Mentor
    FOREIGN KEY (mentorID)
        REFERENCES Mentor (mentorID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Mentor_Usuario (table: Mentor)
ALTER TABLE Mentor ADD CONSTRAINT Mentor_Usuario
    FOREIGN KEY (usuarioID)
        REFERENCES Usuario (usuarioID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Participacion_Evento_Evento (table: Asistencia_Evento)
ALTER TABLE Asistencia_Evento ADD CONSTRAINT Participacion_Evento_Evento
    FOREIGN KEY (eventoID)
        REFERENCES Eventos (evento_ID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Participacion_Evento_Mentor (table: Asistencia_Evento)
ALTER TABLE Asistencia_Evento ADD CONSTRAINT Participacion_Evento_Mentor
    FOREIGN KEY (mentorID)
        REFERENCES Mentor (mentorID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: SesionMentoria_Asignatura (table: SesionMentoria)
ALTER TABLE SesionMentoria ADD CONSTRAINT SesionMentoria_Asignatura
    FOREIGN KEY (asignaturaID)
        REFERENCES Asignatura (asignaturaID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: SesionMentoria_Estudiante (table: SesionMentoria)
ALTER TABLE SesionMentoria ADD CONSTRAINT SesionMentoria_Estudiante
    FOREIGN KEY (estudianteID)
        REFERENCES Estudiante (estudianteID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: SesionMentoria_Mentor (table: SesionMentoria)
ALTER TABLE SesionMentoria ADD CONSTRAINT SesionMentoria_Mentor
    FOREIGN KEY (mentorID)
        REFERENCES Mentor (mentorID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: SesionMentoria_turno (table: SesionMentoria)
ALTER TABLE SesionMentoria ADD CONSTRAINT SesionMentoria_turno
    FOREIGN KEY (turnoID)
        REFERENCES turno (turnoID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: comentario_Estudiante (table: Valoracion)
ALTER TABLE Valoracion ADD CONSTRAINT comentario_Estudiante
    FOREIGN KEY (estudianteID)
        REFERENCES Estudiante (estudianteID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: valoracion_Mentor (table: Valoracion)
ALTER TABLE Valoracion ADD CONSTRAINT valoracion_Mentor
    FOREIGN KEY (mentorID)
        REFERENCES Mentor (mentorID)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- End of file.