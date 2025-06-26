package model.dao;

import java.util.List;

import model.dto.LivroDetalhadoDTO;
import model.entities.Livro;

public interface LivroDao {

	void insert(Livro obj);
	void update(Livro obj);
	void deleteById(Integer id);
	LivroDetalhadoDTO findById(Integer id);
	List<LivroDetalhadoDTO> findAll();
	List<Livro> findByGenero(String genero);
}
