package model.dao;

import model.dto.SetorDTO;

public interface SetorDao {

    void insert(SetorDTO obj);
    void update(SetorDTO obj);
    void deleteById(Integer id);
    SetorDTO findById(Integer id);
}