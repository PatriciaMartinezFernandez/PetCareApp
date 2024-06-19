package clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa una Mascota en la base de datos.
 * 
 * Esta clase utiliza JPA para mapear la entidad Mascota a la tabla "Mascota" en
 * la base de datos.
 * 
 * @author Jaime Martinez Fernandez
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Mascota")
public class Mascota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String apodo;
	private String nombreVulgar;
	private String nombreCientifico;
	private char sexo;
	private int anioNacimiento;
	@ManyToOne
	@JoinColumn(name = "dni")
	private Propietario propietario;
	private String tipoAnimal;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Mascota() {
	}

	/**
	 * Constructor para inicializar todos los campos de la clase Mascota.
	 * 
	 * @param apodo            Apodo de la mascota.
	 * @param nombreVulgar     Nombre vulgar de la mascota.
	 * @param nombreCientifico Nombre cientifico de la mascota.
	 * @param sexo             Sexo de la mascota ('M' para macho, 'F' para hembra).
	 * @param anioNacimiento   Año de nacimiento de la mascota.
	 * @param propietario      Propietario de la mascota.
	 * @param tipoAnimal       Tipo de animal de la mascota.
	 */
	public Mascota(String apodo, String nombreVulgar, String nombreCientifico, char sexo, int anioNacimiento,
			Propietario propietario, String tipoAnimal) {
		this.apodo = apodo;
		this.nombreVulgar = nombreVulgar;
		this.nombreCientifico = nombreCientifico;
		this.sexo = sexo;
		this.anioNacimiento = anioNacimiento;
		this.propietario = propietario;
		this.tipoAnimal = tipoAnimal;
	}

	/**
	 * Obtiene el ID de la mascota.
	 * 
	 * @return El ID de la mascota.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID de la mascota.
	 * 
	 * @param id El ID de la mascota.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el apodo de la mascota.
	 * 
	 * @return El apodo de la mascota.
	 */
	public String getApodo() {
		return apodo;
	}

	/**
	 * Establece el apodo de la mascota.
	 * 
	 * @param apodo El apodo de la mascota.
	 */
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	/**
	 * Obtiene el nombre vulgar de la mascota.
	 * 
	 * @return El nombre vulgar de la mascota.
	 */
	public String getNombreVulgar() {
		return nombreVulgar;
	}

	/**
	 * Establece el nombre vulgar de la mascota.
	 * 
	 * @param nombreVulgar El nombre vulgar de la mascota.
	 */
	public void setNombreVulgar(String nombreVulgar) {
		this.nombreVulgar = nombreVulgar;
	}

	/**
	 * Obtiene el nombre científico de la mascota.
	 * 
	 * @return El nombre científico de la mascota.
	 */
	public String getNombreCientifico() {
		return nombreCientifico;
	}

	/**
	 * Establece el nombre científico de la mascota.
	 * 
	 * @param nombreCientifico El nombre científico de la mascota.
	 */
	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	/**
	 * Obtiene el sexo de la mascota.
	 * 
	 * @return El sexo de la mascota ('M' para macho, 'F' para hembra).
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * Establece el sexo de la mascota.
	 * 
	 * @param sexo El sexo de la mascota ('M' para macho, 'F' para hembra).
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtiene el año de nacimiento de la mascota.
	 * 
	 * @return El año de nacimiento de la mascota.
	 */
	public int getAnioNacimiento() {
		return anioNacimiento;
	}

	/**
	 * Establece el año de nacimiento de la mascota.
	 * 
	 * @param anioNacimiento El año de nacimiento de la mascota.
	 */
	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	/**
	 * Obtiene el propietario de la mascota.
	 * 
	 * @return El propietario de la mascota.
	 */
	public Propietario getPropietario() {
		return propietario;
	}

	/**
	 * Establece el propietario de la mascota.
	 * 
	 * @param propietario El propietario de la mascota.
	 */
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	/**
	 * Obtiene el tipo de animal de la mascota.
	 * 
	 * @return El tipo de animal de la mascota.
	 */
	public String getTipoAnimal() {
		return tipoAnimal;
	}

	/**
	 * Establece el tipo de animal de la mascota.
	 * 
	 * @param tipoAnimal El tipo de animal de la mascota.
	 */
	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	/**
	 * Método toString para representar la información de la Mascota como una
	 * cadena.
	 * 
	 * @return Cadena que representa la información de la mascota.
	 */
	@Override
	public String toString() {
		return "Apodo: " + apodo + ", Nombre Vulgar: " + nombreVulgar + ", Nombre Científico: " + nombreCientifico
				+ ", Sexo: " + (sexo == 'M' ? "Macho" : "Hembra") + ", Año de Nacimiento: " + anioNacimiento
				+ ", Propietario: " + (propietario != null ? propietario.getNombre() : "N/A") + ", Tipo de Animal: "
				+ tipoAnimal;
	}
}
