package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idBiblioteca;
	private Integer idSetor;
	private String ddd;
	private String telefone;
	
	public Telefone() {
	}

	public Telefone(Integer idBiblioteca, Integer idSetor, String ddd, String telefone) {
		this.idBiblioteca = Objects.requireNonNull(idBiblioteca, "Id da biblioteca não pode ser nulo");
		this.idSetor = Objects.requireNonNull(idSetor, "Id do setor não pode ser nulo");
		this.ddd = ddd;
		this.telefone = telefone;
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

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ddd, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(ddd, other.ddd) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return ddd + " " + telefone;
	}
}
