package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idBiblioteca;
	private Integer idLivro;
	private Integer quantidade;
	private Integer idStatus;
	
	private Biblioteca biblioteca;
	private Livro livro;
	private StatusEstoque status;
	private final List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
	
	public Estoque() {
	}

	public Estoque(Integer idBiblioteca, Integer idLivro, Integer quantidade, Integer idStatus) {
		this.idBiblioteca = Objects.requireNonNull(idBiblioteca, "Id da biblioteca não pode ser nulo");
		this.idLivro = Objects.requireNonNull(idLivro, "Id do livro não pode ser nulo");
		this.quantidade = quantidade;
		this.idStatus = idStatus;
	}

	public Integer getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
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
		return Objects.hash(idBiblioteca, idLivro, idStatus, quantidade);
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
		return Objects.equals(idBiblioteca, other.idBiblioteca) && Objects.equals(idLivro, other.idLivro)
				&& Objects.equals(idStatus, other.idStatus) && Objects.equals(quantidade, other.quantidade);
	}

	@Override
	public String toString() {
		return "Estoque [idBiblioteca=" + idBiblioteca + ", idLivro=" + idLivro + ", quantidade=" + quantidade
				+ ", idStatus=" + idStatus + "]";
	}
	
	public void addEmprestimo(Emprestimo emprestimo) {
		listaDeEmprestimos.add(emprestimo);
	}
	
	public void removeEmprestimo(Emprestimo emprestimo) {
		listaDeEmprestimos.remove(emprestimo);
	}
}
