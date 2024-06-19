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
 * Controlador para la escena para dar de baja a una mascota.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerBajaMascota extends Controller {

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
	 * Inicializa la interfaz y carga las mascotas según el tipo seleccionado en el
	 * ChoiceBox.
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
	 * Rellena la tabla de mascotas según el tipo seleccionado.
	 * 
	 * @param tipoAnimal El tipo de animal seleccionado en el ChoiceBox, o null si
	 *                   no se ha seleccionado ningún tipo.
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
	 * Elimina la mascota con el ID especificado. Muestra un mensaje si el campo de
	 * ID está vacío o si no se encuentra ninguna mascota con ese ID.
	 * 
	 * @param event El evento de acción que desencadena la eliminación de la
	 *              mascota.
	 */
	@FXML
	public void eliminarMascota(ActionEvent event) {
		String idTexto = textFieldId.getText().trim();
		if (idTexto.isEmpty()) {
			System.out.println("El campo de ID está vacío.");
			return;
		}

		try {
			Long id = Long.parseLong(idTexto);

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetCare");
			EntityManager em = emfactory.createEntityManager();

			em.getTransaction().begin();

			Mascota mascota = em.find(Mascota.class, id);

			if (mascota != null) {
				em.remove(mascota);
				em.getTransaction().commit();
				System.out.println("Mascota eliminada correctamente.");
				rellenarTabla(choiceBoxTipo.getValue());
			} else {
				System.out.println("No se encontró ninguna mascota con el ID proporcionado.");
			}

			em.close();
			emfactory.close();
		} catch (NumberFormatException e) {
			System.out.println("El ID debe ser un número válido.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
