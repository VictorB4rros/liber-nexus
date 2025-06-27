package model.dao;

import java.util.List;

import model.dto.BibliotecaDTO;

public interface BibliotecaDao {

	void insert(BibliotecaDTO obj);
	void update(BibliotecaDTO obj);
	void deleteById(Integer id);
	BibliotecaDTO findById(Integer id);
	List<BibliotecaDTO> findAll();
}
