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

public class ControllerAltaPropietario extends Controller {

	// Atributos del AltaPropietario.fmxl
	@FXML
	private TextField dniTextField;
	@FXML
	private TextField nombreTextField;
	@FXML
	private TextField direccionTextField;

	public void verificarDatos() {
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

	private void aniadirPropietario(String dni, String nombre, String direccion) {
		/*
		 * // EntityManager em = JPAUtil.getEntityManager(); Session session =
		 * sessionFactory.openSession(); Transaction tx = null; String propietarioID =
		 * null;
		 * 
		 * try { tx = session.beginTransaction(); Propietario propietario = new
		 * Propietario(dni, nombre, direccion); propietarioID = (String)
		 * session.save(propietario); tx.commit(); } catch (HibernateException e) { if
		 * (tx != null) tx.rollback(); e.printStackTrace(); } finally { session.close();
		 * }
		 * 
		 * return propietarioID;
		 */

		EntityManagerFactory emfactory = null;
		EntityManager em = null;

		try {
			emfactory = Persistence.createEntityManagerFactory("PetCare");
			em = emfactory.createEntityManager();

			em.getTransaction().begin();

			Propietario propietario = new Propietario(dni, nombre, direccion);
			System.out.println(propietario);

			em.persist(propietario);

			em.getTransaction().commit();
			System.out.println("Propietario añadido correctamente");

		} catch (Exception e) {
			System.out.println("Error al añadir el propietario:");
			e.printStackTrace();
			if (em != null)
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
			if (emfactory != null)
				emfactory.close();
		}
	}

	/**
	 * Método para volver al menú
	 * 
	 * @param event
	 * @throws IOException
	 */

	public void volver(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/altaMascota.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
