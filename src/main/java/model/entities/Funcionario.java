package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idFuncionario;
	private String cpf;
	private Biblioteca biblioteca;
	private Setor setor;
	private String nomeFuncionario;
	private String emailFuncionario;

	public Funcionario() {
	}

	public Funcionario(String senha, String cpf, Biblioteca biblioteca,
			Setor setor, String nomeFuncionario, String emailFuncionario) {
		super(senha);
		this.cpf = Objects.requireNonNull(cpf, "CPF não pode ser nulo");
		this.biblioteca = Objects.requireNonNull(biblioteca, "Biblioteca não pode ser nula");
		this.setor = Objects.requireNonNull(setor, "Setor não pode ser nulo");
		this.nomeFuncionario = Objects.requireNonNull(nomeFuncionario, "Nome do funcionário não pode ser nulo");
		this.emailFuncionario = Objects.requireNonNull(emailFuncionario, "Email do funcionário não pode ser nulo");
	}

	public Funcionario(Integer idUsuario, String senha, Integer idFuncionario, String cpf, Biblioteca biblioteca,
			Setor setor, String nomeFuncionario, String emailFuncionario) {
		super(idUsuario, senha);
		this.idFuncionario = idFuncionario;
		this.cpf = cpf;
		this.biblioteca = biblioteca;
		this.setor = setor;
		this.nomeFuncionario = nomeFuncionario;
		this.emailFuncionario = emailFuncionario;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getEmailFuncionario() {
		return emailFuncionario;
	}

	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cpf, emailFuncionario, idFuncionario);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(emailFuncionario, other.emailFuncionario)
				&& Objects.equals(idFuncionario, other.idFuncionario);
	}

	@Override
	public String toString() {
		return "Funcionario [idFuncionario=" + idFuncionario + ", cpf=" + cpf + ", biblioteca=" + biblioteca
				+ ", setor=" + setor + ", nomeFuncionario=" + nomeFuncionario + ", emailFuncionario=" + emailFuncionario
				+ "]";
	}
}
