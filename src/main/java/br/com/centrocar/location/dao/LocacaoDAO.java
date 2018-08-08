package br.com.centrocar.location.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.centrocar.location.config.ConnectionManager;
import br.com.centrocar.location.config.ConnectionManagerImpl;
import br.com.centrocar.location.models.Locacao;
import br.com.centrocar.location.models.TipoLocacao;


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
			stmt.setString(1, locacao.getArea());
			stmt.setString(2, locacao.getRua());
			stmt.setString(3, locacao.getPrateleira());
			stmt.setString(4, locacao.getLocal());
			stmt.setDouble(5, locacao.getAltura());
			stmt.setDouble(6, locacao.getLargura());
			stmt.setDouble(7, locacao.getProfundidade());
			stmt.setInt(8, locacao.getTipo().getTipo());
			stmt.executeUpdate();
		}
	}
	
	public List<Locacao> seleciona(Locacao locacao) throws Exception {

		List<Locacao> locacoes = new ArrayList<>();
        Connection conn = manager.open();
        Locacao loc = new Locacao();

        String sql = "SELECT * FROM LOCACAO";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    loc.setId(rs.getInt(""));
                    loc.setAltura(rs.getDouble(""));
                    loc.setArea(rs.getString(""));
                    loc.setLargura(rs.getDouble(""));
                    loc.setLocal(rs.getString(""));
                    loc.setPrateleira(rs.getString(""));
                    loc.setProfundidade(rs.getDouble(""));
                    loc.setRua(rs.getString(""));
                    loc.setTipo(TipoLocacao.valueOf(rs.getString((""))));
                    
                    locacoes.add(loc);
                }
            }
        }
        return locacoes;
    }

}
