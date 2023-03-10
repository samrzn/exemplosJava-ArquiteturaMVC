package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.Contato;
import model.dao.ContatoDAO;

public class ContatoController {
	
	// conforme regras da arquitetura MVC, o View não pode acessar o DAO pois esse
	// contém as regras de banco, persistência e afins, o View acessa apenas o Controller
	// que comunica com o DAO na camada Model, mantendo a segurança dos dados e da aplicação.
	private ContatoDAO dao = new ContatoDAO();

	// abaixo apresentam-se regras de negócios contidas no Controller - ao 
	// receber entrada de dados da View o método DAO, protegido na camada Model e
	// privado para acesso apenas do Controller, repassa a requisição para o banco
	// e aguarda a resposta, após retorno Controller envia para renderização na View.
	public void inserirContato(Contato contato) throws Exception {
		if(contato.getIdade() < 0)
			throw new Exception("Idade não pode ser menor que 0.");
		if (contato.getNome().equals(""))
			throw new Exception("Campo nome não pode ficar vazio.");
		dao.save(contato);
	}
	
	public void excluirContato(Contato contato) throws Exception {
		if(contato.getIdade() < 18)
			throw new Exception("Seu usuário não tem permissão para excluir este cadastro.");
		dao.removeById(contato.getId());
	}
	
	public void excluirContato(int id) throws Exception {
		Contato contato = dao.getContatoById(id);
		if(contato == null)
			throw new Exception("ID inválido.");
		excluirContato(contato);
	}
	
	public void alterarContato(Contato contato) throws Exception {
		if(contato.getIdade() < 0)
			throw new Exception("Idade não pode ser menor que 0.");
		if (contato.getNome().equals(""))
			throw new Exception("Campo 'nome' não pode ficar vazio.");
		dao.update(contato);
	}
	
	public List<Contato> obterTodos() {
		return dao.getContatoModel();
	}
	
	public Contato obterById(int id) throws Exception {
		Contato contato = dao.getContatoById(id);
		if(contato == null)
			throw new Exception("ID inválido.");
		return contato;
	}
	
	
	public static void main(String[] args) {
		ContatoController ctrl = new ContatoController();
		Contato ct1 = new Contato();
		ct1.setIdade(-1);
		try {
			ctrl.inserirContato(ct1);
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
} 