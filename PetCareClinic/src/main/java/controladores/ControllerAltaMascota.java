package controladores;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import clases.JPAUtil;
import clases.Propietario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAltaMascota extends Controller {

	@FXML
	private TextField apodoField;
	@FXML
	private ChoiceBox<String> animalChoiceBox;
	@FXML
	private Label nombreCientificoLabel;
	@FXML
	private RadioButton machoRadioButton;
	@FXML
	private RadioButton hembraRadioButton;
	@FXML
	private TextField anioNacimientoField;
	@FXML
	private ChoiceBox<String> propietarioChoiceBox;
	@FXML
	private Button aniadirMascotaButton;

	public void initialize() {
		cargarPropietarios();
	}

	private void cargarPropietarios() {
	    EntityManager em = JPAUtil.getEntityManager();
	    
	    try {
	        List<Object[]> propietarios = em.createQuery("SELECT p.nombre, p.dni FROM Propietario p", Object[].class).getResultList();
	        for (Object[] propietario : propietarios) {
	            String nombre = (String) propietario[0];
	            String dni = (String) propietario[1];
	            String propietarioString = nombre + " (DNI: " + dni + ")";
	            propietarioChoiceBox.getItems().add(propietarioString);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}





	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void registrarPropietario(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/altaPropietario.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
