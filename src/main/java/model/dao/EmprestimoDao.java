package model.dao;

import java.util.List;

import model.dto.EmprestimoDTO;

public interface EmprestimoDao {

    void insert(EmprestimoDTO emprestimo);
    void update(EmprestimoDTO emprestimo);
    void deleteById(Integer idEmprestimo);
    EmprestimoDTO findById(Integer idEmprestimo);
    List<EmprestimoDTO> findAll(Integer idUsuario);
}