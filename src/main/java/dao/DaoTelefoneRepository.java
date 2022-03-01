package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DaoTelefoneRepository {

	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new  DAOUsuarioRepository();
	
	public DaoTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelTelefone> listFone(Integer idUserPai) throws Exception{
		List<ModelTelefone> retorno = new ArrayList<>();

		String sql = "SELECT * FROM \"curso-jsp\".telefone where usuario_pai_id ="+idUserPai;
		PreparedStatement listar = connection.prepareStatement(sql);
		
		ResultSet rs = listar.executeQuery();
		while (rs.next()) {
			ModelTelefone telefone = new ModelTelefone();
			
			telefone.setId(rs.getInt("id"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioId(rs.getInt("usuario_pai_id")));
			telefone.setUsuario_cad_id(daoUsuarioRepository.consultaUsuarioId(rs.getInt("usuario_cad_id")));
			
			
			retorno.add(telefone);
		}
	
		
		return retorno;
	
	}
	
	public void gravarTelefone(ModelTelefone modelTelefone) throws Exception {
		String sql = "INSERT INTO \"curso-jsp\".telefone (numero, usuario_pai_id, usuario_cad_id) VALUES(?, ?, ?);";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, modelTelefone.getNumero());
		insert.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		insert.setLong(3, modelTelefone.getUsuario_cad_id().getId());
		
		insert.execute();
		
		connection.commit();
	}
	
	public void deleteFone(Integer id) throws Exception{
		String sql = "DELETE FROM \"curso-jsp\".telefone WHERE id=?";
		PreparedStatement delete = connection.prepareStatement(sql);
		delete.setInt(1, id);
		
		delete.executeUpdate();
		connection.commit();
		
	}
	
}
