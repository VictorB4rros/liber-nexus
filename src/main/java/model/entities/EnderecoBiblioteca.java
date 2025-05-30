package model.entities;

import java.util.Objects;

public class EnderecoBiblioteca {

	private String estado;
	private String cidade;
	private String cep;
	private String bairro;
	private String rua;
	private Integer numero;
	
	public EnderecoBiblioteca() {
	}

	public EnderecoBiblioteca(String estado, String cidade, String cep, String bairro, String rua, Integer numero) {
		this.estado = estado;
		this.cidade = cidade;
		this.cep = cep;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
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
		return "EnderecoBiblioteca [estado=" + estado + ", cidade=" + cidade + ", cep=" + cep + ", bairro=" + bairro
				+ ", rua=" + rua + ", numero=" + numero + "]";
	}
}
