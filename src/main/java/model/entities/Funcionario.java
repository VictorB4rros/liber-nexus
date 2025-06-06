package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idFuncionario;
	private String cpf;
	private Integer idBiblioteca;
	private Integer idSetor;
	private String nomeFuncionario;
	private String emailFuncionario;

	public Funcionario() {
	}

	public Funcionario(String senha, String cpf, Integer idBiblioteca,
			Integer idSetor, String nomeFuncionario, String emailFuncionario) {
		super(senha);
		this.cpf = Objects.requireNonNull(cpf, "CPF não pode ser nulo");
		this.idBiblioteca = Objects.requireNonNull(idBiblioteca, "Id da biblioteca não pode ser nulo");
		this.idSetor = Objects.requireNonNull(idSetor, "Id do setor não pode ser nulo");
		this.nomeFuncionario = Objects.requireNonNull(nomeFuncionario, "Nome do funcionário não pode ser nulo");
		this.emailFuncionario = Objects.requireNonNull(emailFuncionario, "Email do funcionário não pode ser nulo");
	}

	public Funcionario(Integer idUsuario, String senha, Integer idFuncionario, String cpf, Integer idBiblioteca,
			Integer idSetor, String nomeFuncionario, String emailFuncionario) {
		super(idUsuario, senha);
		this.idFuncionario = idFuncionario;
		this.cpf = cpf;
		this.idBiblioteca = idBiblioteca;
		this.idSetor = idSetor;
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

	public Integer getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
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
		return "Funcionario [idFuncionario=" + idFuncionario + ", cpf=" + cpf + ", idBiblioteca=" + idBiblioteca
				+ ", idSetor=" + idSetor + ", nomeFuncionario=" + nomeFuncionario + ", emailFuncionario="
				+ emailFuncionario + "]";
	}
}
