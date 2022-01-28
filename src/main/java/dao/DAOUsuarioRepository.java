package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin usuario) throws Exception {
		
			if(usuario.isNovo()) {
				
			
			String sql = "INSERT INTO \"curso-jsp\".model_login (login, senha, nome, email) VALUES(?, ?, ?, ?);";
		
			PreparedStatement gravar = connection.prepareStatement(sql);
			gravar.setString(1, usuario.getLogin());
			gravar.setString(2, usuario.getSenha());
			gravar.setString(3, usuario.getNome());
			gravar.setString(4, usuario.getEmail());
			
			gravar.execute();
			connection.commit();
			}else {
				String sql = "UPDATE \"curso-jsp\".model_login SET login=?, senha=?, nome=?, email=? WHERE id="+usuario.getId();
				
				PreparedStatement atualizar = connection.prepareStatement(sql);
				atualizar.setString(1, usuario.getLogin());
				atualizar.setString(2, usuario.getSenha());
				atualizar.setString(3, usuario.getNome());
				atualizar.setString(4, usuario.getEmail());
				
				atualizar.executeUpdate();
				connection.commit();
			}
			return this.consultaUsuario(usuario.getLogin());
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		
		String sql = "SELECT * FROM \"curso-jsp\".model_login where upper(login) = upper(?)";
		
		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, login);
		
		ResultSet resultado = consultar.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
		}
		
		return modelLogin;
	}
	public ModelLogin consultaUsuarioId(String id) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		
		String sql = "SELECT * FROM \"curso-jsp\".model_login where id = ?";
		
		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setLong(1, Long.parseLong(id));
		
		ResultSet resultado = consultar.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
		}
		
		return modelLogin;
	}
	
	public List<ModelLogin> consultaUsuariList(String nome) throws Exception {
		List<ModelLogin> retorno = new ArrayList<>();
		
		String sql = "SELECT * FROM \"curso-jsp\".model_login where upper(nome) like upper(?)";
		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, "%"+nome+"%");
		
		ResultSet resultado = consultar.executeQuery();
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT count(1) > 0 as existe FROM \"curso-jsp\".model_login where upper(login) = upper(?)";
		
		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, login);
		
		ResultSet resultado = consultar.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
		
		return false;
	}
	
	public void deletarUser(String id) throws Exception{
		String sql = "DELETE FROM \"curso-jsp\".model_login WHERE id = ?";
		
		PreparedStatement deletar = connection.prepareStatement(sql);
		deletar.setLong(1, Long.parseLong(id));
		deletar.executeUpdate();
		connection.commit();
		
	}
}
