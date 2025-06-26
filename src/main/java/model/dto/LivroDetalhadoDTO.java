package model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LivroDetalhadoDTO {
    private Integer idLivro;
    private String tituloLivro;
    private String autorLivro;
    private Date dataPublicacao;
    private String genero;
    private String idioma;

    
    public static class BibliotecaInfo {
        private String nomeBiblioteca;
        private int quantidade;
        private String status;

        public BibliotecaInfo(String nomeBiblioteca, int quantidade, String status) {
            this.nomeBiblioteca = nomeBiblioteca;
            this.quantidade = quantidade;
            this.status = status;
        }

        public String getNomeBiblioteca() {
            return nomeBiblioteca;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public String getStatus() {
            return status;
        }
    }

    private List<BibliotecaInfo> bibliotecas = new ArrayList<>();

    public void addBiblioteca(String nomeBiblioteca, int quantidade, String status) {
        bibliotecas.add(new BibliotecaInfo(nomeBiblioteca, quantidade, status));
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<BibliotecaInfo> getBibliotecas() {
        return bibliotecas;
    }
}