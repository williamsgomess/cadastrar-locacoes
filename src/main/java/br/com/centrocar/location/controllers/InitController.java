package br.com.centrocar.location.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import br.com.centrocar.location.models.Locacao;
import br.com.centrocar.location.models.TipoLocacao;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//this.txLocal.setText("C2A-01-A-001");

	}

	@FXML
	void pulaCampo(KeyEvent event) {

		try {

			final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);

			if (TAB.match(event)) {
				String filial;
				filial = "00002";
				Locacao locacao = new Locacao(txLocal.getText(), TipoLocacao.GRD);
				if(filial.equals("00001")) {
					separaLocalCentrocar(filial, locacao.getLocal());
					
				} else if (filial.equals("00002")) {
					separaLocalC2(filial, locacao.getLocal());
					System.out.println("C2");
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		String prateleira = um + "." + dois + "." + tres;
		String rua = um + "." + dois;
		String area = um;

		this.txPrateleira.setText(prateleira);
		this.txRua.setText(rua);
		this.txArea.setText(area);
	}

}
