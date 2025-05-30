package model.entities;

import java.util.Objects;

public class Estoque {

	private Integer quantidade;
	
	private Biblioteca biblioteca;
	private Livro livro;
	private StatusEstoque status;
	
	public Estoque() {
	}

	public Estoque(Integer quantidade, Biblioteca biblioteca, Livro livro, StatusEstoque status) {
		this.quantidade = quantidade;
		this.biblioteca = biblioteca;
		this.livro = livro;
		this.status = status;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public StatusEstoque getStatus() {
		return status;
	}

	public void setStatus(StatusEstoque status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(biblioteca, livro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estoque other = (Estoque) obj;
		return Objects.equals(biblioteca, other.biblioteca) && Objects.equals(livro, other.livro);
	}

	@Override
	public String toString() {
		return "Estoque [quantidade=" + quantidade + ", biblioteca=" + biblioteca + ", livro=" + livro + ", status="
				+ status + "]";
	}
}
