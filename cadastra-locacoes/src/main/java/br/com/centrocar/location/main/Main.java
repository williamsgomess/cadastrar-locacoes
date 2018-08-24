package br.com.centrocar.location.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Williams Gomes
 *
 * @version 1.0.0
 * @see Application
 */
public class Main extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) {
		Main.stage = stage;
		inicia();
	}

	/**
	 * Inicia os componentes necessários para criar uma cena (view).
	 * </br>
	 * Busca o arquivo fxml responsável por conter a parte visual que aparecerá ao usuário no início da aplicação, 
	 * logo após centraliza a tela.
	 * </br>
	 * Será lançada uma {@code MainException} caso não encontre o arquivo .fxml.
	 * @throws IOException 
	 */
	public void inicia() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Init.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.centerOnScreen();
			stage.setTitle("Cadastramento de Locações");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.getMessage();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
