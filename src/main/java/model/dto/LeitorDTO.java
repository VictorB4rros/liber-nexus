package model.dto;

import java.io.Serializable;

public class LeitorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idUsuario;
    private String cpfUsuario;
    private String senha;
    private String nomeLeitor;
    private String emailLeitor;

    public LeitorDTO() {
    }

    public LeitorDTO(Integer idUsuario, String cpfUsuario, String senha,
                     String nomeLeitor, String emailLeitor) {
        this.idUsuario = idUsuario;
        this.cpfUsuario = cpfUsuario;
        this.senha = senha;
        this.nomeLeitor = nomeLeitor;
        this.emailLeitor = emailLeitor;
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

    public String getNomeLeitor() {
        return nomeLeitor;
    }

    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }

    public String getEmailLeitor() {
        return emailLeitor;
    }

    public void setEmailLeitor(String emailLeitor) {
        this.emailLeitor = emailLeitor;
    }
}