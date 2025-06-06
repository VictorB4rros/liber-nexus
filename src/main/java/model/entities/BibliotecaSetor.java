package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class BibliotecaSetor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idBiblioteca;
	private Integer idSetor;
	
	private Biblioteca biblioteca;
	private Setor setor;
	private Telefone telefone;
	private Email email;
	
	public BibliotecaSetor() {
	}

	public BibliotecaSetor(Integer idBiblioteca, Integer idSetor) {
		this.idBiblioteca = Objects.requireNonNull(idBiblioteca, "Id da biblioteca não pode ser nulo");
		this.idSetor = Objects.requireNonNull(idSetor, "Id do setor não pode ser nulo");
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
		return Objects.hash(idBiblioteca, idSetor);
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
		return Objects.equals(idBiblioteca, other.idBiblioteca) && Objects.equals(idSetor, other.idSetor);
	}
}
