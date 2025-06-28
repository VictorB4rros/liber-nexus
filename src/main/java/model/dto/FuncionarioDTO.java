package model.dto;

import java.io.Serializable;

public class FuncionarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idFuncionario;
    private Integer idUsuario;
    private String cpfUsuario;
    private String senha;
    private String nomeFuncionario;
    private String emailFuncionario;
    private Integer idBiblioteca;
    private Integer idSetor;
    
    private String nomeBiblioteca;
    private String nomeSetor;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Integer idFuncionario, Integer idUsuario, String cpfUsuario, String senha,
                          String nomeFuncionario, String emailFuncionario, Integer idBiblioteca, Integer idSetor,
                          String nomeBiblioteca, String nomeSetor) {
        this.idFuncionario = idFuncionario;
        this.idUsuario = idUsuario;
        this.cpfUsuario = cpfUsuario;
        this.senha = senha;
        this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.idBiblioteca = idBiblioteca;
        this.idSetor = idSetor;
        this.nomeBiblioteca = nomeBiblioteca;
        this.nomeSetor = nomeSetor;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public Integer getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public void setNomeBiblioteca(String nomeBiblioteca) {
        this.nomeBiblioteca = nomeBiblioteca;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
}