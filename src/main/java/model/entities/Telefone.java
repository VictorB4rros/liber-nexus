package model.entities;

import java.util.Objects;

public class Telefone {

	private String ddd;
	private String telefone;
	
	public Telefone() {
	}

	public Telefone(String ddd, String telefone) {
		this.ddd = ddd;
		this.telefone = telefone;
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
		return "Telefone [ddd=" + ddd + ", telefone=" + telefone + "]";
	}
}
