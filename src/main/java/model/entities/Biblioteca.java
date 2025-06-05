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
	private final List<BibliotecaSetor> listaDeSetores = new ArrayList<>();
	private final List<Estoque> livrosEmEstoque = new ArrayList<>();

	public Biblioteca() {
	}

	public Biblioteca(String cnpjBiblioteca, String nomeBiblioteca, Time horaAbertura, Time horaFechamento,
			EnderecoBiblioteca endereco) {
		this.cnpjBiblioteca = Objects.requireNonNull(cnpjBiblioteca, "CNPJ da biblioteca não pode ser nulo");
		this.nomeBiblioteca = Objects.requireNonNull(nomeBiblioteca, "Nome da biblioteca não pode ser nulo");
		this.horaAbertura = Objects.requireNonNull(horaAbertura, "Hora de abertura não pode ser nula");
		this.horaFechamento = Objects.requireNonNull(horaFechamento, "Hora de fechamento não pode ser nula");
		this.endereco = Objects.requireNonNull(endereco, "Endereço da biblioteca não pode ser nulo");

		if (horaAbertura.after(horaFechamento)) {
			throw new IllegalArgumentException("Hora de abertura não pode ser após a hora de fechamento");
		}
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
		Objects.requireNonNull(horaAbertura, "Hora de abertura não pode ser nula");
		if (this.horaFechamento != null && horaAbertura.after(this.horaFechamento)) {
			throw new IllegalArgumentException("Hora de abertura não pode ser após a hora de fechamento");
		}
		this.horaAbertura = horaAbertura;
	}

	public Time getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(Time horaFechamento) {
		Objects.requireNonNull(horaFechamento, "Hora de fechamento não pode ser nula");
		if (this.horaAbertura != null && this.horaAbertura.after(horaFechamento)) {
			throw new IllegalArgumentException("Hora de fechamento não pode ser antes da hora de abertura");
		}
		this.horaFechamento = horaFechamento;
	}

	public EnderecoBiblioteca getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoBiblioteca endereco) {
		this.endereco = Objects.requireNonNull(endereco, "Endereço da biblioteca não pode ser nulo");
	}

	public List<BibliotecaSetor> getListaDeSetores() {
		return listaDeSetores;
	}

	public List<Estoque> getLivrosEmEstoque() {
		return livrosEmEstoque;
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
	    return "Biblioteca [id = " + idBiblioteca +
	            ", CNPJ = " + cnpjBiblioteca +
	            ", Nome = " + nomeBiblioteca +
	            ", Abertura = " + horaAbertura +
	            ", Fechamento = " + horaFechamento +
	            ", Endereço = " + endereco + 
	            ", Setores = " + listaDeSetores.size() + 
	            ", Livros em estoque = " + livrosEmEstoque.size() + "]";
	}

	public void addBibliotecaSetor(BibliotecaSetor bibliotecaSetor) {
		listaDeSetores.add(bibliotecaSetor);
	}
	
	public void removeBibliotecaSetor(BibliotecaSetor bibliotecaSetor) {
		listaDeSetores.remove(bibliotecaSetor);
	}

	public void addEstoque(Estoque estoque) {
		livrosEmEstoque.add(estoque);
	}
	
	public void removeEstoque(Estoque estoque) {
		livrosEmEstoque.remove(estoque);
	}
}
