package controladores;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controlador base que proporciona la configuracion de Hibernate y manejo de
 * escenas de JavaFX.
 * 
 * @author Jaime Martinez Fernandez
 */
public class Controller {

	protected static SessionFactory sessionFactory;

	// Inicializa la configuracion de Hibernate.
	static {
		try {
			// Carga la configuracion de Hibernate desde el archivo "hibernate.cfg.xml".
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			// Crea el registro de servicios de Hibernate.
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			// Construye el SessionFactory.
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Escenas
	protected Stage stage; // Ventana principal de la aplicacion JavaFX.
	protected Scene scene; // Escena actual de la aplicacion JavaFX.
	protected Parent root; // Raiz de la escena actual de la aplicacion JavaFX.

}
