DROP DATABASE IF EXISTS CitasMedicas;
CREATE DATABASE CitasMedicas;
USE CitasMedicas;

CREATE TABLE Pacientes (
    idPaciente INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Dni VARCHAR(9) UNIQUE NOT NULL,
    pass VARCHAR(256) NOT NULL,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Especialidades (
    idEspecialidad INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Tipo ENUM('Cirugia','Escaner','Traumatologia') NOT NULL
);

CREATE TABLE Citas (
    idCita INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT UNSIGNED NOT NULL,
    idEspecialidad INT UNSIGNED NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    telefono CHAR(9) NOT NULL,
    FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente),
    FOREIGN KEY (idEspecialidad) REFERENCES Especialidades(idEspecialidad)
);


INSERT INTO Pacientes (idPaciente, Dni, pass, nombre) VALUES
 (1,"70278728T","", "Fernando Gutierrez"),
 (2,"70278728L","", "Clarita Gomez");

INSERT INTO Especialidades (idEspecialidad, Tipo) VALUES
 (1, "Cirugia"),
 (2, "Escaner"),
 (3, "Traumatologia");

INSERT INTO Citas (idCita, idPaciente, idEspecialidad, direccion, telefono) VALUES
 (1, 1, 1, "Avenida Salamanca", "687292213"),
 (2, 1, 2, "Calle Tirso de Molina", "687990011"),
 (3, 2, 2, "Calle Tirso de Molina", "612542331");
