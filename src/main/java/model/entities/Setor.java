package model.entities;

import java.util.Objects;

public class Setor {

	private Integer idSetor;
	private String descricao;
	
	private Biblioteca biblioteca;
	private Telefone telefone;
	private Email email;
	
	public Setor() {
	}
	
	public Setor(Integer idSetor, String descricao, Biblioteca biblioteca, Telefone telefone, Email email) {
		this.idSetor = idSetor;
		this.descricao = descricao;
		this.biblioteca = biblioteca;
		this.telefone = telefone;
		this.email = email;
	}

	public Integer getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
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
		return Objects.hash(biblioteca, idSetor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		return Objects.equals(biblioteca, other.biblioteca) && Objects.equals(idSetor, other.idSetor);
	}

	@Override
	public String toString() {
		return "Setor [idSetor=" + idSetor + ", descricao=" + descricao + ", biblioteca=" + biblioteca + ", telefone="
				+ telefone + ", email=" + email + "]";
	}
}
