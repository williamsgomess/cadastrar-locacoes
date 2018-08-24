package br.com.centrocar.pesquisa_locacoes.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	@Override
	public void start(Stage stage) {
		Main.stage = stage;
		inicia();
	}

	public void inicia() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Pesquisa.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.centerOnScreen();
			stage.setTitle("Pesquisa de Locações");
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
