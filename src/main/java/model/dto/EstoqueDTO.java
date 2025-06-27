package model.dto;

import java.io.Serializable;

public class EstoqueDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer idBiblioteca;
    private Integer idLivro;
    private Integer quantidade;
    private Integer idStatus;
    private String nomeBiblioteca;
    private String tituloLivro;
    private String descricaoStatus;

    public EstoqueDTO() {
    }

	public EstoqueDTO(Integer idBiblioteca, Integer idLivro, Integer quantidade, Integer idStatus,
			String nomeBiblioteca, String tituloLivro, String descricaoStatus) {
		this.idBiblioteca = idBiblioteca;
		this.idLivro = idLivro;
		this.quantidade = quantidade;
		this.idStatus = idStatus;
		this.nomeBiblioteca = nomeBiblioteca;
		this.tituloLivro = tituloLivro;
		this.descricaoStatus = descricaoStatus;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public void setNomeBiblioteca(String nomeBiblioteca) {
        this.nomeBiblioteca = nomeBiblioteca;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}
}
