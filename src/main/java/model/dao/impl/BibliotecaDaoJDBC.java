package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.BibliotecaDao;
import model.dto.BibliotecaDTO;

public class BibliotecaDaoJDBC implements BibliotecaDao {

    private final Connection conn;

    public BibliotecaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(BibliotecaDTO obj) {
        try (PreparedStatement st = conn.prepareStatement(
            "INSERT INTO TB_Bibliotecas (cnpj_biblioteca, nome_biblioteca, hora_abertura, hora_fechamento) " +
            "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, obj.getCnpjBiblioteca());
            st.setString(2, obj.getNomeBiblioteca());
            st.setTime(3, Time.valueOf(obj.getHoraAbertura()));
            st.setTime(4, Time.valueOf(obj.getHoraFechamento()));

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setId(rs.getInt(1));
                    }
                }
            } else {
                throw new DbException("Erro inesperado: nenhuma linha inserida.");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao inserir biblioteca: " + e.getMessage());
        }
    }

    @Override
    public void update(BibliotecaDTO obj) {
        try (PreparedStatement st = conn.prepareStatement(
            "UPDATE TB_Bibliotecas SET cnpj_biblioteca = ?, nome_biblioteca = ?, hora_abertura = ?, hora_fechamento = ? " +
            "WHERE id_biblioteca = ?")) {

            st.setString(1, obj.getCnpjBiblioteca());
            st.setString(2, obj.getNomeBiblioteca());
            st.setTime(3, Time.valueOf(obj.getHoraAbertura()));
            st.setTime(4, Time.valueOf(obj.getHoraFechamento()));
            st.setInt(5, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar biblioteca: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
            "DELETE FROM TB_Bibliotecas WHERE id_biblioteca = ?")) {

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao excluir biblioteca: " + e.getMessage());
        }
    }

    @Override
    public BibliotecaDTO findById(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM TB_Bibliotecas WHERE id_biblioteca = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return instantiateBiblioteca(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar biblioteca por ID: " + e.getMessage());
        }
    }

    @Override
    public List<BibliotecaDTO> findAll() {
        try (PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM TB_Bibliotecas ORDER BY nome_biblioteca")) {

            ResultSet rs = st.executeQuery();
            List<BibliotecaDTO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateBiblioteca(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new DbException("Erro ao listar bibliotecas: " + e.getMessage());
        }
    }

    private BibliotecaDTO instantiateBiblioteca(ResultSet rs) throws SQLException {
        BibliotecaDTO dto = new BibliotecaDTO();
        dto.setId(rs.getInt("id_biblioteca"));
        dto.setCnpjBiblioteca(rs.getString("cnpj_biblioteca"));
        dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
        dto.setHoraAbertura(rs.getTime("hora_abertura").toLocalTime());
        dto.setHoraFechamento(rs.getTime("hora_fechamento").toLocalTime());
        return dto;
    }
}