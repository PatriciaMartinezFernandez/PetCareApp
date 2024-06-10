package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Cargar el archivo FXML
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		
		// Crear la escena
		Scene scene = new Scene(root);

		// Titulo venta
		primaryStage.setTitle("Clínica Veterinaria PetCare");
		
		// Establecer la escena en la etapa (stage)
		primaryStage.setScene(scene);

		// Mostrar la etapa (stage)
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
