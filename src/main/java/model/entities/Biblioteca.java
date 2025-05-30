package model.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Biblioteca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idBiblioteca;
	private String cnpjBiblioteca;
	private String nomeBiblioteca;
	private Time horaAbertura;
	private Time horaFechamento;
	
	private EnderecoBiblioteca endereco;
	private List<Setor> listaSetores = new ArrayList<>();
	private List<Estoque> livrosEmEstoque = new ArrayList<>();
	private List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
	
	public Biblioteca() {
	}

	public Biblioteca(Integer idBiblioteca, String cnpjBiblioteca, String nomeBiblioteca, Time horaAbertura,
			Time horaFechamento, EnderecoBiblioteca endereco) {
		this.idBiblioteca = idBiblioteca;
		this.cnpjBiblioteca = cnpjBiblioteca;
		this.nomeBiblioteca = nomeBiblioteca;
		this.horaAbertura = horaAbertura;
		this.horaFechamento = horaFechamento;
		this.endereco = endereco;
	}

	public Integer getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getCnpjBiblioteca() {
		return cnpjBiblioteca;
	}

	public void setCnpjBiblioteca(String cnpjBiblioteca) {
		this.cnpjBiblioteca = cnpjBiblioteca;
	}

	public String getNomeBiblioteca() {
		return nomeBiblioteca;
	}

	public void setNomeBiblioteca(String nomeBiblioteca) {
		this.nomeBiblioteca = nomeBiblioteca;
	}

	public Time getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(Time horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public Time getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(Time horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public EnderecoBiblioteca getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoBiblioteca endereco) {
		this.endereco = endereco;
	}

	public List<Setor> getListaSetores() {
		return listaSetores;
	}

	public List<Estoque> getLivrosEmEstoque() {
		return livrosEmEstoque;
	}

	public List<Emprestimo> getListaDeEmprestimos() {
		return listaDeEmprestimos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpjBiblioteca, idBiblioteca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biblioteca other = (Biblioteca) obj;
		return Objects.equals(cnpjBiblioteca, other.cnpjBiblioteca) && Objects.equals(idBiblioteca, other.idBiblioteca);
	}

	@Override
	public String toString() {
		return "Biblioteca [idBiblioteca=" + idBiblioteca + ", cnpjBiblioteca=" + cnpjBiblioteca + ", nomeBiblioteca="
				+ nomeBiblioteca + "]";
	}
	
	public void addSetor(Setor setor) {
		listaSetores.add(setor);
	}
	
	public void addEstoque(Estoque estoque) {
		livrosEmEstoque.add(estoque);
	}
	
	public void addEmprestimo(Emprestimo emprestimo) {
		listaDeEmprestimos.add(emprestimo);
	}
}
