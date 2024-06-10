package clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "propietario")
public class Propietario {
	@Id
	private String dni;
	private String nombre;
	private String direccion;
	private int numMascotas;
	// @OneToMany(mappedBy = "propietario")
	private List<Mascota> mascotas = new ArrayList<Mascota>();

	public Propietario() {
	}

	public Propietario(String dni, String nombre, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numMascotas = mascotas.size();
	}

	/*
	 * public Propietario(String dni, String nombre, String direccion, int
	 * numMascotas, List<Mascota> mascotas) { this.dni = dni; this.nombre = nombre;
	 * this.direccion = direccion; this.numMascotas = numMascotas; this.mascotas =
	 * mascotas; }
	 */

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumMascotas() {
		return numMascotas;
	}

	public void setNumMascotas(int numMascotas) {
		this.numMascotas = numMascotas;
	}

	/*
	 * public List<Mascota> getMascotas() { return mascotas; }
	 * 
	 * public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
	 */
	@Override
	public String toString() {
		return "Propietario [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", numMascotas="
				+ numMascotas + "]";
	}

}