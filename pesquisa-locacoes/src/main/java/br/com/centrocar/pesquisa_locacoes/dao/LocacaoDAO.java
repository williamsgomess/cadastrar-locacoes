package br.com.centrocar.pesquisa_locacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.centrocar.pesquisa_locacoes.config.ConnectionManager;
import br.com.centrocar.pesquisa_locacoes.config.ConnectionManagerImpl;
import br.com.centrocar.pesquisa_locacoes.models.Locacao;

public class LocacaoDAO {
	
	private final ConnectionManager manager;
	
	public LocacaoDAO() {
		this.manager = ConnectionManagerImpl.getConnection();
	}
	
	public List<Locacao> buscaLocacoes() throws Exception {
		List<Locacao> locacoes = new ArrayList<>();
        Connection conn = manager.open();

        String sql = "SELECT * FROM LOCACAO";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	new LocacaoDAOHelper().pegaResultadoDaPesquisa(locacoes, rs);
                }
            }
        }
        return locacoes;
    }
	
	public List<Locacao> buscaLocacoes(String local, String tipo) throws Exception {
		List<Locacao> locacoes = new ArrayList<>();
        Connection conn = manager.open();

        String sql = "SELECT * FROM LOCACAO WHERE ID IS NOT NULL";
        
        if (local != null && local.equals("") == false) {
			sql += " AND LOCAL LIKE '" +local.trim()+ "%'";
		}
        if (tipo != null && tipo.equals("") == false) {
        	sql += " AND TIPO LIKE '%" + tipo.toUpperCase() + "%'";
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	new LocacaoDAOHelper().pegaResultadoDaPesquisa(locacoes, rs);
                }
            }
        }
        return locacoes;
    }
}
