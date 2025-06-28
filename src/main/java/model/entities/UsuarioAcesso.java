package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioAcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private Integer codAcesso;
	
	public UsuarioAcesso() {
	}

	public UsuarioAcesso(Integer idUsuario, Integer codAcesso) {
		this.idUsuario = idUsuario;
		this.codAcesso = codAcesso;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getCodAcesso() {
		return codAcesso;
	}

	public void setCodAcesso(Integer codAcesso) {
		this.codAcesso = codAcesso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codAcesso, idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioAcesso other = (UsuarioAcesso) obj;
		return Objects.equals(codAcesso, other.codAcesso) && Objects.equals(idUsuario, other.idUsuario);
	}
}
