package br.com.centrocar.location.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	private TableColumn<Locacao, String> colTipo;
	@FXML
	private TableColumn<Locacao, Integer> colId;
	@FXML
	private TableColumn<Locacao, String> colLocal;
	@FXML
	private TableColumn<Locacao, Boolean> selectedCol;

	private Locacao locacao;
	private Integer id = null;

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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btSalvar.setOnAction(event -> {
			try {
				this.locacao = new Locacao(txLocal.getText());
				if (this.id != null) {
					gravaAlteracoesDaLocacao();
					this.id = null;
					resetaCampos();
				} else {

					if (jaExiste(locacao)) {
						JOptionPane.showMessageDialog(null, "Locação " + locacao.getLocal() + " já cadastrada!",
								"Alerta!", JOptionPane.WARNING_MESSAGE);
					} else {
						Integer qtd = Integer.parseInt(txQuantidade.getText());
						Integer valor = 0;
						for (int i = 0; i < qtd; i++) {
							valor = adicionaLocalAutomatico(valor);
						}
						//resetaCampos();
						JOptionPane.showMessageDialog(null, "Locação " + locacao.getLocal() + " salva com sucesso!",
								"Cadastro realizado!", JOptionPane.INFORMATION_MESSAGE);
					}
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
				desabilitaCampos(false);
				String filial = cbAlmoxarifado.getValue().toString();
				this.locacao = new Locacao(txLocal.getText());
				verificaAlmoxarifadoSelecionado(filial);
				this.id = null;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Verifique se a filial está correta!", "Erro de formatação",
					JOptionPane.WARNING_MESSAGE);
			desabilitaCampos(true);

		} finally {
			desabilitaCampos(false);
		}
	}

	private void desabilitaCampos(boolean b) {
		txPrateleira.setDisable(b);
		txArea.setDisable(b);
		txRua.setDisable(b);
		txQuantidade.setDisable(b);
		cbTipo.setDisable(b);
		txLargura.setDisable(b);
		txAltura.setDisable(b);
		txProfundidade.setDisable(b);
		btSalvar.setDisable(b);
	}

	@FXML
	void atualiza(KeyEvent event) {
		try {
			final KeyCombination F5 = new KeyCodeCombination(KeyCode.F5);
			if (F5.match(event)) {
				initLocacoes();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verificaAlmoxarifadoSelecionado(String filial) {
		if (filial.equals(Almoxarifado.CENTROCAR_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.CENTROCAR_VITRINE.toString()))
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());

		if (filial.equals(Almoxarifado.CENTROSERVICE_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.CENTROSERVICE_ESTOQUE.toString()))
			separaLocalMegaPecas(filial, locacao.getLocal());

		if (filial.equals(Almoxarifado.DISPNEU_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.DISPNEU_ESTOQUE.toString()))
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());

		if (filial.equals(Almoxarifado.MEGA_ESTOQUE.toString()) || filial.equals(Almoxarifado.MEGA_VITRINE.toString()))
			separaLocalMegaPecas(filial, locacao.getLocal());

		if (filial.equals(Almoxarifado.PNEUSTIL_ESTOQUE.toString())
				|| filial.equals(Almoxarifado.PNEUSTIL_VITRINE.toString()))
			separaLocalCentrocarCentroServicePneuStil(filial, locacao.getLocal());
	}

	private void initUpdate() throws SQLException {
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

	private Integer adicionaLocalAutomatico(Integer valor) throws Exception {
		String[] localSeparado = locacao.getLocal().split("\\.");
		String um = localSeparado[0];
		String dois = localSeparado[1];
		String tres = localSeparado[2];
		Integer qt = Integer.parseInt(localSeparado[3]);

		Integer soma = qt + valor;
		String loc = um + "." + dois + "." + tres + "." + String.format("%03d", soma);

		System.out.println(loc);
		salvaLocacao(loc);
		System.out.println("Gravado!");
		valor = +1;
		return valor;
	}

	private void salvaLocacao(String loc) throws Exception, SQLException {
		locacao.setAltura(Double.parseDouble(txAltura.getText()));
		locacao.setArea(txArea.getText());
		locacao.setLargura(Double.parseDouble(txLargura.getText()));
		locacao.setPrateleira(txPrateleira.getText());
		locacao.setProfundidade(Double.parseDouble(txProfundidade.getText()));
		locacao.setRua(txRua.getText());
		locacao.setTipo(cbTipo.getValue());
		locacao.setLocal(loc);

		new LocacaoDAO().adiciona(locacao);

		initLocacoes();
	}

	private void gravaAlteracoesDaLocacao() throws Exception {
		locacao.setAltura(Double.parseDouble(txAltura.getText()));
		locacao.setArea(txArea.getText());
		locacao.setLargura(Double.parseDouble(txLargura.getText()));
		locacao.setPrateleira(txPrateleira.getText());
		locacao.setProfundidade(Double.parseDouble(txProfundidade.getText()));
		locacao.setRua(txRua.getText());
		locacao.setTipo(cbTipo.getValue());
		locacao.setLocal(txLocal.getText());
		locacao.setId(this.id);

		new LocacaoDAO().altera(locacao);

		JOptionPane.showMessageDialog(null, "Locação " + locacao.getLocal() + " atualizada com sucesso!",
				"Atualização bem sucedida!", JOptionPane.INFORMATION_MESSAGE);

		initLocacoes();
	}

	private static boolean jaExiste(Locacao locacao) throws Exception {
		List<Locacao> locacoes = new LocacaoDAO().buscaLocacoes();
		if (locacoes.contains(locacao)) {
			return true;
		} else {
			return false;
		}

	}

	private void resetaCampos() {
		txLocal.setText("");
		txPrateleira.setText("");
		txArea.setText("");
		txRua.setText("");
		txQuantidade.setText("1");
		this.id = 0;
		cbTipo.setValue(TipoLocacao.PEQ);
		txLargura.setText("0.0");
		txAltura.setText("0.0");
		txProfundidade.setText("0.0");
	}
}
