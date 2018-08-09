package br.com.centrocar.location.dao.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.centrocar.location.models.Locacao;
import br.com.centrocar.location.models.TipoLocacao;

public class LocacaoDAOHelper {

	public void pegaResultadoDaPesquisa(List<Locacao> locacoes, ResultSet rs) throws SQLException {
		@SuppressWarnings("deprecation")
		Locacao loc = new Locacao();
		
		loc.setId(rs.getInt("ID"));
		loc.setAltura(rs.getDouble("ALTURA"));
		loc.setArea(rs.getString("AREA"));
		loc.setLargura(rs.getDouble("LARGURA"));
		loc.setLocal(rs.getString("LOCAL"));
		loc.setPrateleira(rs.getString("PRATELEIRA"));
		loc.setProfundidade(rs.getDouble("PROFUNDIDADE"));
		loc.setRua(rs.getString("RUA"));
		
		getTipoLocacao(rs, loc); 
		
		locacoes.add(loc);
	}
	
	public void populaParamentros(Locacao locacao, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, locacao.getArea());
		stmt.setString(2, locacao.getRua());
		stmt.setString(3, locacao.getPrateleira());
		stmt.setString(4, locacao.getLocal());
		stmt.setDouble(5, locacao.getAltura());
		stmt.setDouble(6, locacao.getLargura());
		stmt.setDouble(7, locacao.getProfundidade());
		stmt.setInt(8, locacao.getTipo().getTipo());
	}

	private void getTipoLocacao(ResultSet rs, Locacao loc) throws SQLException {
		Integer tipo = rs.getInt("TIPO");
		if (tipo == 1) {
			loc.setTipo(TipoLocacao.PEQ);
		} if (tipo == 2) {
			loc.setTipo(TipoLocacao.MED);
		} if (tipo == 3) {
			loc.setTipo(TipoLocacao.GRD);
		}if (tipo == 4) {
			loc.setTipo(TipoLocacao.PESADO);
		} if (tipo == 5) {
			loc.setTipo(TipoLocacao.LATARIA);
		}  if (tipo == 6) {
			loc.setTipo(TipoLocacao.RADIADOR);
		}  if (tipo == 7) {
			loc.setTipo(TipoLocacao.ESCAPAMENTO);
		}  if (tipo == 8) {
			loc.setTipo(TipoLocacao.PARA_CHOQUE);
		} if (tipo == 9) {
			loc.setTipo(TipoLocacao.PALHETA);
		}
	}
}