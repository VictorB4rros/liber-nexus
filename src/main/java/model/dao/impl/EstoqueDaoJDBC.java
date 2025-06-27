package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.EstoqueDao;
import model.dto.EstoqueDTO;

public class EstoqueDaoJDBC implements EstoqueDao {


    private final Connection conn;

    public EstoqueDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(EstoqueDTO obj) {
        try (PreparedStatement st = conn.prepareStatement(
            "INSERT INTO TB_Estoque (id_biblioteca, id_livro, qtd_estoque, id_status) VALUES (?, ?, ?, ?)")) {

            st.setInt(1, obj.getIdBiblioteca());
            st.setInt(2, obj.getIdLivro());
            st.setInt(3, obj.getQuantidade());
            st.setInt(4, obj.getIdStatus());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(EstoqueDTO obj) {
        try (PreparedStatement st = conn.prepareStatement(
            "UPDATE TB_Estoque SET qtd_estoque = ?, id_status = ? WHERE id_biblioteca = ? AND id_livro = ?")) {

            st.setInt(1, obj.getQuantidade());
            st.setInt(2, obj.getIdStatus());
            st.setInt(3, obj.getIdBiblioteca());
            st.setInt(4, obj.getIdLivro());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idBiblioteca, Integer idLivro) {
        try (PreparedStatement st = conn.prepareStatement(
            "DELETE FROM TB_Estoque WHERE id_biblioteca = ? AND id_livro = ?")) {

            st.setInt(1, idBiblioteca);
            st.setInt(2, idLivro);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public EstoqueDTO findById(Integer idBiblioteca, Integer idLivro) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("""
                SELECT
                    b.nome_biblioteca AS biblioteca,
                    l.titulo_livro AS livro,
                    e.qtd_estoque AS quantidade,
                    s.descricao_status AS status
                FROM
                    TB_Estoque e
                JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
                JOIN TB_Livros l ON e.id_livro = l.id_livro
                JOIN TB_Status_Estoque s ON e.id_status = s.id_status
                WHERE
                    e.id_biblioteca = ? AND e.id_livro = ?
            """);

            st.setInt(1, idBiblioteca);
            st.setInt(2, idLivro);
            rs = st.executeQuery();

            if (rs.next()) {
                EstoqueDTO dto = new EstoqueDTO();
                dto.setNomeBiblioteca(rs.getString("biblioteca"));
                dto.setTituloLivro(rs.getString("livro"));
                dto.setQuantidade(rs.getInt("quantidade"));
                dto.setDescricaoStatus(rs.getString("status"));
                return dto;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar estoque por ID composto: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public List<EstoqueDTO> findAll() {
        try (PreparedStatement st = conn.prepareStatement(
            "SELECT e.*, b.nome_biblioteca, l.titulo_livro, s.descricao_status FROM TB_Estoque e " +
            "JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca " +
            "JOIN TB_Livros l ON e.id_livro = l.id_livro " +
            "JOIN TB_Status_Estoque s ON e.id_status = s.id_status")) {

            ResultSet rs = st.executeQuery();
            List<EstoqueDTO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateDTO(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<EstoqueDTO> findByBiblioteca(Integer idBiblioteca) {
        try (PreparedStatement st = conn.prepareStatement(
            "SELECT e.*, b.nome_biblioteca, l.titulo_livro, s.descricao_status FROM TB_Estoque e " +
            "JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca " +
            "JOIN TB_Livros l ON e.id_livro = l.id_livro " +
            "JOIN TB_Status_Estoque s ON e.id_status = s.id_status " +
            "WHERE e.id_biblioteca = ?")) {

            st.setInt(1, idBiblioteca);
            ResultSet rs = st.executeQuery();
            List<EstoqueDTO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateDTO(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<EstoqueDTO> findByLivro(Integer idLivro) {
        try (PreparedStatement st = conn.prepareStatement(
            "SELECT e.*, b.nome_biblioteca, l.titulo_livro, s.descricao_status FROM TB_Estoque e " +
            "JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca " +
            "JOIN TB_Livros l ON e.id_livro = l.id_livro " +
            "JOIN TB_Status_Estoque s ON e.id_status = s.id_status " +
            "WHERE e.id_livro = ?")) {

            st.setInt(1, idLivro);
            ResultSet rs = st.executeQuery();
            List<EstoqueDTO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateDTO(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private EstoqueDTO instantiateDTO(ResultSet rs) throws SQLException {
        EstoqueDTO dto = new EstoqueDTO();
        dto.setIdBiblioteca(rs.getInt("id_biblioteca"));
        dto.setIdLivro(rs.getInt("id_livro"));
        dto.setQuantidade(rs.getInt("qtd_estoque"));
        dto.setIdStatus(rs.getInt("id_status"));
        dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
        dto.setTituloLivro(rs.getString("titulo_livro"));
        dto.setDescricaoStatus(rs.getString("descricao_status"));
        return dto;
    }
}
