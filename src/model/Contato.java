package model;

import java.util.Date;

public class Contato {

	private int id = 0;
	private String nome = "";
	private int idade = 0;
	private Date dataCadastro;

	// método toString() sobrescrito para retornar os dados contidos no objeto
	// quando utilizado ao invés de retornar a posição dos dados na memória.
	@Override
	public String toString() {
		return nome + " : " + idade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}