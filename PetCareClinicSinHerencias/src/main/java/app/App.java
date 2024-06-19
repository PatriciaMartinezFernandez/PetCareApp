package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación PetCare. Extiende de la clase Application de
 * JavaFX para iniciar la aplicacón.
 * 
 * Esta clase carga el archivo FXML "menuPetCare.fxml"
 * 
 * @author Jaime Martinez Fernandez
 */
public class App extends Application {

	/**
	 * Método principal de inicio. Carga y configura la escena.
	 * 
	 * @param primaryStage La ventana principal de la aplicación.
	 * @throws Exception Si hay algún error al cargar el archivo.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Clínica Veterinaria PetCare");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Método principal que lanza la aplicación.
	 * 
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
