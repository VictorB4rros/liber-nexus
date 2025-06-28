package model.dao;

import java.util.List;

import model.dto.TelefoneDTO;

public interface TelefoneDao {

    void insert(TelefoneDTO obj);
    void update(TelefoneDTO obj);
    void deleteById(Integer idBiblioteca, Integer idSetor);
    TelefoneDTO findById(Integer idBiblioteca, Integer idSetor);
    List<TelefoneDTO> findAll(Integer idBiblioteca);
}