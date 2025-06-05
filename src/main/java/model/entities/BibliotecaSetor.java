package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class BibliotecaSetor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Biblioteca biblioteca;
	private Setor setor;
	private Telefone telefone;
	private Email email;
	
	public BibliotecaSetor() {
	}

	public BibliotecaSetor(Biblioteca biblioteca, Setor setor, Telefone telefone, Email email) {
		this.biblioteca = Objects.requireNonNull(biblioteca, "Biblioteca não pode ser nula");
		this.setor = Objects.requireNonNull(setor, "Setor não pode ser nulo");
		this.telefone = telefone;
		this.email = email;
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(biblioteca, email, setor, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibliotecaSetor other = (BibliotecaSetor) obj;
		return Objects.equals(biblioteca, other.biblioteca) && Objects.equals(email, other.email)
				&& Objects.equals(setor, other.setor) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "BibliotecaSetor [biblioteca=" + biblioteca.getNomeBiblioteca() + ", setor=" + setor.getDescricao() + ", telefone=" + telefone + ", email="
				+ email + "]";
	}
}
