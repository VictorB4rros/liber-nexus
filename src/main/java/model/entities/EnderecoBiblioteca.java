package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class EnderecoBiblioteca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String estado;
	private String cidade;
	private String cep;
	private String bairro;
	private String rua;
	private Integer numero;
	
	private Biblioteca biblioteca;
	
	public EnderecoBiblioteca() {
	}

	public EnderecoBiblioteca(String estado, String cidade, String cep, String bairro, String rua, Integer numero, Biblioteca biblioteca) {
		this.estado = Objects.requireNonNull(estado, "estado não pode ser nulo");
		this.cidade = Objects.requireNonNull(cidade, "cidade não pode ser nula");
		this.cep = Objects.requireNonNull(cep, "cep não pode ser nulo");
		this.bairro = Objects.requireNonNull(bairro, "bairro não pode ser nulo");
		this.rua = Objects.requireNonNull(rua, "rua não pode ser nula");
		this.numero = Objects.requireNonNull(numero, "numero não pode ser nulo");
		this.biblioteca = Objects.requireNonNull(biblioteca, "biblioteca não pode ser nula");
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoBiblioteca other = (EnderecoBiblioteca) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "estado=" + estado + ", cidade=" + cidade + ", cep=" + cep + ", bairro=" + bairro
				+ ", rua=" + rua + ", numero=" + numero + "]";
	}
}
