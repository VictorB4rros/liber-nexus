package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idEmprestimo;
	private Integer idUsuario;
	private Integer idBiblioteca;
	private Integer idLivro;
	private Date dataReserva;
	private Date dataColeta;
	private Date dataPrevDevolucao;
	private Date dataDevolucao;

	private Usuario usuario;
	private Estoque estoque;

	public Emprestimo() {
	}

	public Emprestimo(Integer idUsuario, Integer idBiblioteca, Integer idLivro, Date dataReserva, Date dataColeta,
			Date dataPrevDevolucao, Date dataDevolucao) {
		this.idUsuario = Objects.requireNonNull(idUsuario, "Id do usuário não pode ser nulo");
		this.idBiblioteca = Objects.requireNonNull(idBiblioteca, "Id da biblioteca não pode ser nulo");
		this.idLivro = Objects.requireNonNull(idLivro, "Id do livro não pode ser nulo");
		this.dataReserva = dataReserva;
		this.dataColeta = dataColeta;
		this.dataPrevDevolucao = dataPrevDevolucao;
		this.dataDevolucao = dataDevolucao;
	}

	public Emprestimo(Integer idEmprestimo, Integer idUsuario, Integer idBiblioteca, Integer idLivro, Date dataReserva,
			Date dataColeta, Date dataPrevDevolucao, Date dataDevolucao) {
		this.idEmprestimo = idEmprestimo;
		this.idUsuario = idUsuario;
		this.idBiblioteca = idBiblioteca;
		this.idLivro = idLivro;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEmprestimo);
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
		return Objects.equals(idEmprestimo, other.idEmprestimo);
	}
}
