package controladores;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import clases.Mascota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la escena para editar los datos de la mascota seleccionada.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerDatosMascota extends Controller {

	@FXML
	private Label labelNombreCientifico;
	@FXML
	private Label labelTipoAnimal;
	@FXML
	private Label labelNombreMascotaEditada;
	@FXML
	private Label labelAnimal;
	@FXML
	private TextField textFieldApodo;
	@FXML
	private RadioButton radioButtonMacho;
	@FXML
	private RadioButton radioButtonHembra;
	@FXML
	private TextField textFieldAnioNacimiento;
	@FXML
	private Label labelPropietario;

	private Long idMascota;

	/**
	 * Inicializa los datos de la mascota para la vista de edición.
	 * 
	 * @param idMascota El ID de la mascota cuyos datos se cargarán.
	 */
	public void initData(Long idMascota) {
		this.idMascota = idMascota;
		cargarDatosMascota();
	}

	/**
	 * Carga los datos de la mascota desde la base de datos y los muestra en la
	 * interfaz gráfica.
	 */
	private void cargarDatosMascota() {
		EntityManagerFactory emfactory = null;
		EntityManager em = null;

		try {
			emfactory = Persistence.createEntityManagerFactory("PetCare");
			em = emfactory.createEntityManager();

			Mascota mascota = em.find(Mascota.class, idMascota);

			if (mascota != null) {
				labelNombreMascotaEditada.setText(mascota.getApodo());
				labelTipoAnimal.setText(mascota.getTipoAnimal());
				labelNombreCientifico.setText(mascota.getNombreCientifico());
				labelAnimal.setText(mascota.getNombreVulgar());
				textFieldApodo.setText(mascota.getApodo());

				if ('M' == mascota.getSexo()) {
					radioButtonMacho.setSelected(true);
				} else if ('H' == mascota.getSexo()) {
					radioButtonHembra.setSelected(true);
				}

				textFieldAnioNacimiento.setText(String.valueOf(mascota.getAnioNacimiento()));
				labelPropietario.setText(mascota.getPropietario().getNombre());
			} else {
				System.out.println("No se encontró la mascota con ID: " + idMascota);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	 * Confirma los cambios realizados en los datos de la mascota y los actualiza en
	 * la base de datos.
	 */
	public void confirmarCambios() {
		EntityManagerFactory emfactory = null;
		EntityManager em = null;

		try {
			emfactory = Persistence.createEntityManagerFactory("PetCare");
			em = emfactory.createEntityManager();
			em.getTransaction().begin();

			Mascota mascota = em.find(Mascota.class, idMascota);

			if (mascota != null) {
				// Actualizar los datos de la mascota con los valores de los campos
				mascota.setApodo(textFieldApodo.getText());
				mascota.setAnioNacimiento(Integer.parseInt(textFieldAnioNacimiento.getText()));

				if (radioButtonMacho.isSelected()) {
					mascota.setSexo('M');
				} else if (radioButtonHembra.isSelected()) {
					mascota.setSexo('H');
				}

				// Persistir los cambios en la base de datos
				em.getTransaction().commit();
				System.out.println("Cambios confirmados para la mascota con ID: " + idMascota);
			} else {
				System.out.println("No se encontró la mascota con ID: " + idMascota);
			}

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
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
	 * Carga la escena del menú principal cuando se presiona el botón de volver.
	 * 
	 * @param event El evento de acción que desencadena la carga de la escena del
	 *              menú principal.
	 * @throws IOException Si ocurre un error durante la carga de la escena desde el
	 *                     archivo FXML.
	 */
	@FXML
	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
