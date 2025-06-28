package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.EmailDao;
import model.dto.EmailDTO;

public class EmailDaoJDBC implements EmailDao {

    private final Connection conn;

    public EmailDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(EmailDTO obj) {
        PreparedStatement checkAssoc = null;
        PreparedStatement insertAssoc = null;
        PreparedStatement insertEmail = null;

        try {
            conn.setAutoCommit(false);

            
            checkAssoc = conn.prepareStatement("""
                SELECT 1 FROM TB_Biblioteca_Setor
                WHERE id_biblioteca = ? AND id_setor = ?
            """);
            checkAssoc.setInt(1, obj.getIdBiblioteca());
            checkAssoc.setInt(2, obj.getIdSetor());
            ResultSet rs = checkAssoc.executeQuery();

            if (!rs.next()) {
                
                insertAssoc = conn.prepareStatement("""
                    INSERT INTO TB_Biblioteca_Setor (id_biblioteca, id_setor)
                    VALUES (?, ?)
                """);
                insertAssoc.setInt(1, obj.getIdBiblioteca());
                insertAssoc.setInt(2, obj.getIdSetor());
                insertAssoc.executeUpdate();
            }

            
            insertEmail = conn.prepareStatement("""
                INSERT INTO TB_Email_Bibliotecas (id_biblioteca, id_setor, email_biblioteca)
                VALUES (?, ?, ?)
            """);
            insertEmail.setInt(1, obj.getIdBiblioteca());
            insertEmail.setInt(2, obj.getIdSetor());
            insertEmail.setString(3, obj.getEmail());
            insertEmail.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new DbException("Erro ao realizar rollback: " + ex.getMessage());
            }
            throw new DbException("Erro ao inserir email: " + e.getMessage());
        } finally {
            try {
                if (checkAssoc != null) checkAssoc.close();
                if (insertAssoc != null) insertAssoc.close();
                if (insertEmail != null) insertEmail.close();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(EmailDTO obj) {
        try (PreparedStatement st = conn.prepareStatement("""
            UPDATE TB_Email_Bibliotecas
            SET email_biblioteca = ?
            WHERE id_biblioteca = ? AND id_setor = ?
        """)) {
            st.setString(1, obj.getEmail());
            st.setInt(2, obj.getIdBiblioteca());
            st.setInt(3, obj.getIdSetor());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar email: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idBiblioteca, Integer idSetor) {
        try (PreparedStatement st = conn.prepareStatement("""
            DELETE FROM TB_Email_Bibliotecas
            WHERE id_biblioteca = ? AND id_setor = ?
        """)) {
            st.setInt(1, idBiblioteca);
            st.setInt(2, idSetor);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar email: " + e.getMessage());
        }
    }

    @Override
    public EmailDTO findById(Integer idBiblioteca, Integer idSetor) {
        try (PreparedStatement st = conn.prepareStatement("""
            SELECT e.email_biblioteca, b.nome_biblioteca, s.descricao_setor
            FROM TB_Email_Bibliotecas e
            JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
            JOIN TB_Setores s ON e.id_setor = s.id_setor
            WHERE e.id_biblioteca = ? AND e.id_setor = ?
        """)) {
            st.setInt(1, idBiblioteca);
            st.setInt(2, idSetor);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                EmailDTO dto = new EmailDTO();
                dto.setIdBiblioteca(idBiblioteca);
                dto.setIdSetor(idSetor);
                dto.setEmail(rs.getString("email_biblioteca"));
                dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
                dto.setNomeSetor(rs.getString("descricao_setor"));
                return dto;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar email: " + e.getMessage());
        }
    }

    @Override
    public List<EmailDTO> findAll(Integer idBiblioteca) {
        try (PreparedStatement st = conn.prepareStatement("""
            SELECT e.id_setor, e.email_biblioteca, b.nome_biblioteca, s.descricao_setor
            FROM TB_Email_Bibliotecas e
            JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
            JOIN TB_Setores s ON e.id_setor = s.id_setor
            WHERE e.id_biblioteca = ?
        """)) {
            st.setInt(1, idBiblioteca);
            ResultSet rs = st.executeQuery();

            List<EmailDTO> list = new ArrayList<>();
            while (rs.next()) {
                EmailDTO dto = new EmailDTO();
                dto.setIdBiblioteca(idBiblioteca);
                dto.setIdSetor(rs.getInt("id_setor"));
                dto.setEmail(rs.getString("email_biblioteca"));
                dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
                dto.setNomeSetor(rs.getString("descricao_setor"));
                list.add(dto);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar e-mails: " + e.getMessage());
        }
    }
}