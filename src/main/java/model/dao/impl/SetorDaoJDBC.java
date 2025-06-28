package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.SetorDao;
import model.dto.SetorDTO;

public class SetorDaoJDBC implements SetorDao {

    private final Connection conn;

    public SetorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(SetorDTO obj) {
        String sql = "INSERT INTO TB_Setores (descricao_setor) VALUES (?)";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir setor: " + e.getMessage());
        }
    }

    @Override
    public void update(SetorDTO obj) {
        String sql = "UPDATE TB_Setores SET descricao_setor = ? WHERE id_setor = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getDescricao());
            st.setInt(2, obj.getIdSetor());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar setor: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM TB_Setores WHERE id_setor = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar setor: " + e.getMessage());
        }
    }

    @Override
    public SetorDTO findById(Integer id) {
        String sql = "SELECT * FROM TB_Setores WHERE id_setor = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    SetorDTO dto = new SetorDTO();
                    dto.setIdSetor(rs.getInt("id_setor"));
                    dto.setDescricao(rs.getString("descricao_setor"));
                    return dto;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar setor: " + e.getMessage());
        }
    }
}