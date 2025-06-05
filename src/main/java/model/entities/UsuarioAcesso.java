package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioAcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Acesso acesso;
	
	public UsuarioAcesso() {
	}

	public UsuarioAcesso(Usuario usuario, Acesso acesso) {
		this.usuario = usuario;
		this.acesso = acesso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acesso, usuario);
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
		return Objects.equals(acesso, other.acesso) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "UsuarioAcesso [usuario=" + usuario + ", acesso=" + acesso + "]";
	}
}
