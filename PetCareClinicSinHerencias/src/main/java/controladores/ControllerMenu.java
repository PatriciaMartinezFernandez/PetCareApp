package controladores;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import clases.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador para la escena del menú principal de la aplicación PetCare. Este
 * controlador gestiona la carga de datos estadísticos y la navegación entre
 * diferentes escenas.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerMenu extends Controller {

	@FXML
	private Label contadorMascotas;
	@FXML
	private Label contadorPropietarios;
	@FXML
	private PieChart pieChartTiposMascotas;

	/**
	 * Método inicializador de JavaFX. Este método se ejecuta automáticamente
	 * después de cargar la vista desde el archivo FXML. Inicializa los contadores
	 * de mascotas y propietarios, así como el gráfico de tipos de mascotas.
	 */
	@FXML
	public void initialize() {
		contarMascotas();
		contarPropietarios();
		actualizarPieChartTiposMascotas();
	}

	/**
	 * Cuenta el número total de propietarios en la base de datos y actualiza el
	 * valor en el label `contadorPropietarios`.
	 */
	private void contarPropietarios() {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();

			long totalPropietarios = (long) em.createQuery("SELECT COUNT(p) FROM Propietario p").getSingleResult();
			contadorPropietarios.setText(String.valueOf(totalPropietarios));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Cuenta el número total de mascotas en la base de datos y actualiza el valor
	 * en el label `contadorMascotas`.
	 */
	private void contarMascotas() {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();

			long totalMascotas = (long) em.createQuery("SELECT COUNT(m) FROM Mascota m").getSingleResult();
			contadorMascotas.setText(String.valueOf(totalMascotas));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Actualiza el PieChart `pieChartTiposMascotas` con los datos de tipos de
	 * mascotas y su cantidad.
	 */
	private void actualizarPieChartTiposMascotas() {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();

			String query = "SELECT m.tipoAnimal, COUNT(m) FROM Mascota m GROUP BY m.tipoAnimal";
			List<Object[]> results = em.createQuery(query, Object[].class).getResultList();

			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			for (Object[] result : results) {
				String tipoAnimal = (String) result[0];
				Long cantidad = (Long) result[1];
				pieChartData.add(new PieChart.Data(tipoAnimal, cantidad));
			}

			pieChartTiposMascotas.setData(pieChartData);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Maneja el evento de alta de mascota, cargando la vista correspondiente.
	 * 
	 * @param event Evento de acción que desencadena el método (por ejemplo, clic en
	 *              un botón).
	 * @throws IOException Si ocurre un error al cargar la vista de alta de mascota
	 *                     desde el archivo FXML.
	 */
	public void altaMascota(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/altaMascota.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Maneja el evento de baja de mascota, cargando la vista correspondiente.
	 * 
	 * @param event Evento de acción que desencadena el método (por ejemplo, clic en
	 *              un botón).
	 * @throws IOException Si ocurre un error al cargar la vista de baja de mascota
	 *                     desde el archivo FXML.
	 */
	public void bajaMascota(ActionEvent event) throws IOException {
		cargarVista("/fxml/bajaMascota.fxml");
	}

	/**
	 * Maneja el evento de edición de mascota, cargando la vista correspondiente.
	 * 
	 * @param event Evento de acción que desencadena el método (por ejemplo, clic en
	 *              un botón).
	 * @throws IOException Si ocurre un error al cargar la vista de edición de
	 *                     mascota desde el archivo FXML.
	 */
	public void editarMascota(ActionEvent event) throws IOException {
		cargarVista("/fxml/editarMascota.fxml");
	}

	/**
	 * Maneja el evento de lista de mascotas, cargando la vista correspondiente.
	 * 
	 * @param event Evento de acción que desencadena el método (por ejemplo, clic en
	 *              un botón).
	 * @throws IOException Si ocurre un error al cargar la vista de lista de
	 *                     mascotas desde el archivo FXML.
	 */
	public void listaMascota(ActionEvent event) throws IOException {
		cargarVista("/fxml/listaMascotas.fxml");
	}

	/**
	 * Maneja el evento de estadísticas de mascotas, cargando la vista
	 * correspondiente.
	 * 
	 * @param event Evento de acción que desencadena el método (por ejemplo, clic en
	 *              un botón).
	 * @throws IOException Si ocurre un error al cargar la vista de estadísticas de
	 *                     mascotas desde el archivo FXML.
	 */
	public void estadisticas(ActionEvent event) throws IOException {
		cargarVista("/fxml/estadisticasMascotas.fxml");
	}

	/**
	 * Carga una nueva vista FXML en la ventana principal.
	 * 
	 * @param fxmlPath Ruta del archivo FXML que se va a cargar.
	 * @throws IOException Si ocurre un error al cargar la vista desde el archivo
	 *                     FXML.
	 */
	private void cargarVista(String fxmlPath) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fxmlPath));
		stage = (Stage) ((Node) pieChartTiposMascotas).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
