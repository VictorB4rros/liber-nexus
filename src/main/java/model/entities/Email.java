package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Email implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private Integer idBiblioteca;
	private Integer idSetor;

	public Email() {
	}

	public Email(String email, Integer idBiblioteca, Integer idSetor) {
		this.email = email;
		this.idBiblioteca = Objects.requireNonNull(idBiblioteca, "Id da biblioteca não pode ser nulo");
		this.idSetor = Objects.requireNonNull(idSetor, "Id do setor não pode ser nulo");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, idBiblioteca, idSetor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(email, other.email) && Objects.equals(idBiblioteca, other.idBiblioteca)
				&& Objects.equals(idSetor, other.idSetor);
	}

	@Override
	public String toString() {
		return email;
	}
}
