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
 * Aqui são feitas todas as manipulações no banco de dados.
 * Busca locacões, Altera locações e Cria locações.
 * 
 * @author Williams Gomes
 * @see ConnectionManager
 * @see LocacaoDAOHelper
 */
public class LocacaoDAO {
	
	/**
	 * @see ConnectionManager
	 */
	private final ConnectionManager manager;
	
	/**
	 * Construtor iniciando uma instância de conexão.
	 * 
	 * @see ConnectionManagerImpl
	 */
	public LocacaoDAO() {
		this.manager = ConnectionManagerImpl.getConnection();
	}
	
	/**
	 * Aqui é realizado a inserção de uma nova locação.
	 * 
	 * @param locacao
	 * @throws SQLException
	 */
	public void adiciona(Locacao locacao) throws SQLException {
		Connection conn = manager.open();
		String sql = "INSERT INTO locacao (area, rua, prateleira, local, altura, largura, profundidade, tipo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			new LocacaoDAOHelper().populaParamentros(locacao, stmt);
			stmt.executeUpdate();
		}
	}
	
	/**
	 * Aqui acontece a busca das locações cadastradas no banco de dados.
	 * @return Uma lista de locações.
	 * @throws Exception
	 */
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

	/**
	 * Aqui acontece a alteração de uma locação.
	 * @param locacao
	 * @throws SQLException
	 */
	public void altera(Locacao locacao) throws SQLException {
		Connection open = manager.open();
		
		String sql = "UPDATE LOCACAO SET AREA = ?, RUA = ?, PRATELEIRA = ?, LOCAL = ?,"
				+ " ALTURA = ?, LARGURA = ?, PROFUNDIDADE = ?, TIPO = ? WHERE ID = ?";
		
		try (PreparedStatement stmt = open.prepareStatement(sql)) {
			new LocacaoDAOHelper().pegaDadosParaAtualizar(locacao, stmt);
		}
	}
}
