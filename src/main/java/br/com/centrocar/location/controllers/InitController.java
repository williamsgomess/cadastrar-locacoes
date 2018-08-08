package br.com.centrocar.location.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import br.com.centrocar.location.dao.LocacaoDAO;
import br.com.centrocar.location.models.Almoxarifado;
import br.com.centrocar.location.models.Locacao;
import br.com.centrocar.location.models.TipoLocacao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.cbTipo.setValue(TipoLocacao.PEQ);
		this.cbAlmoxarifado.setValue(Almoxarifado.CENTROCAR_ESTOQUE);

		cbTipo.setItems(FXCollections.observableArrayList(TipoLocacao.values()));
		cbAlmoxarifado.setItems(FXCollections.observableArrayList(Almoxarifado.values()));

		btSalvar.setOnAction(event -> {

			try {
				Locacao locacao1 = new Locacao(txLocal.getText());
				Integer qtd = Integer.parseInt(txQuantidade.getText());
				for (int i = 0; i < qtd; i++) {

					adicionaLocalAutomatico(locacao1.getLocal(), i);

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
				
				Locacao locacao = new Locacao(txLocal.getText());
				if (filial.equals(Almoxarifado.CENTROCAR_ESTOQUE.toString()) 
						|| filial.equals(Almoxarifado.CENTROCAR_VITRINE.toString())) {
					
					separaLocalCentrocar(filial, locacao.getLocal());
					System.out.println("C4");

				}
				if (filial.equals(Almoxarifado.CENTROSERVICE_ESTOQUE.toString())) {
					separaLocalC2(filial, locacao.getLocal());
					System.out.println("C2");
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@FXML
    void atualiza(ActionEvent event) {

    }
	
	private void initLocacoes() throws Exception {
		colNumeroMovimento.setCellValueFactory(new PropertyValueFactory<>("numeroDeOrdem"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoParceiro"));
		colTotalLiquido.setCellValueFactory(new PropertyValueFactory<>("totalLiquido"));
		colData.setCellValueFactory(new PropertyValueFactory<>("dataDeEntrada"));
		tbListaDeNotasFiscais.setItems(listaNotasFiscais());
	}

	private ObservableList<NotaFiscal> listaNotasFiscais() throws Exception {
		NotaFiscalDAO dao = new NotaFiscalDAO();
		nota.setDataDeEntrada(dataPicker.getValue());
		nota.setCodigoFilialContabil(txCodigoFilial.getText());
		nota.setCodigoParceiro(txCodigoParceiro.getText());
		nota.setDescricaoFilialContabil(txDescricaoFilial.getText());
		nota.setDescricaoParceiro(txDescricaoParceiro.getText());
		nota.setNumeroDeOrdem(txNumeroNotaFiscal.getText());

		return FXCollections.observableArrayList(dao.listarNotas(nota));
	}


	private void separaLocalCentrocar(String filial, String local) {
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

	private void separaLocalC2(String filial, String local) {
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

		Locacao locacao = new Locacao(loc);
		locacao.setAltura(Double.parseDouble(txAltura.getText()));
		locacao.setArea(txArea.getText());
		locacao.setLargura(Double.parseDouble(txLargura.getText()));
		locacao.setPrateleira(txPrateleira.getText());
		locacao.setProfundidade(Double.parseDouble(txProfundidade.getText()));
		locacao.setRua(txRua.getText());
		locacao.setTipo(cbTipo.getValue());
		new LocacaoDAO().adiciona(locacao);
		
		System.out.println("Gravado!");

	}

}
