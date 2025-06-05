package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Acesso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codAcesso;
	private String tipoAcesso;
	
	private final List<UsuarioAcesso> listaDeUsuarios = new ArrayList<>();
	
	public Acesso() {
	}

	public Acesso(String tipoAcesso) {
		this.tipoAcesso = Objects.requireNonNull(tipoAcesso, "o tipo de acesso não pode ser nulo");
	}

	public Acesso(Integer codAcesso, String tipoAcesso) {
		this.codAcesso = codAcesso;
		this.tipoAcesso = tipoAcesso;
	}

	public Integer getCodAcesso() {
		return codAcesso;
	}

	public void setCodAcesso(Integer codAcesso) {
		this.codAcesso = codAcesso;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public List<UsuarioAcesso> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codAcesso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		return Objects.equals(codAcesso, other.codAcesso);
	}

	@Override
	public String toString() {
		return "Acesso [codAcesso=" + codAcesso + ", tipoAcesso=" + tipoAcesso + "]";
	}
	
	public void addUsuarioAcesso(UsuarioAcesso usuarioAcesso) {
		listaDeUsuarios.add(usuarioAcesso);
	}
	
	public void removeUsuarioAcesso(UsuarioAcesso usuarioAcesso) {
		listaDeUsuarios.remove(usuarioAcesso);
	}
}
