package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.TelefoneDao;
import model.dto.TelefoneDTO;

public class TelefoneDaoJDBC implements TelefoneDao {

    private final Connection conn;

    public TelefoneDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(TelefoneDTO obj) {
        PreparedStatement stSetor = null;
        PreparedStatement stTel = null;

        try {
            conn.setAutoCommit(false);

            
            String sqlCheck = "SELECT 1 FROM TB_Biblioteca_Setor WHERE id_biblioteca = ? AND id_setor = ?";
            try (PreparedStatement checkSt = conn.prepareStatement(sqlCheck)) {
                checkSt.setInt(1, obj.getIdBiblioteca());
                checkSt.setInt(2, obj.getIdSetor());
                ResultSet rs = checkSt.executeQuery();

                if (!rs.next()) {
                    
                    stSetor = conn.prepareStatement(
                        "INSERT INTO TB_Biblioteca_Setor (id_biblioteca, id_setor) VALUES (?, ?)"
                    );
                    stSetor.setInt(1, obj.getIdBiblioteca());
                    stSetor.setInt(2, obj.getIdSetor());
                    stSetor.executeUpdate();
                }
            }

            
            stTel = conn.prepareStatement(
                "INSERT INTO TB_Tel_Bibliotecas (id_biblioteca, id_setor, ddd_biblioteca, tel_biblioteca) " +
                "VALUES (?, ?, ?, ?)"
            );
            stTel.setInt(1, obj.getIdBiblioteca());
            stTel.setInt(2, obj.getIdSetor());
            stTel.setString(3, obj.getDdd());
            stTel.setString(4, obj.getTelefone());
            stTel.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                throw new DbException("Erro ao fazer rollback: " + rollbackEx.getMessage());
            }
            throw new DbException("Erro ao inserir telefone: " + e.getMessage());

        } finally {
            try {
                if (stSetor != null) stSetor.close();
                if (stTel != null) stTel.close();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DbException("Erro ao liberar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(TelefoneDTO obj) {
        try (PreparedStatement st = conn.prepareStatement(
            "UPDATE TB_Tel_Bibliotecas SET ddd_biblioteca = ?, tel_biblioteca = ? WHERE id_biblioteca = ? AND id_setor = ?"
        )) {
            st.setString(1, obj.getDdd());
            st.setString(2, obj.getTelefone());
            st.setInt(3, obj.getIdBiblioteca());
            st.setInt(4, obj.getIdSetor());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar telefone: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idBiblioteca, Integer idSetor) {
        try (PreparedStatement st = conn.prepareStatement(
            "DELETE FROM TB_Tel_Bibliotecas WHERE id_biblioteca = ? AND id_setor = ?"
        )) {
            st.setInt(1, idBiblioteca);
            st.setInt(2, idSetor);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar telefone: " + e.getMessage());
        }
    }

    @Override
    public TelefoneDTO findById(Integer idBiblioteca, Integer idSetor) {
        String sql = """
            SELECT t.id_biblioteca, t.id_setor, t.ddd_biblioteca, t.tel_biblioteca,
                   b.nome_biblioteca, s.descricao_setor
            FROM TB_Tel_Bibliotecas t
            JOIN TB_Bibliotecas b ON t.id_biblioteca = b.id_biblioteca
            JOIN TB_Setores s ON t.id_setor = s.id_setor
            WHERE t.id_biblioteca = ? AND t.id_setor = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idBiblioteca);
            st.setInt(2, idSetor);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new TelefoneDTO(
                        rs.getInt("id_biblioteca"),
                        rs.getInt("id_setor"),
                        rs.getString("ddd_biblioteca"),
                        rs.getString("tel_biblioteca"),
                        rs.getString("nome_biblioteca"),
                        rs.getString("descricao_setor")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar telefone: " + e.getMessage());
        }
    }

    @Override
    public List<TelefoneDTO> findAll(Integer idBiblioteca) {
        String sql = """
            SELECT t.id_biblioteca, t.id_setor, t.ddd_biblioteca, t.tel_biblioteca,
                   b.nome_biblioteca, s.descricao_setor
            FROM TB_Tel_Bibliotecas t
            JOIN TB_Bibliotecas b ON t.id_biblioteca = b.id_biblioteca
            JOIN TB_Setores s ON t.id_setor = s.id_setor
            WHERE t.id_biblioteca = ?
        """;

        List<TelefoneDTO> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idBiblioteca);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    TelefoneDTO dto = new TelefoneDTO(
                        rs.getInt("id_biblioteca"),
                        rs.getInt("id_setor"),
                        rs.getString("ddd_biblioteca"),
                        rs.getString("tel_biblioteca"),
                        rs.getString("nome_biblioteca"),
                        rs.getString("descricao_setor")
                    );
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar telefones por biblioteca: " + e.getMessage());
        }
        return list;
    }
}
