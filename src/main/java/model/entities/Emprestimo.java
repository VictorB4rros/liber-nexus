package model.entities;

import java.sql.Date;
import java.util.Objects;

public class Emprestimo {

	private Integer idEmprestimo;
	private Usuario usuario;
	private Biblioteca biblioteca;
	private Estoque estoque;
	private Date dataReserva;
	private Date dataColeta;
	private Date dataPrevDevolucao;
	private Date dataDevolucao;
	
	public Emprestimo() {
	}

	public Emprestimo(Integer idEmprestimo, Usuario usuario, Biblioteca biblioteca, Estoque estoque, Date dataReserva,
			Date dataColeta, Date dataPrevDevolucao, Date dataDevolucao) {
		this.idEmprestimo = idEmprestimo;
		this.usuario = usuario;
		this.biblioteca = biblioteca;
		this.estoque = estoque;
		this.dataReserva = dataReserva;
		this.dataColeta = dataColeta;
		this.dataPrevDevolucao = dataPrevDevolucao;
		this.dataDevolucao = dataDevolucao;
	}

	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Date getDataPrevDevolucao() {
		return dataPrevDevolucao;
	}

	public void setDataPrevDevolucao(Date dataPrevDevolucao) {
		this.dataPrevDevolucao = dataPrevDevolucao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(biblioteca, idEmprestimo, estoque, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(biblioteca, other.biblioteca) && Objects.equals(idEmprestimo, other.idEmprestimo)
				&& Objects.equals(estoque, other.estoque) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Emprestimo [idEmprestimo=" + idEmprestimo + ", usuario=" + usuario + ", biblioteca=" + biblioteca
				+ ", estoque=" + estoque + ", dataReserva=" + dataReserva + ", dataColeta=" + dataColeta
				+ ", dataPrevDevolucao=" + dataPrevDevolucao + ", dataDevolucao=" + dataDevolucao + "]";
	}
}
