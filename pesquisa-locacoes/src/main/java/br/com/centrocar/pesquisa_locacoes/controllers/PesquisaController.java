package br.com.centrocar.pesquisa_locacoes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.com.centrocar.pesquisa_locacoes.dao.LocacaoDAO;
import br.com.centrocar.pesquisa_locacoes.models.Locacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PesquisaController implements Initializable {

	@FXML
	private TableColumn<Locacao, String> colRua;
	@FXML
	private TableColumn<Locacao, Double> colProfundidade;
	@FXML
	private TableColumn<Locacao, Double> colLargura;
	@FXML
	private JFXButton btPesquisar;
	@FXML
	private TableColumn<Locacao, String> colPrateleira;
	@FXML
	private TableColumn<Locacao, Double> colAltura;
	@FXML
	private TableColumn<Locacao, String> colTipo;
	@FXML
	private TableColumn<Locacao, String> colArea;
	@FXML
	private TableColumn<Locacao, Integer> colID;
	@FXML
	private TableColumn<Locacao, String> colLocal;
	@FXML
	private TableView<Locacao> tvLocacoes;
	@FXML
	private JFXTextField txPesquisa;
	@FXML
	private JFXTextField txTipo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btPesquisar.setOnAction(event -> {
			try {
				initLocacoes();
				//cbTipo.setValue(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		txPesquisa.setOnKeyReleased(event -> {
			try {
				initLocacoes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		txTipo.setOnKeyReleased(event -> {
			try {
				initLocacoes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}

	private void initLocacoes() throws Exception {
		colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		colArea.setCellValueFactory(new PropertyValueFactory<>("area")); 
		colID.setCellValueFactory(new PropertyValueFactory<>("id")); 
		colLargura.setCellValueFactory(new PropertyValueFactory<>("largura")); 
		colLocal.setCellValueFactory(new PropertyValueFactory<>("local"));
		colPrateleira.setCellValueFactory(new PropertyValueFactory<>("prateleira"));
		colProfundidade.setCellValueFactory(new PropertyValueFactory<>("profundidade"));
		colRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		tvLocacoes.setItems(listaLocacoes());
	}

	private ObservableList<Locacao> listaLocacoes() throws Exception {
		LocacaoDAO dao = new LocacaoDAO();
		return FXCollections.observableArrayList(dao.buscaLocacoes(txPesquisa.getText(), txTipo.getText()));
	}

}
