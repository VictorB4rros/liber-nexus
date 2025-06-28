package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idFuncionario;
	private Integer idBiblioteca;
	private Integer idSetor;
	private String nomeFuncionario;
	private String emailFuncionario;

	public Funcionario() {
	}

	public Funcionario(Integer idUsuario, String senha, Integer idFuncionario, String cpf, Integer idBiblioteca,
			Integer idSetor, String nomeFuncionario, String emailFuncionario) {
		super(idUsuario, cpf, senha);
		this.idFuncionario = idFuncionario;
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
		result = prime * result + Objects.hash(super.getCpfUsuario(), emailFuncionario, idFuncionario);
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
		return Objects.equals(super.getCpfUsuario(), other.getCpfUsuario()) && Objects.equals(emailFuncionario, other.emailFuncionario)
				&& Objects.equals(idFuncionario, other.idFuncionario);
	}
}
