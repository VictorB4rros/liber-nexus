package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantidade;
	private Biblioteca biblioteca;
	private Livro livro;
	private StatusEstoque status;
	
	private final List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
	
	public Estoque() {
	}

	public Estoque(Integer quantidade, Biblioteca biblioteca, Livro livro, StatusEstoque status) {
		this.quantidade = Objects.requireNonNull(quantidade, "quantidade não pode ser nula");
		this.biblioteca = Objects.requireNonNull(biblioteca, "biblioteca não pode ser nula");
		this.livro = Objects.requireNonNull(livro, "livro não pode ser nulo");
		this.status = Objects.requireNonNull(status, "status não pode ser nulo");
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

	public List<Emprestimo> getListaDeEmprestimos() {
		return listaDeEmprestimos;
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
