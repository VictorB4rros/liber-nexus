package model.dao;

import java.util.List;

import model.dto.EmailDTO;

public interface EmailDao {

    void insert(EmailDTO obj);
    void update(EmailDTO obj);
    void deleteById(Integer idBiblioteca, Integer idSetor);
    EmailDTO findById(Integer idBiblioteca, Integer idSetor);
    List<EmailDTO> findAll(Integer idBiblioteca);
}