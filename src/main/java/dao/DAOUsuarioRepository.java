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

	public ModelLogin gravarUsuario(ModelLogin usuario, Long usaerLogado) throws Exception {

		if (usuario.isNovo()) {

			String sql = "INSERT INTO \"curso-jsp\".model_login (login, senha, nome, email, usuario_id, perfil, sexo) VALUES(?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement gravar = connection.prepareStatement(sql);
			gravar.setString(1, usuario.getLogin());
			gravar.setString(2, usuario.getSenha());
			gravar.setString(3, usuario.getNome());
			gravar.setString(4, usuario.getEmail());
			gravar.setLong(5, usaerLogado);
			gravar.setString(6, usuario.getPerfil());
			gravar.setString(7, usuario.getSexo());

			gravar.execute();
			connection.commit();
			
			if(usuario.getFotouser() !=null && !usuario.getFotouser().isEmpty()) {
				sql = "update \"curso-jsp\".model_login set fotouser =?, extersaofotouser=?  where login=?";
				
				gravar = connection.prepareStatement(sql);
				
				gravar.setString(1, usuario.getFotouser());
				gravar.setString(2, usuario.getExtersaofotouser());
				gravar.setString(3, usuario.getLogin());
				
				gravar.execute();
				connection.commit();
				
			}
			
		} else {
			String sql = "UPDATE \"curso-jsp\".model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=? WHERE id="
					+ usuario.getId();

			PreparedStatement atualizar = connection.prepareStatement(sql);
			atualizar.setString(1, usuario.getLogin());
			atualizar.setString(2, usuario.getSenha());
			atualizar.setString(3, usuario.getNome());
			atualizar.setString(4, usuario.getEmail());
			atualizar.setString(5, usuario.getPerfil());
			atualizar.setString(6, usuario.getSexo());

			atualizar.executeUpdate();
			connection.commit();
			
			if(usuario.getFotouser() !=null && !usuario.getFotouser().isEmpty()) {
				sql = "update \"curso-jsp\".model_login set fotouser =?, extersaofotouser=?  where id=?";
				
				atualizar = connection.prepareStatement(sql);
				
				atualizar.setString(1, usuario.getFotouser());
				atualizar.setString(2, usuario.getExtersaofotouser());
				atualizar.setLong(3, usuario.getId());
				
				atualizar.execute();
				connection.commit();
				
			}
		}
		return this.consultaUsuario(usuario.getLogin());
	}

	public ModelLogin consultaUsuarioLogado(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT * FROM \"curso-jsp\".model_login where upper(login) = upper(?) ";

		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, login);

		ResultSet resultado = consultar.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT * FROM \"curso-jsp\".model_login where upper(login) = upper(?) and useradmin is false ";

		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, login);

		ResultSet resultado = consultar.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT * FROM \"curso-jsp\".model_login where upper(login) = upper(?) and useradmin is false and usuario_id = ?";

		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, login);
		consultar.setLong(2, userLogado);

		ResultSet resultado = consultar.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuarioId(String id, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT * FROM \"curso-jsp\".model_login where id = ? and useradmin is false and usuario_id = ?";

		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setLong(1, Long.parseLong(id));
		consultar.setLong(2, userLogado);

		ResultSet resultado = consultar.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public List<ModelLogin> consultaUsuariList(String nome, Long userLogado) throws Exception {
		List<ModelLogin> retorno = new ArrayList<>();

		String sql = "SELECT * FROM \"curso-jsp\".model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, "%" + nome + "%");
		consultar.setLong(2, userLogado);

		ResultSet resultado = consultar.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public List<ModelLogin> UsuariosiLista(Long userLogado) throws Exception {
		List<ModelLogin> retorno = new ArrayList<>();

		String sql = "SELECT * FROM \"curso-jsp\".model_login where useradmin = false and usuario_id = ? ";
		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setLong(1, userLogado);

		ResultSet resultado = consultar.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT count(1) > 0 as existe FROM \"curso-jsp\".model_login where upper(login) = upper(?) ";

		PreparedStatement consultar = connection.prepareStatement(sql);
		consultar.setString(1, login);

		ResultSet resultado = consultar.executeQuery();

		if (resultado.next()) {
			return resultado.getBoolean("existe");
		}

		return false;
	}

	public void deletarUser(String id) throws Exception {
		String sql = "DELETE FROM \"curso-jsp\".model_login WHERE id = ? and useradmin is false";

		PreparedStatement deletar = connection.prepareStatement(sql);
		deletar.setLong(1, Long.parseLong(id));
		deletar.executeUpdate();
		connection.commit();

	}
}
