package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.EnderecoBibliotecaDao;
import model.dto.EnderecoBibliotecaDTO;

public class EnderecoBibliotecaDaoJDBC implements EnderecoBibliotecaDao {

    private final Connection conn;

    public EnderecoBibliotecaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(EnderecoBibliotecaDTO obj) {
        String sql = """
            INSERT INTO TB_End_Bibliotecas
            (id_biblioteca, estado_biblioteca, cidade_biblioteca, cep_biblioteca,
             bairro_biblioteca, rua_biblioteca, num_biblioteca)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, obj.getIdBiblioteca());
            st.setString(2, obj.getEstado());
            st.setString(3, obj.getCidade());
            st.setString(4, obj.getCep());
            st.setString(5, obj.getBairro());
            st.setString(6, obj.getRua());
            st.setInt(7, obj.getNumero());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir endereço: " + e.getMessage());
        }
    }

    @Override
    public void update(EnderecoBibliotecaDTO obj) {
        String sql = """
            UPDATE TB_End_Bibliotecas
            SET estado_biblioteca = ?, cidade_biblioteca = ?, cep_biblioteca = ?,
                bairro_biblioteca = ?, rua_biblioteca = ?, num_biblioteca = ?
            WHERE id_biblioteca = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getEstado());
            st.setString(2, obj.getCidade());
            st.setString(3, obj.getCep());
            st.setString(4, obj.getBairro());
            st.setString(5, obj.getRua());
            st.setInt(6, obj.getNumero());
            st.setInt(7, obj.getIdBiblioteca());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar endereço: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idBiblioteca) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM TB_End_Bibliotecas WHERE id_biblioteca = ?")) {
            st.setInt(1, idBiblioteca);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar endereço: " + e.getMessage());
        }
    }

    @Override
    public EnderecoBibliotecaDTO findById(Integer idBiblioteca) {
        String sql = """
            SELECT * FROM TB_End_Bibliotecas WHERE id_biblioteca = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idBiblioteca);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                EnderecoBibliotecaDTO dto = new EnderecoBibliotecaDTO();
                dto.setIdBiblioteca(rs.getInt("id_biblioteca"));
                dto.setEstado(rs.getString("estado_biblioteca"));
                dto.setCidade(rs.getString("cidade_biblioteca"));
                dto.setCep(rs.getString("cep_biblioteca"));
                dto.setBairro(rs.getString("bairro_biblioteca"));
                dto.setRua(rs.getString("rua_biblioteca"));
                dto.setNumero(rs.getInt("num_biblioteca"));
                return dto;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar endereço: " + e.getMessage());
        }
    }
}
