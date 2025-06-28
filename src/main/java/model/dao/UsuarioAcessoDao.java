package model.dao;

import model.dto.UsuarioAcessoDTO;

public interface UsuarioAcessoDao {

    void insert(UsuarioAcessoDTO acesso);
    void update(UsuarioAcessoDTO acesso);
    void delete(Integer idUsuario, Integer codAcesso);
    UsuarioAcessoDTO findById(Integer idUsuario, Integer codAcesso);
}