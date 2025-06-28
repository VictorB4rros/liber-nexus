package model.dto;

import java.io.Serializable;

public class SetorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSetor;
    private String descricao;

    public SetorDTO() {
    }

    public SetorDTO(Integer idSetor, String descricao) {
        this.idSetor = idSetor;
        this.descricao = descricao;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}