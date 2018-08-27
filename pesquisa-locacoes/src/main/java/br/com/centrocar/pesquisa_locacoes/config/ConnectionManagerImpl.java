package br.com.centrocar.pesquisa_locacoes.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManagerImpl implements ConnectionManager {
	
	private static ConnectionManagerImpl manager;
	private final String url;
	private final String user;
	private final String pass;
	
	public ConnectionManagerImpl() { 
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.centrocar.pesquisa_locacoes.util.db");
		url = bundle.getString("url");
		user = bundle.getString("user");
		pass = bundle.getString("pass");
	}
	
	public static ConnectionManagerImpl getConnection() {
		if (manager == null) {
			manager = new ConnectionManagerImpl();
		}
		
		return manager;
	}

	@Override
	public java.sql.Connection open() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}

	@Override
	public void close(java.sql.Connection conn) throws SQLException {
		conn.close();
	}

}
