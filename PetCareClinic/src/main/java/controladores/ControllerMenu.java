package controladores;

import java.io.IOException;

import javax.persistence.EntityManager;

import clases.JPAUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerMenu extends Controller {

	@FXML
	private Label contadorMascotas;
	@FXML
	private Label contadorPropietarios;

	@FXML
	public void initialize() {
		contarMascotas();
		contarPropietarios();
	}

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

	private void contarMascotas() {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();

			long totalMascotas = (long) em.createQuery("SELECT COUNT(m) FROM Mascotas m").getSingleResult();
			contadorMascotas.setText(String.valueOf(totalMascotas));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void altaMascota(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/altaMascota.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void bajaMascota(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/bajaMascota.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void editarMascota(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/editarMascota.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void listaMascota(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/listaMascotas.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
