package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.ContatoModel;
import model.Contato;
import model.connection.ConnectionDB;

public class ContatoDAO {

	// query SQL para salvar dados - create.
	public void save(Contato contato) {
		String sql = "INSERT INTO CONTATOS(NOME, IDADE, DATACADASTRO)" 
												  + "VALUES(?, ?, ?)";
		Connection conx = null;
		PreparedStatement pdst = null;
		try {
			conx = ConnectionDB.createConnectionToMySQL();
			pdst = conx.prepareStatement(sql);
			pdst.setString(1, contato.getNome());
			pdst.setInt(2, contato.getIdade());
			pdst.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pdst.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// encerra conexão após executar a query.
			try {
				if (pdst != null)
					pdst.close();
				if (conx != null)
					conx.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// query SQL para remover dados - delete.
	public void removeById(int id) {
		String sql = "DELETE FROM CONTATOS WHERE ID = ?";
		Connection conx = null;
		PreparedStatement pdst = null;
		try {
			conx = ConnectionDB.createConnectionToMySQL();
			pdst = conx.prepareStatement(sql);
			pdst.setInt(1, id);
			pdst.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// encerra conexão após executar a query.
			try {
				if (pdst != null)
					pdst.close();
				if (conx != null)
					conx.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// query SQL para atualizar dados - update.
	public void update(Contato contato) {
		String sql = "UPDATE CONTATOS SET NOME = ?, IDADE = ?," 
							+ " DATACADASTRO = ? WHERE ID = ?";
		Connection conx = null;
		PreparedStatement pdst = null;
		try {
			conx = ConnectionDB.createConnectionToMySQL();
			pdst = conx.prepareStatement(sql);
			pdst.setString(1, contato.getNome());
			pdst.setInt(2, contato.getIdade());
			pdst.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pdst.setInt(4, contato.getId());
			pdst.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// encerra conexão após executar a query.
			try {
				if (pdst != null)
					pdst.close();
				if (conx != null)
					conx.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// query SQL para exibir dados - read.
	public List<Contato> getContatoModel() {
		String sql = "SELECT * FROM CONTATOS";
		List<Contato> contatos = new ArrayList<>();
		Connection conx = null;
		PreparedStatement pdst = null;
		ResultSet rset = null;
		try {
			conx = ConnectionDB.createConnectionToMySQL();
			pdst = conx.prepareStatement(sql);
			rset = pdst.executeQuery();
			while (rset.next()) {
				Contato c1 = new Contato();
				c1.setId(rset.getInt("ID"));
				c1.setNome(rset.getString("NOME"));
				c1.setIdade(rset.getInt("IDADE"));
				c1.setDataCadastro(rset.getDate("DATACADASTRO"));
				contatos.add(c1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// encerra conexão após executar a query.
			try {
				if (rset != null)
					rset.close();
				if (pdst != null)
					pdst.close();
				if (conx != null)
					conx.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return contatos;
	}


	public static void main(String[] args) {
		
		// teste validação de funcionamento do DAO.
		ContatoDAO dao = new ContatoDAO();
		for (Contato c1 : dao.getContatoModel())
			System.out.println(c1.getNome() + " -> " + c1.getIdade());
	}
}