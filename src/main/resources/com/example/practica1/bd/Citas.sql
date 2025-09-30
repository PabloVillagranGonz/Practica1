DROP DATABASE IF EXISTS CitasMedicas;
CREATE DATABASE CitasMedicas;
USE CitasMedicas;

CREATE TABLE Pacientes (
    idPaciente INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Dni VARCHAR(9) UNIQUE NOT NULL,
    pass VARCHAR(64) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    telefono CHAR(9) NOT NULL
);

CREATE TABLE Especialidades (
    idEspecialidad INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Tipo ENUM('Cirugia','Escaner','Traumatologia') NOT NULL
);

CREATE TABLE Citas (
    idCita INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT UNSIGNED NOT NULL,
    idEspecialidad INT UNSIGNED NOT NULL,
    fecha DATE,
    FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente),
    FOREIGN KEY (idEspecialidad) REFERENCES Especialidades(idEspecialidad)
);


INSERT INTO Pacientes (idPaciente, Dni, pass, nombre, direccion, telefono) VALUES
 (1,"70278728T", SHA2("123", 256), "Fernando Gutierrez", "Avenida Salamanca", "687292213"),
 (2,"70278728L", SHA2("123", 256), "Clarita Gomez" , "Calle Tirso de Molina", "687990011");

INSERT INTO Especialidades (idEspecialidad, Tipo) VALUES
 (1, "Cirugia"),
 (2, "Escaner"),
 (3, "Traumatologia");

INSERT INTO Citas (idCita, idPaciente, idEspecialidad, fecha) VALUES
(1, 1, 1, '2025-05-12'),
(2, 1, 2, '2025-09-25'),
(3, 2, 2, '2025-10-01');

