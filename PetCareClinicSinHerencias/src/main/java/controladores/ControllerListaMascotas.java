package controladores;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import clases.Mascota;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nombres.TipoAnimal;

/**
 * Controlador para la escena de lista de mascotas. Este controlador gestiona la
 * carga de datos de mascotas desde la base de datos y muestra estos datos en
 * una tabla JavaFX.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerListaMascotas extends Controller {

	@FXML
	private ChoiceBox<TipoAnimal> choiceBoxTipo;
	@FXML
	private TableView<Mascota> tableViewMascota;
	@FXML
	private TableColumn<Mascota, Integer> colID;
	@FXML
	private TableColumn<Mascota, String> colApodo;
	@FXML
	private TableColumn<Mascota, String> colNombreVulgar;
	@FXML
	private TableColumn<Mascota, String> colNombreCientifico;
	@FXML
	private TableColumn<Mascota, String> colSexo;
	@FXML
	private TableColumn<Mascota, Integer> colAnioNacimiento;
	@FXML
	private TableColumn<Mascota, String> colPropietario;
	@FXML
	private TableColumn<Mascota, String> colTipoAnimal;
	private ObservableList<Mascota> listaMascotas;

	/**
	 * Método inicializador de JavaFX. Este método se ejecuta automáticamente
	 * después de cargar la vista desde el archivo FXML. Configura el ChoiceBox y la
	 * TableView, y llama a rellenarTabla() para cargar los datos iniciales.
	 */
	@FXML
	public void initialize() {
		choiceBoxTipo.setItems(FXCollections.observableArrayList(TipoAnimal.values()));
		choiceBoxTipo.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> rellenarTabla(newValue));

		listaMascotas = FXCollections.observableArrayList();
		tableViewMascota.setItems(listaMascotas);

		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colApodo.setCellValueFactory(new PropertyValueFactory<>("apodo"));
		colNombreVulgar.setCellValueFactory(new PropertyValueFactory<>("nombreVulgar"));
		colNombreCientifico.setCellValueFactory(new PropertyValueFactory<>("nombreCientifico"));
		colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
		colAnioNacimiento.setCellValueFactory(new PropertyValueFactory<>("anioNacimiento"));
		colPropietario.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPropietario().getNombre()));
		colTipoAnimal.setCellValueFactory(new PropertyValueFactory<>("tipoAnimal"));

		rellenarTabla(null);
	}

	/**
	 * Rellena la tabla de mascotas con datos obtenidos de la base de datos.
	 * 
	 * @param tipoAnimal Tipo de animal para filtrar las mascotas (puede ser null
	 *                   para mostrar todas las mascotas).
	 */
	private void rellenarTabla(TipoAnimal tipoAnimal) {
		EntityManagerFactory emfactory = null;
		EntityManager em = null;

		try {
			emfactory = Persistence.createEntityManagerFactory("PetCare");
			em = emfactory.createEntityManager();
			em.getTransaction().begin();

			String queryString = "SELECT DISTINCT m FROM Mascota m JOIN FETCH m.propietario";

			if (tipoAnimal != null) {
				queryString += " WHERE m.tipoAnimal = :tipo";
			}

			TypedQuery<Mascota> query = em.createQuery(queryString, Mascota.class);

			if (tipoAnimal != null) {
				query.setParameter("tipo", tipoAnimal.toString());
			}

			List<Mascota> mascotas = query.getResultList();

			listaMascotas.clear();
			listaMascotas.addAll(mascotas);

			em.getTransaction().commit();
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
	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
