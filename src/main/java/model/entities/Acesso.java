package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Acesso {

	private Integer codAcesso;
	private String tipoAcesso;
	
	List<Usuario> listaDeUsuarios = new ArrayList<>();
	
	public Acesso() {
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

	public List<Usuario> getListaDeUsuarios() {
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
	
	public void addUsuario(Usuario usuario) {
		if (!listaDeUsuarios.contains(usuario)) {
			listaDeUsuarios.add(usuario);
			usuario.getListaDeAcessos().add(this);
		}
	}
}
