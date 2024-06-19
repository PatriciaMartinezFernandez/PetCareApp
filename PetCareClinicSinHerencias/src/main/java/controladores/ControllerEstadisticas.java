package controladores;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import clases.JPAUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * Controlador para la escena de estadísticas de mascotas. Este controlador
 * carga datos desde la base de datos utilizando JPA y muestra estos datos en
 * diferentes tipos de gráficos.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerEstadisticas extends Controller {

	@FXML
	private PieChart pieChartGenero;
	@FXML
	private BarChart<String, Number> barChartPropietario;
	@FXML
	private AreaChart<String, Number> areaChartEdad;
	private EntityManager em;

	/**
	 * Constructor por defecto del controlador. Inicializa el EntityManager
	 * utilizando JPAUtil.
	 */
	public ControllerEstadisticas() {
		em = JPAUtil.getEntityManager();
	}

	/**
	 * Método inicializador de JavaFX. Este método se ejecuta automáticamente
	 * después de cargar la vista desde el archivo FXML. Carga los datos en los
	 * gráficos al inicializar la vista.
	 */
	@FXML
	public void initialize() {
		cargarDatosGenero();
		cargarDatosPropietario();
		cargarDatosEdad();
	}

	/**
	 * Carga los datos de género (cantidad de mascotas por género) y los muestra en
	 * un PieChart.
	 */
	private void cargarDatosGenero() {
		String consulta = "SELECT m.sexo, COUNT(m) FROM Mascota m GROUP BY m.sexo";
		TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
		List<Object[]> resultados = query.getResultList();

		pieChartGenero.getData().clear();

		for (Object[] resultado : resultados) {
			Character sexoChar = (Character) resultado[0];
			String sexo = sexoChar.toString();
			Long cantidad = (Long) resultado[1];
			PieChart.Data data = new PieChart.Data(sexo, cantidad);
			pieChartGenero.getData().add(data);
		}
	}

	/**
	 * Carga los datos de propietario (cantidad de mascotas por propietario) y los
	 * muestra en un BarChart.
	 */
	private void cargarDatosPropietario() {
		String consulta = "SELECT m.propietario.nombre, COUNT(m) FROM Mascota m GROUP BY m.propietario.nombre";
		TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
		List<Object[]> resultados = query.getResultList();

		barChartPropietario.getData().clear();

		XYChart.Series<String, Number> datos = new XYChart.Series<>();
		for (Object[] resultado : resultados) {
			String nombrePropietario = (String) resultado[0];
			Long cantidadMascotas = (Long) resultado[1];
			Integer cantidadMascotasInt = cantidadMascotas.intValue();
			datos.getData().add(new XYChart.Data<>(nombrePropietario, cantidadMascotasInt));
		}

		barChartPropietario.getData().add(datos);

	}

	/**
	 * Carga los datos de edad (cantidad de mascotas por año de nacimiento) y los
	 * muestra en un AreaChart.
	 */
	private void cargarDatosEdad() {
		String consulta = "SELECT m.anioNacimiento, COUNT(m) FROM Mascota m GROUP BY m.anioNacimiento";
		TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
		List<Object[]> resultados = query.getResultList();

		areaChartEdad.getData().clear();

		resultados.sort((o1, o2) -> {
			Integer valor1 = (Integer) o1[0];
			Integer valor2 = (Integer) o2[0];
			return valor1.compareTo(valor2);
		});

		XYChart.Series<String, Number> datos = new XYChart.Series<>();
		for (Object[] resultado : resultados) {
			Integer anio = (Integer) resultado[0];
			Long cantidad = (Long) resultado[1];
			datos.getData().add(new XYChart.Data<>(anio.toString(), cantidad));
		}

		areaChartEdad.getData().add(datos);
	}

	/**
	 * Maneja el evento de volver al menú principal. Cierra la ventana actual y
	 * muestra la ventana del menú principal.
	 * 
	 * @param event Evento de acción que desencadena el método (por ejemplo, clic en
	 *              un botón).
	 * @throws IOException Si ocurre un error al cargar la vista del menú principal
	 *                     desde el archivo FXML.
	 */
	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}