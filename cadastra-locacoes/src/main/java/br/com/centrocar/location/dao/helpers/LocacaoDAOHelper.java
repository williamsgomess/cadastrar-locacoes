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
		String tipo = rs.getString("TIPO");
		getTipoLocacao(rs, loc, tipo);

		
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
		stmt.setString(8, locacao.getTipo().getTipo());
	}

	private void getTipoLocacao(ResultSet rs, Locacao loc, String tipo) throws SQLException {
		if (tipo.equals("PEQ")) {
			loc.setTipo(TipoLocacao.PEQ);
		} if (tipo.equals("MED")) {
			loc.setTipo(TipoLocacao.MED);
		} if (tipo.equals("GRD")) {
			loc.setTipo(TipoLocacao.GRD);
		}if (tipo.equals("PESADO")) {
			loc.setTipo(TipoLocacao.PESADO);
		} if (tipo.equals("LATARIA")) {
			loc.setTipo(TipoLocacao.LATARIA);
		}  if (tipo.equals("RADIADOR")) {
			loc.setTipo(TipoLocacao.RADIADOR);
		}  if (tipo.equals("ESCAPAMENTO")) {
			loc.setTipo(TipoLocacao.ESCAPAMENTO);
		}  if (tipo.equals("PARA-CHOQUE")) {
			loc.setTipo(TipoLocacao.PARA_CHOQUE);
		} if (tipo.equals("PALHETA")) {
			loc.setTipo(TipoLocacao.PALHETA);
		}
	}
	
	public void pegaDadosParaAtualizar(Locacao locacao, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, locacao.getArea());
		stmt.setString(2, locacao.getRua());
		stmt.setString(3, locacao.getPrateleira());
		stmt.setString(4, locacao.getLocal());
		stmt.setDouble(5, locacao.getAltura());
		stmt.setDouble(6, locacao.getLargura());
		stmt.setDouble(7, locacao.getProfundidade());
		stmt.setString(8, locacao.getTipo().getTipo());
		stmt.setInt(9, locacao.getId());
		
		stmt.execute();
	}
}