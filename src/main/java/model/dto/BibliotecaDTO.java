package model.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class BibliotecaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String cnpjBiblioteca;
    private String nomeBiblioteca;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;

    public BibliotecaDTO() {
    }

    public BibliotecaDTO(Integer id, String cnpjBiblioteca, String nomeBiblioteca, LocalTime horaAbertura, LocalTime horaFechamento) {
        this.id = id;
        this.cnpjBiblioteca = cnpjBiblioteca;
        this.nomeBiblioteca = nomeBiblioteca;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpjBiblioteca() {
        return cnpjBiblioteca;
    }

    public void setCnpjBiblioteca(String cnpjBiblioteca) {
        this.cnpjBiblioteca = cnpjBiblioteca;
    }

    public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public void setNomeBiblioteca(String nomeBiblioteca) {
        this.nomeBiblioteca = nomeBiblioteca;
    }

    public LocalTime getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(LocalTime horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public LocalTime getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(LocalTime horaFechamento) {
        this.horaFechamento = horaFechamento;
    }
}