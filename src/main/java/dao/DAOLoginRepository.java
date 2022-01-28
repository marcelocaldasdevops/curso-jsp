package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private static Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin login) throws SQLException {
		String sql = "select * from \"curso-jsp\".model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login.getLogin());
		statement.setString(2, login.getSenha());
		
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			return true;
		}
		
		
		return false;
	}
}
