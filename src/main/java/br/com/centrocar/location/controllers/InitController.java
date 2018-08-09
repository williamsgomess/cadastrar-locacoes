package br.com.centrocar.location.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import br.com.centrocar.location.dao.LocacaoDAO;
import br.com.centrocar.location.models.Almoxarifado;
import br.com.centrocar.location.models.Locacao;
import br.com.centrocar.location.models.TipoLocacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class InitController implements Initializable {

	@FXML
	private JFXTextField txArea;
	@FXML
	private JFXTextField txAltura;
	@FXML
	private JFXTextField txRua;
	@FXML
	private JFXTextField txProfundidade;
	@FXML
	private JFXTextField txLargura;
	@FXML
	private JFXButton btSalvar;
	@FXML
	private JFXTextField txLocal;
	@FXML
	private JFXTextField txPrateleira;
	@FXML
	private JFXComboBox<TipoLocacao> cbTipo;
	@FXML
	private JFXComboBox<Almoxarifado> cbAlmoxarifado;
	@FXML
	private JFXTextField txQuantidade;
	@FXML
	private TableView<Locacao> tbLocacoes;
	@FXML
	private TableColumn<Locacao, String> colRua;
	@FXML
	private TableColumn<Locacao, Double> colProfundidade;
	@FXML
	private TableColumn<Locacao, String> colArea;
	@FXML
	private TableColumn<Locacao, Double> colLargura;
	@FXML
	private TableColumn<Locacao, String> colPrateleira;
	@FXML
	private TableColumn<Locacao, Double> colAltura;
	@FXML
	private TableColumn<Locacao, Integer> colTipo;
	@FXML
	private TableColumn<Locacao, Integer> colId;
	@FXML
	private TableColumn<Locacao, String> colLocal;

	private Locacao locacao;
	private Integer id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.cbTipo.setValue(TipoLocacao.PEQ);
		this.cbAlmoxarifado.setValue(Almoxarifado.CENTROCAR_ESTOQUE);

		cbTipo.setItems(FXCollections.observableArrayList(TipoLocacao.values()));
		cbAlmoxarifado.setItems(FXCollections.observableArrayList(Almoxarifado.values()));
		
		tbLocacoes.setOnMousePressed(event -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				try {
					initUpdate();
					System.out.println(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btSalvar.setOnAction(event -> {
			try {
				this.locacao = new Locacao(txLocal.getText());
				Integer qtd = Integer.parseInt(txQuantidade.getText());
				for (int i = 0; i < qtd; i++) {
					adicionaLocalAutomatico(locacao.getLocal(), i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void pulaCampo(KeyEvent event) {
		try {
			final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);

			if (TAB.match(event)) {
				String filial = cbAlmoxarifado.getValue().toString();
				this.locacao = new Locacao(txLocal.getText());
				
				verificaAlmoxarifacoSelecionado(filial);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void verificaAlmoxarifacoSelecionado(String filial) {
		if (filial.equals(Almoxarifado.CENTROCAR_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.CENTROCAR_VITRINE.toString())) 
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());

		if (filial.equals(Almoxarifado.CENTROSERVICE_ESTOQUE.toString())) 
			separaLocalMegaPecas(filial, locacao.getLocal());
		
		if (filial.equals(Almoxarifado.DISPNEU_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.DISPNEU_ESTOQUE.toString())) 
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());
		
		if (filial.equals(Almoxarifado.MEGA_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.MEGA_VITRINE.toString())) 
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());
		
		if (filial.equals(Almoxarifado.PNEUSTIL_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.PNEUSTIL_VITRINE.toString())) 
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());
		
	}

	@FXML
	void atualiza(KeyEvent event) {
		try {
			final KeyCombination F5 = new KeyCodeCombination(KeyCode.F5);
			if (F5.match(event)) initLocacoes();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initUpdate() {
		locacao = tbLocacoes.getSelectionModel().getSelectedItem();
		txAltura.setText(locacao.getAltura().toString());
		txArea.setText(locacao.getArea());
		txLargura.setText(locacao.getAltura().toString());
		txLocal.setText(locacao.getLocal());
		txPrateleira.setText(locacao.getPrateleira());
		txProfundidade.setText(locacao.getProfundidade().toString());
		txRua.setText(locacao.getRua());
		cbTipo.setValue(locacao.getTipo());
		this.id = locacao.getId();
		
	}

	private void initLocacoes() throws Exception {
		colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		colArea.setCellValueFactory(new PropertyValueFactory<>("area"));
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colLargura.setCellValueFactory(new PropertyValueFactory<>("largura"));
		colLocal.setCellValueFactory(new PropertyValueFactory<>("local"));
		colPrateleira.setCellValueFactory(new PropertyValueFactory<>("prateleira"));
		colProfundidade.setCellValueFactory(new PropertyValueFactory<>("profundidade"));
		colRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		tbLocacoes.setItems(listaLocacoes());
	}

	private ObservableList<Locacao> listaLocacoes() throws Exception {
		LocacaoDAO dao = new LocacaoDAO();
		return FXCollections.observableArrayList(dao.buscaLocacoes());
	}

	private void separaLocalCentrocarCentroServicePneuStil(String filial, String local) {
		String[] localSeparado = local.split("\\.");
		String um = localSeparado[0];
		String dois = localSeparado[1];
		String tres = localSeparado[2];

		String prateleira = um + "." + dois + "." + tres;
		String rua = um + "." + dois;
		String area = um;

		this.txPrateleira.setText(prateleira);
		this.txRua.setText(rua);
		this.txArea.setText(area);
	}

	private void separaLocalMegaPecas(String filial, String local) {
		String[] localSeparado = local.split("-");
		String um = localSeparado[0];
		String dois = localSeparado[1];
		String tres = localSeparado[2];

		String prateleira = um + "-" + dois + "-" + tres;
		String rua = um + "-" + dois;
		String area = um;

		this.txPrateleira.setText(prateleira);
		this.txRua.setText(rua);
		this.txArea.setText(area);
	}

	private void adicionaLocalAutomatico(String local, Integer qtd) throws Exception {
		String[] localSeparado = local.split("\\.");
		String um = localSeparado[0];
		String dois = localSeparado[1];
		String tres = localSeparado[2];
		Integer qt = Integer.parseInt(localSeparado[3]);

		Integer soma = qt + qtd;
		String loc = um + "." + dois + "." + tres + "." + String.format("%03d", soma);

		System.out.println(loc);
		salvaLocacao(loc);
		System.out.println("Gravado!");
	}

	private void salvaLocacao(String loc) throws Exception, SQLException {
		Locacao locacao = new Locacao(loc);
		locacao.setAltura(Double.parseDouble(txAltura.getText()));
		locacao.setArea(txArea.getText());
		locacao.setLargura(Double.parseDouble(txLargura.getText()));
		locacao.setPrateleira(txPrateleira.getText());
		locacao.setProfundidade(Double.parseDouble(txProfundidade.getText()));
		locacao.setRua(txRua.getText());
		locacao.setTipo(cbTipo.getValue());
		new LocacaoDAO().adiciona(locacao);
	}

}
