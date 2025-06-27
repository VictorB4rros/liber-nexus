package model.dao;

import model.dto.EnderecoBibliotecaDTO;

public interface EnderecoBibliotecaDao {

    void insert(EnderecoBibliotecaDTO obj);
    void update(EnderecoBibliotecaDTO obj);
    void deleteById(Integer idBiblioteca);
    EnderecoBibliotecaDTO findById(Integer idBiblioteca);
}
