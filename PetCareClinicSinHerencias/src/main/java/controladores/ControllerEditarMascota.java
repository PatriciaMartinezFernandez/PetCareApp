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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nombres.TipoAnimal;

/**
 * Controlador para la escena para editar las mascotas.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerEditarMascota extends Controller {

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
	@FXML
	private TextField textFieldId;

	private ObservableList<Mascota> listaMascotas;

	/**
	 * Inicializa la vista de edición de mascotas. Configura los elementos visuales
	 * y carga las mascotas inicialmente.
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
	 * Rellena la tabla de mascotas según el tipo de animal seleccionado.
	 * 
	 * @param tipoAnimal Tipo de animal seleccionado para filtrar mascotas, o null
	 *                   para mostrar todas.
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
	 * Abre la ventana para editar los datos de una mascota específica.
	 * 
	 * @param event Evento de acción que desencadena la edición de la mascota.
	 * @throws IOException Si ocurre un error al cargar la vista de edición de datos
	 *                     de mascota.
	 */
	@FXML
	public void editarMascota(ActionEvent event) throws IOException {
		String idMascotaText = textFieldId.getText().trim();

		if (idMascotaText.isEmpty()) {
			System.out.println("Por favor, ingrese un ID de mascota válido.");
			return;
		}

		Long idMascota;
		try {
			idMascota = Long.parseLong(idMascotaText);
		} catch (NumberFormatException e) {
			System.out.println("Por favor, ingrese un ID de mascota válido (número entero).");
			return;
		}

		EntityManagerFactory emfactory = null;
		EntityManager em = null;

		try {
			emfactory = Persistence.createEntityManagerFactory("PetCare");
			em = emfactory.createEntityManager();

			Mascota mascota = em.find(Mascota.class, idMascota);

			if (mascota != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/datosMascota.fxml"));
				Scene scene = new Scene(loader.load());
				ControllerDatosMascota controller = loader.getController();
				controller.initData(idMascota);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();

				((Node) event.getSource()).getScene().getWindow().hide();
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
	 * Carga la escena del menú principal cuando se presiona el botón de volver.
	 * 
	 * @param event Evento de acción que desencadena la carga de la escena del menú
	 *              principal.
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
