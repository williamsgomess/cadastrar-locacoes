package br.com.centrocar.location.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.centrocar.location.config.ConnectionManager;
import br.com.centrocar.location.config.ConnectionManagerImpl;
import br.com.centrocar.location.dao.helpers.LocacaoDAOHelper;
import br.com.centrocar.location.models.Locacao;

/**
 * 
 * @author Williams Gomes
 *
 */
public class LocacaoDAO {
	
	private final ConnectionManager manager;
	
	public LocacaoDAO() {
		this.manager = ConnectionManagerImpl.getConnection();
	}
	
	public void adiciona(Locacao locacao) throws SQLException {
		Connection conn = manager.open();
		String sql = "INSERT INTO locacao (area, rua, prateleira, local, altura, largura, profundidade, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			new LocacaoDAOHelper().populaParamentros(locacao, stmt);
			stmt.executeUpdate();
		}
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

	public void altera(Locacao locacao) {
		
	}
	
}
