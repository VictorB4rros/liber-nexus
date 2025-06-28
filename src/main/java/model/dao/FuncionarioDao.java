package model.dao;

import java.util.List;

import model.dto.FuncionarioDTO;

public interface FuncionarioDao {

    void insert(FuncionarioDTO obj);
    void update(FuncionarioDTO obj);
    void deleteById(Integer idFuncionario);
    FuncionarioDTO findById(Integer idFuncionario);
    List<FuncionarioDTO> findAll(Integer idBiblioteca, Integer idSetor);
}