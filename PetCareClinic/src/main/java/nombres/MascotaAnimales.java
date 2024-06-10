package nombres;

import java.util.HashMap;
import java.util.Map;

public enum MascotaAnimales {
	// Mamíferos
    PERRO("Perro", "Canis lupus familiaris"),
    GATO("Gato", "Felis catus"),
    RATON("Ratón", "Mus musculus"),
    CABALLO("Caballo", "Equus ferus caballus"),
    CONEJO("Conejo", "Oryctolagus cuniculus"),

    // Aves
    LORO("Loro", "Psittaciformes"),
    CANARIO("Canario", "Serinus canaria"),
    AGUILA("Águila", "Aquila chrysaetos"),
    PINGUINO("Pingüino", "Spheniscidae"),
    BUHO("Búho", "Strigiformes"),

    // Reptiles
    TORTUGA("Tortuga", "Testudines"),
    SERPIENTE("Serpiente", "Serpentes"),
    COCODRILO("Cocodrilo", "Crocodylidae"),
    IGUANA("Iguana", "Iguana iguana"),
    LAGARTO("Lagarto", "Lacertilia"),

    // Peces
    PEZ_DORADO("Pez Dorado", "Carassius auratus"),
    TIBURON("Tiburón", "Selachimorpha"),
    SALMON("Salmón", "Salmo salar"),
    BETTA("Pez Betta", "Betta splendens"),
    ANGUILA("Anguila", "Anguilliformes");
    

    private final String NOMBRE_VULGAR;
    private final String NOMBRE_CIENTIFICO;
	private static final Map<String, String> NOMBRES = new HashMap<>();

    MascotaAnimales(String nombreVulgar, String nombreCientifico) {
        this.NOMBRE_VULGAR = nombreVulgar;
        this.NOMBRE_CIENTIFICO = nombreCientifico;
    }

    public String getNombreVulgar() {
        return NOMBRE_VULGAR;
    }

    public String getNombreCientifico() {
        return NOMBRE_CIENTIFICO;
    }

    static {
        for (MascotaAnimales tipo : MascotaAnimales.values()) {
            NOMBRES.put(tipo.getNombreVulgar(), tipo.getNombreCientifico());
        }
    }

    public static String getNombreCientificoPorVulgar(String nombreVulgar) {
        return NOMBRES.get(nombreVulgar);
    }
}
