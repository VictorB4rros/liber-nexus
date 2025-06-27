package model.dto;

import java.io.Serializable;

public class EnderecoBibliotecaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idBiblioteca;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private Integer numero;

    public EnderecoBibliotecaDTO() {
    }

    public EnderecoBibliotecaDTO(Integer idBiblioteca, String estado, String cidade, String cep,
                                  String bairro, String rua, Integer numero) {
        this.idBiblioteca = idBiblioteca;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public Integer getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}