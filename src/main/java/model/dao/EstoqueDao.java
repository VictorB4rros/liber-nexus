package model.dao;

import java.util.List;

import model.dto.EstoqueDTO;

public interface EstoqueDao {

	void insert(EstoqueDTO obj);
    void update(EstoqueDTO obj);
    void deleteById(Integer idBiblioteca, Integer idLivro);
    EstoqueDTO findById(Integer idBiblioteca, Integer idLivro);
    List<EstoqueDTO> findAll();
    List<EstoqueDTO> findByBiblioteca(Integer idBiblioteca);
    List<EstoqueDTO> findByLivro(Integer idLivro);
}
