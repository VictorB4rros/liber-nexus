package model.dto;

import java.io.Serializable;

public class UsuarioAcessoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idUsuario;
    private Integer codAcesso;
    private String tipoAcesso;

    public UsuarioAcessoDTO() {
    }

    public UsuarioAcessoDTO(Integer idUsuario, Integer codAcesso, String tipoAcesso) {
        this.idUsuario = idUsuario;
        this.codAcesso = codAcesso;
        this.tipoAcesso = tipoAcesso;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getCodAcesso() {
        return codAcesso;
    }

    public void setCodAcesso(Integer codAcesso) {
        this.codAcesso = codAcesso;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(String tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}