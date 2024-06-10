DROP DATABASE IF EXISTS PetCare;
CREATE DATABASE IF NOT EXISTS PetCare CHARACTER SET utf8mb4;
USE PetCare;

CREATE TABLE Propietario (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

CREATE TABLE Mascota (
	id INT AUTO_INCREMENT PRIMARY KEY,
    apodo VARCHAR(45) NOT NULL,
    nombreVulgar VARCHAR(45) NOT NULL,
    nombreCientifico VARCHAR(45) NOT NULL,
    sexo CHAR NOT NULL,
    anioNacimiento INT NOT NULL,
    propietario VARCHAR(45) NOT NULL,
    tipo VARCHAR(45) NOT NULL,
    FOREIGN KEY (propietario) REFERENCES Propietario (dni)  
);


CREATE TABLE Mamifero (
	id INT PRIMARY KEY,
    raza VARCHAR(45) NOT NULL,
    pedigree VARCHAR(45) NOT NULL,
    FOREIGN KEY (id) REFERENCES Mascota (id)
);

CREATE TABLE Ave (
	id INT PRIMARY KEY,
    tipoPlumaje VARCHAR(45) NOT NULL,
    habitos VARCHAR(200) NOT NULL,
    FOREIGN KEY (id) REFERENCES Mascota (id)
);

CREATE TABLE Reptil (
	id INT PRIMARY KEY,
    tipoEscamas VARCHAR(45) NOT NULL,
    FOREIGN KEY (id) REFERENCES Mascota (id)
);

CREATE TABLE Pez (
	id INT PRIMARY KEY,
    tipoAgua VARCHAR(45) NOT NULL,
    FOREIGN KEY (id) REFERENCES Mascota (id)
);

CREATE TABLE PropietariosTienenMascotas (
dniPropietario VARCHAR(9),
idMascota INT,
PRIMARY KEY (dniPropietario, idMascota)
);

select * from propietario;