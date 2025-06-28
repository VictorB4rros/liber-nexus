package model.dto;

import java.io.Serializable;
import java.sql.Date;

public class EmprestimoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idEmprestimo;
    private Integer idUsuario;
    private Integer idBiblioteca;
    private Integer idLivro;
    private Date dataReserva;
    private Date dataColeta;
    private Date dataPrevDevolucao;
    private Date dataDevolucao;

    private String nomeUsuario;
    private String nomeBiblioteca;
    private String nomeLivro;

    public EmprestimoDTO() {
    }

    public EmprestimoDTO(Integer idEmprestimo, Integer idUsuario, Integer idBiblioteca, Integer idLivro,
                         Date dataReserva, Date dataColeta, Date dataPrevDevolucao, Date dataDevolucao,
                         String nomeUsuario, String nomeBiblioteca, String nomeLivro) {
        this.idEmprestimo = idEmprestimo;
        this.idUsuario = idUsuario;
        this.idBiblioteca = idBiblioteca;
        this.idLivro = idLivro;
        this.dataReserva = dataReserva;
        this.dataColeta = dataColeta;
        this.dataPrevDevolucao = dataPrevDevolucao;
        this.dataDevolucao = dataDevolucao;
        this.nomeUsuario = nomeUsuario;
        this.nomeBiblioteca = nomeBiblioteca;
        this.nomeLivro = nomeLivro;
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public void setNomeBiblioteca(String nomeBiblioteca) {
        this.nomeBiblioteca = nomeBiblioteca;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
}