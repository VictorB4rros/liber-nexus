package model.dto;

import java.io.Serializable;

public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idBiblioteca;
    private Integer idSetor;
    private String email;

    private String nomeBiblioteca;
    private String nomeSetor;

    public EmailDTO() {
    }

    public EmailDTO(Integer idBiblioteca, Integer idSetor, String email, String nomeBiblioteca, String nomeSetor) {
        this.idBiblioteca = idBiblioteca;
        this.idSetor = idSetor;
        this.email = email;
        this.nomeBiblioteca = nomeBiblioteca;
        this.nomeSetor = nomeSetor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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