DROP DATABASE IF EXISTS PetCare;
CREATE DATABASE IF NOT EXISTS PetCare CHARACTER SET utf8mb4;
USE PetCare;

CREATE TABLE Propietario (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

CREATE TABLE Mascota (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    apodo VARCHAR(45) NOT NULL,
    nombreVulgar VARCHAR(45) NOT NULL,
    nombreCientifico VARCHAR(45) NOT NULL,
    sexo CHAR NOT NULL,
    anioNacimiento INT NOT NULL,
    dni VARCHAR(45),
    tipoAnimal VARCHAR(45),
    CONSTRAINT FK_MASCOTA_PROPIETARIO FOREIGN KEY (dni) REFERENCES Propietario(dni)
);

CREATE TABLE PropietariosTienenMascotas (
dniPropietario VARCHAR(9),
idMascota BIGINT,
PRIMARY KEY (dniPropietario, idMascota),
CONSTRAINT FK_PROPIETARIO_TIENE_MASCOTA_DNI FOREIGN KEY (dniPropietario) REFERENCES Propietario (dni),
CONSTRAINT FK_PROPIETARIO_TIENE_MASCOTA_ID FOREIGN KEY (idMascota) REFERENCES Mascota(id)
);

select * from propietario;
select * from mascota;
