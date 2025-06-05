package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Leitor extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String nomeLeitor;
	private String emailLeitor;
	
	public Leitor() {
	}
	
	public Leitor(String senha, String cpf, String nomeLeitor, String emailLeitor) {
		super(senha);
		this.cpf = Objects.requireNonNull(cpf, "CPF não pode ser nulo");
		this.nomeLeitor = Objects.requireNonNull(nomeLeitor, "Nome do leitor não pode ser nulo");
		this.emailLeitor = Objects.requireNonNull(emailLeitor, "Email do leitor não pode ser nulo");
	}

	public Leitor(Integer idUsuario, String senha, String cpf, String nomeLeitor, String emailLeitor) {
		super(idUsuario, senha);
		this.cpf = cpf;
		this.nomeLeitor = nomeLeitor;
		this.emailLeitor = emailLeitor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeLeitor() {
		return nomeLeitor;
	}

	public void setNomeLeitor(String nomeLeitor) {
		this.nomeLeitor = nomeLeitor;
	}

	public String getEmailLeitor() {
		return emailLeitor;
	}

	public void setEmailLeitor(String emailLeitor) {
		this.emailLeitor = emailLeitor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cpf, emailLeitor);
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
		Leitor other = (Leitor) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(emailLeitor, other.emailLeitor);
	}

	@Override
	public String toString() {
		return "Leitor [cpf=" + cpf + ", nomeLeitor=" + nomeLeitor + ", emailLeitor=" + emailLeitor + "]";
	}
}
