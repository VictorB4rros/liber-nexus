package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Leitor extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeLeitor;
	private String emailLeitor;
	
	public Leitor() {
	}

	public Leitor(Integer idUsuario, String senha, String cpf, String nomeLeitor, String emailLeitor) {
		super(idUsuario, cpf, senha);
		this.nomeLeitor = nomeLeitor;
		this.emailLeitor = emailLeitor;
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
		result = prime * result + Objects.hash(super.getCpfUsuario(), emailLeitor);
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
		return Objects.equals(super.getCpfUsuario(), other.getCpfUsuario()) && Objects.equals(emailLeitor, other.emailLeitor);
	}
}
