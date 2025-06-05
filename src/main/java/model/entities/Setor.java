package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idSetor;
	private String descricao;
	
	private final List<BibliotecaSetor> listaDeBibliotecas = new ArrayList<>();
	
	public Setor() {
	}
	
	public Setor(String descricao) {
		this.descricao = Objects.requireNonNull(descricao, "Decrição do setor não pode ser nula");
	}
	
	public Setor(Integer idSetor, String descricao) {
		this.idSetor = idSetor;
		this.descricao = descricao;
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

	public List<BibliotecaSetor> getListaDeBibliotecas() {
		return listaDeBibliotecas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSetor);
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
		return Objects.equals(idSetor, other.idSetor);
	}

	@Override
	public String toString() {
		return "Setor [idSetor=" + idSetor + ", descricao=" + descricao + ", listaDeBibliotecas=" + listaDeBibliotecas
				+ "]";
	}
	
	public void addBiblioteca(BibliotecaSetor bibliotecaSetor) {
		listaDeBibliotecas.add(bibliotecaSetor);
	}
}
