# PETCARE APP

## Objetivo del proyecto
Desarrollar una aplicación que gestione la información de mascotas y sus propietarios en una clínica veterinaria, usando JavaFX para la interfaz gráfica y MySQL para la base de datos, con persistencia gestionada por JPA.

## Especificación de requisitos y análisis de los mismos
### 1. Alta de Mascota:
- El usuario podrá registrar una nueva mascota, seleccionando entre mamíferos, aves, reptiles o peces, e ingresando los datos correspondientes de la mascota y su propietario. Si el propietario ya existe, se asignará la mascota a él; de lo contrario, se creará un nuevo propietario.
### 2. Baja de Mascota:
- El usuario podrá eliminar una mascota existente de la base de datos, ingresando el número identificativo de la misma.
### 3. Edición de datos de la Mascota:
- El usuario podrá modificar los datos de una mascota existente y, si es necesario, también los datos del propietario. Para ello, deberá ingresar el número identificativo de la mascota.
### 4. Listados:
- El usuario podrá generar listados de mascotas, eligiendo entre un listado completo de todas las mascotas de la clínica o un listado filtrado por tipo de mascota (mamífero, ave, reptil o pez).

