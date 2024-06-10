package clases;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Mamifero")
public class Mamifero extends Mascota {
	private String raza;
	private boolean pedigri;

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public boolean getPedigri() {
		return pedigri;
	}

	public void setPedigri(boolean pedigri) {
		this.pedigri = pedigri;
	}

}
