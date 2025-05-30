package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

	private Integer idUsuario;
	private String cpfUsuario;
	private String nomeUsuario;
	private String emailUsuario;
	
	List<Acesso> listaDeAcessos = new ArrayList<>();
	List<Emprestimo> listaDeEmprestimos = new ArrayList<>();
	
	public Usuario() {
	}

	public Usuario(Integer idUsuario, String cpfUsuario, String nomeUsuario, String emailUsuario) {
		this.idUsuario = idUsuario;
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public List<Acesso> getListaDeAcessos() {
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

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", cpfUsuario=" + cpfUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", emailUsuario=" + emailUsuario + "]";
	}
	
	public void addAcesso(Acesso acesso) {
		if (!listaDeAcessos.contains(acesso)) {
			listaDeAcessos.add(acesso);
			acesso.getListaDeUsuarios().add(this);
		}
	}
	
	public void addEmprestimo(Emprestimo emprestimo) {
		listaDeEmprestimos.add(emprestimo);
	}
}
