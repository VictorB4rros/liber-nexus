package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String cpfUsuario;
	private String senha;
	
	private final List<UsuarioAcesso> listaDeAcessos = new ArrayList<>();
	private final List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
	
	public Usuario() {
	}
	
	public Usuario(String senha) {
		this.senha = Objects.requireNonNull(senha, "Senha não pode ser nula");
	}

	public Usuario(Integer idUsuario, String cpfUsuario, String senha) {
		this.idUsuario = idUsuario;
		this.cpfUsuario = cpfUsuario;
		this.senha = senha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public List<UsuarioAcesso> getListaDeAcessos() {
		return listaDeAcessos;
	}

	public List<Emprestimo> getListaDeEmprestimos() {
		return listaDeEmprestimos;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpfUsuario, idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpfUsuario, other.cpfUsuario) && Objects.equals(idUsuario, other.idUsuario);
	}

	public void addAcesso(UsuarioAcesso usuarioAcesso) {
		listaDeAcessos.add(usuarioAcesso);
	}
	
	public void addEmprestimo(Emprestimo emprestimo) {
		listaDeEmprestimos.add(emprestimo);
	}
}
