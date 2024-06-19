package clases;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase utilizada para gestionar el EntityManagerFactory y proporcionar
 * EntityManagers.
 * 
 * Esta clase se encarga de crear una instancia única de EntityManagerFactory y
 * proporciona métodos estáticos para obtener EntityManagers y cerrar el
 * EntityManagerFactory.
 * 
 * @author Jaime Martinez Fernandez
 */
public class JPAUtil {
	// Instancia única de EntityManagerFactory para la aplicación.
	private static final EntityManagerFactory emFactory;

	// Inicializa el EntityManagerFactory y le digo que utilice como unidad de
	// persistencia "PetCare"
	static {
		emFactory = Persistence.createEntityManagerFactory("PetCare");
	}

	/**
	 * Proporciona una instancia de EntityManager.
	 * 
	 * Este método crea y devuelve una nueva instancia de EntityManager a partir de
	 * la EntityManagerFactory.
	 * 
	 * @return Una instancia de EntityMangager
	 */
	public static EntityManager getEntityManager() {
		System.out.println("getentitymanager");
		return emFactory.createEntityManager();
	}

	/**
	 * Cierra el EntityManager.
	 */
	public static void close() {
		emFactory.close();
	}
}