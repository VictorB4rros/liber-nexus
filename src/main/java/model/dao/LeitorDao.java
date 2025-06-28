package model.dao;

import model.dto.LeitorDTO;

public interface LeitorDao {

    void insert(LeitorDTO leitor);
    void update(LeitorDTO leitor);
    void deleteById(Integer idUsuario);
    LeitorDTO findById(Integer idUsuario);
}