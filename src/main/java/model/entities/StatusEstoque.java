package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class StatusEstoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idStatusEstoque;
	private String descricao;
	
	public StatusEstoque() {
	}
	
	public StatusEstoque(String descricao) {
		this.descricao = descricao;
	}

	public StatusEstoque(Integer idStatusEstoque, String descricao) {
		this.idStatusEstoque = idStatusEstoque;
		this.descricao = descricao;
	}

	public Integer getIdStatusEstoque() {
		return idStatusEstoque;
	}

	public void setIdStatusEstoque(Integer idStatusEstoque) {
		this.idStatusEstoque = idStatusEstoque;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idStatusEstoque);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusEstoque other = (StatusEstoque) obj;
		return Objects.equals(idStatusEstoque, other.idStatusEstoque);
	}

	@Override
	public String toString() {
		return "StatusEstoque [idStatusEstoque=" + idStatusEstoque + ", descricao=" + descricao + "]";
	}
}
