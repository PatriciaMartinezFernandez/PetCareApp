package controladores;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import clases.Propietario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la escena para dar de alta propietario.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerAltaPropietario extends Controller {

	@FXML
	private TextField dniTextField;
	@FXML
	private TextField nombreTextField;
	@FXML
	private TextField direccionTextField;

	/**
	 * Verifica los datos ingresados y añade un nuevo propietario si todos los
	 * campos están completos.
	 * 
	 * @throws Exception Si ocurre un error durante la operación de persistencia.
	 */
	public void verificarDatos() throws Exception {
		String dni = dniTextField.getText();
		String nombre = nombreTextField.getText();
		String direccion = direccionTextField.getText();

		if (dni != null && !dni.isBlank() && nombre != null && !nombre.isBlank() && direccion != null
				&& !direccion.isBlank()) {
			aniadirPropietario(dni, nombre, direccion);
		} else {
			System.err.println("No se ha podido añadir al propietario");
		}
	}

	/**
	 * Persiste un nuevo propietario en la base de datos.
	 * 
	 * @param dni       El DNI del propietario.
	 * @param nombre    El nombre del propietario.
	 * @param direccion La dirección del propietario.
	 * @throws Exception Si ocurre un error durante la operación de persistencia.
	 */
	public void aniadirPropietario(String dni, String nombre, String direccion) throws Exception {

		EntityManagerFactory emfactory = null;
		EntityManager em = null;

		try {
			emfactory = Persistence.createEntityManagerFactory("PetCare");
			em = emfactory.createEntityManager();
			em.getTransaction().begin();

			Propietario propietario = new Propietario(dni, nombre, direccion);
			em.persist(propietario);

			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emfactory != null) {
				emfactory.close();
			}
		}
	}

	/**
	 * Carga la escena de alta de mascota cuando se presiona el botón de volver.
	 * 
	 * @param event El evento de acción que desencadena esta función.
	 * @throws IOException Si ocurre un error durante la carga de la escena desde el
	 *                     archivo FXML.
	 */
	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/altaMascota.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
