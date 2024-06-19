package controladores;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import clases.JPAUtil;
import clases.Mascota;
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
import nombres.MascotaAnimales;
import nombres.TipoAnimal;

/**
 * Controlador para el formulario para dar de alta de mascota.
 * 
 * @author Jaime Martinez Fernandez
 */
public class ControllerAltaMascota extends Controller {

	@FXML
	private TextField apodoField;
	@FXML
	private ChoiceBox<String> animalChoiceBox;
	@FXML
	private Label nombreCientificoLabel;
	@FXML
	private Label tipoAnimalLabel;
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

	/**
	 * Método que se llama automáticamente al cargar la vista. Inicializa los
	 * componentes del formulario.
	 */
	@FXML
	public void initialize() {
		cargarNombresMascotas();
		imprimirNombreCientifico();
		imprimirTipoDeAnimal();
		cargarPropietarios();
	}

	/**
	 * Cargta las posibles mascotas en el ChoiceBox.
	 */
	private void cargarNombresMascotas() {
		animalChoiceBox.getItems().addAll(Arrays.stream(MascotaAnimales.values()).map(MascotaAnimales::getNombreVulgar)
				.collect(Collectors.toList()));
	}

	/**
	 * Asigna el nombre científico correspondiente al nombre vulgar seleccionado en
	 * el ChoiceBox.
	 */
	private void imprimirNombreCientifico() {
		animalChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				String nombreCientifico = MascotaAnimales.getNombreCientificoPorVulgar(newValue);
				nombreCientificoLabel.setText(nombreCientifico != null ? nombreCientifico : "");
			}
		});
	}

	/**
	 * Asigna el tipo de animal correspondiente al nombre vulgar seleccionado en el
	 * ChoiceBox.
	 */
	private void imprimirTipoDeAnimal() {
		animalChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				String nombreCientifico = MascotaAnimales.getNombreCientificoPorVulgar(newValue);
				String tipoAnimal = String.valueOf(obtenerTipoAnimal(newValue));
				nombreCientificoLabel.setText(nombreCientifico != null ? nombreCientifico : "");
				tipoAnimalLabel.setText(tipoAnimal);
			}
		});
	}

	/**
	 * Obtiene el tipo de animal basado en el nombre vulgar.
	 * 
	 * @param nombreVulgar El nombre vulgar de la mascota.
	 * @return El tipo de animal de la mascota.
	 */
	public static TipoAnimal obtenerTipoAnimal(String nombreVulgar) {
		switch (nombreVulgar) {
		case "Perro":
		case "Gato":
		case "Ratón":
		case "Conejo":
		case "Caballo":
			return TipoAnimal.MAMIFERO;
		case "Loro":
		case "Canario":
		case "Águila":
		case "Pingüino":
		case "Búho":
			return TipoAnimal.AVE;
		case "Tortuga":
		case "Serpiente":
		case "Cocodrilo":
		case "Iguana":
		case "Lagarto":
			return TipoAnimal.REPTIL;
		case "Pez Dorado":
		case "Tiburón":
		case "Salmón":
		case "Pez Betta":
		case "Anguila":
			return TipoAnimal.PEZ;
		default:
			return TipoAnimal.DESCONOCIDO;
		}
	}

	/**
	 * Carga los propietarios guardados en la base de datos en el ChoiceBox.
	 */
	private void cargarPropietarios() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			List<Propietario> propietarios = em.createQuery("SELECT p FROM Propietario p", Propietario.class)
					.getResultList();
			System.out.println("Propiearios disponibles :" + propietarios);
			List<String> nombresPropietarios = propietarios.stream().map(Propietario::getNombre).toList();
			propietarioChoiceBox.getItems().addAll(nombresPropietarios);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	/**
	 * Regresa al menu de la aplicacion.
	 * 
	 * @param event Evento de accion que desencada el metodo.
	 * @throws IOException Si ocurre un error al carga la escena.
	 */
	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/menuPetCare.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Redirige a la escena de registro de propietarios.
	 * 
	 * @param event Evento de accion que desencada el metodo.
	 * @throws IOException Si ocurre un error al carga la escena.
	 */
	public void registrarPropietario(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/altaPropietario.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Convierte una cadena a un entero.
	 * 
	 * Si la cadena es nula o vacía, devuelve -1.
	 * 
	 * @param str La cadena a convertir.
	 * @return El entero obtenido a partir de la cadena, o -1 si la cadena es nula o
	 *         vacía.
	 */
	private Integer parseInt(String str) {
		Integer num;

		if (str == null || str.isBlank())
			num = -1;
		else
			num = Integer.parseInt(str);

		return num;

	}

	/**
	 * Obtiene los datos comunes de la mascota ingresados en la interfaz gráfica.
	 * 
	 * @return La instancia de Mascota con los datos ingresados, o null si falta
	 *         algún dato.
	 */
	public Mascota obtenerDatosComunes() {
		String apodo = apodoField.getText();
		String nombreVulgar = animalChoiceBox.getValue();
		String nombreCientifico = nombreCientificoLabel.getText();
		String tipoAnimal = tipoAnimalLabel.getText();
		char sexo = machoRadioButton.isSelected() ? 'M' : 'H';
		Integer anioNacimiento = parseInt(anioNacimientoField.getText());
		String propietario = propietarioChoiceBox.getValue();

		if (apodo.isBlank() || nombreVulgar == null || nombreVulgar.isBlank() || nombreCientifico.isBlank()
				|| tipoAnimal.isBlank() || anioNacimiento == -1 || propietario == null || propietario.isBlank()) {
			System.out.println("Por favor complete todos los campos.");
			return null;
		}

		Propietario prop = obtenerPropietarioDesdeNombre(propietario);

		return new Mascota(apodo, nombreVulgar, nombreCientifico, sexo, anioNacimiento, prop, tipoAnimal);
	}

	/**
	 * Obtiene un Propietario a partir de su nombre.
	 * 
	 * @param nombrePropietario El nombre del propietario a buscar.
	 * @return El Propietario encontrado.
	 */
	private Propietario obtenerPropietarioDesdeNombre(String nombrePropietario) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			TypedQuery<Propietario> query = em.createQuery("SELECT p FROM Propietario p WHERE p.nombre = :nombre",
					Propietario.class);
			query.setParameter("nombre", nombrePropietario);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}

	/**
	 * Metodo que crea una nueva mascota y la persiste en la base de datos.
	 * 
	 * @param event Evento de accion que desencada el metodo.
	 */
	public void crearMascota(ActionEvent event) {
		try {
			Mascota m = obtenerDatosComunes();
			if (m == null) {
				System.out.println("Por favor complete todos los campos.");
				return;
			}
			System.out.println("Mi mascota: " + m);

			EntityManagerFactory emfactory = null;
			EntityManager em = null;

			try {
				emfactory = Persistence.createEntityManagerFactory("PetCare");
				em = emfactory.createEntityManager();

				em.getTransaction().begin();

				// Obtenemos el propietario
				Propietario propietario = m.getPropietario();
				System.out.println("propietario = " + propietario);

				// Persistimos el propietario si no existe ya en la base de datos
				Propietario propietarioExistente = em.find(Propietario.class, propietario.getDni());
				if (propietarioExistente == null) {
					em.persist(propietario);
					em.flush();
				} else {
					propietario = propietarioExistente;
					m.setPropietario(propietario);
				}

				// Persistimos la mascota
				em.persist(m);

				em.getTransaction().commit();
				System.out.println("Propietario y mascota añadidos correctamente");

			} catch (Exception e) {
				System.out.println("Error al añadir el propietario y la mascota:");
				e.printStackTrace();
				if (em != null) {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
