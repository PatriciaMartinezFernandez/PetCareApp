package clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que representa un Propietario en la base de datos.
 * 
 * Esta clase utiliza JPA para mapear la entidad Propietario a la base de datos.
 * 
 * @author Jaime Martinez Fernandez
 */
@Entity
public class Propietario {
	@Id
	private String dni;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String direccion;

	/**
	 * Constructor vacio requerido por JPA.
	 */
	public Propietario() {
	}

	/**
	 * Constructor para inicializar todos los campos de la clase Propietario.
	 * 
	 * @param dni       DNI del propietario.
	 * @param nombre    Nombre del propietario.
	 * @param direccion Direccion del propietario.
	 */
	public Propietario(String dni, String nombre, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	/**
	 * Obtiene el DNI del propietario.
	 * 
	 * @return El DNI del propietario.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI del propietario.
	 * 
	 * @param dni El DNI del propietario.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtiene el nombre del propietario.
	 * 
	 * @return El nombre del propietario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del propietario.
	 * 
	 * @param nombre El nombre del propietario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la dirección del propietario.
	 * 
	 * @return La dirección del propietario.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la dirección del propietario.
	 * 
	 * @param direccion La dirección del propietario.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Método toString para representar la información del Propietario como una
	 * cadena.
	 * 
	 * @return Cadena que representa la información del propietario.
	 */
	@Override
	public String toString() {
		return "Propietario [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

}