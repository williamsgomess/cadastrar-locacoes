package br.com.centrocar.pesquisa_locacoes.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
	
	Connection open() throws SQLException;
	
	void close(Connection conn) throws SQLException;

}
