package nombres;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración que define diferentes tipos de animales comúnmente usados como
 * mascotas. Cada tipo de animal tiene un nombre vulgar y un nombre científico
 * asociado.
 * 
 * @author Jaime Martinez Fernanez
 */
public enum MascotaAnimales {
	// Mamíferos
	PERRO("Perro", "Canis lupus familiaris"), GATO("Gato", "Felis catus"), RATON("Ratón", "Mus musculus"),
	CABALLO("Caballo", "Equus ferus caballus"), CONEJO("Conejo", "Oryctolagus cuniculus"),

	// Aves
	LORO("Loro", "Psittaciformes"), CANARIO("Canario", "Serinus canaria"), AGUILA("Águila", "Aquila chrysaetos"),
	PINGUINO("Pingüino", "Spheniscidae"), BUHO("Búho", "Strigiformes"),

	// Reptiles
	TORTUGA("Tortuga", "Testudines"), SERPIENTE("Serpiente", "Serpentes"), COCODRILO("Cocodrilo", "Crocodylidae"),
	IGUANA("Iguana", "Iguana iguana"), LAGARTO("Lagarto", "Lacertilia"),

	// Peces
	PEZ_DORADO("Pez Dorado", "Carassius auratus"), TIBURON("Tiburón", "Selachimorpha"), SALMON("Salmón", "Salmo salar"),
	BETTA("Pez Betta", "Betta splendens"), ANGUILA("Anguila", "Anguilliformes");

	private final String NOMBRE_VULGAR;
	private final String NOMBRE_CIENTIFICO;
	private static final Map<String, String> NOMBRES = new HashMap<>();

	/**
	 * Constructor para los tipos de animales.
	 * 
	 * @param nombreVulgar     Nombre común o vulgar del animal.
	 * @param nombreCientifico Nombre científico del animal.
	 */
	MascotaAnimales(String nombreVulgar, String nombreCientifico) {
		this.NOMBRE_VULGAR = nombreVulgar;
		this.NOMBRE_CIENTIFICO = nombreCientifico;
	}

	/**
	 * Obtiene el nombre vulgar del tipo de animal.
	 * 
	 * @return Nombre vulgar del animal.
	 */
	public String getNombreVulgar() {
		return NOMBRE_VULGAR;
	}

	/**
	 * Obtiene el nombre científico del tipo de animal.
	 * 
	 * @return Nombre científico del animal.
	 */
	public String getNombreCientifico() {
		return NOMBRE_CIENTIFICO;
	}

	// Inicializa mapa de nombres vulgares a científicos
	static {
		for (MascotaAnimales tipo : MascotaAnimales.values()) {
			NOMBRES.put(tipo.getNombreVulgar(), tipo.getNombreCientifico());
		}
	}

	/**
	 * Obtiene el nombre científico asociado a un nombre vulgar específico.
	 * 
	 * @param nombreVulgar Nombre vulgar del animal.
	 * @return Nombre científico del animal, o null si no se encuentra.
	 */
	public static String getNombreCientificoPorVulgar(String nombreVulgar) {
		return NOMBRES.get(nombreVulgar);
	}
}
