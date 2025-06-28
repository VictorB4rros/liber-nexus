package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbException;
import model.dao.LeitorDao;
import model.dto.LeitorDTO;

public class LeitorDaoJDBC implements LeitorDao {

    private final Connection conn;

    public LeitorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(LeitorDTO leitor) {
        String sqlUsuario = "INSERT INTO TB_Usuarios (cpf_usuario, senha) VALUES (?, ?)";
        String sqlLeitor = "INSERT INTO TB_Leitores (cpf_leitor, nome_leitor, email_leitor, id_usuario) VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement st = conn.prepareStatement(
                    "SELECT id_usuario FROM TB_Usuarios WHERE id_usuario = ? OR cpf_usuario = ?")) {
                st.setInt(1, leitor.getIdUsuario() != null ? leitor.getIdUsuario() : -1);
                st.setString(2, leitor.getCpfUsuario());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    throw new DbException("Usuário com esse CPF ou ID já existe.");
                }
            }

            int idUsuarioGerado;
            try (PreparedStatement stUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stUsuario.setString(1, leitor.getCpfUsuario());
                stUsuario.setString(2, leitor.getSenha());
                int rowsAffected = stUsuario.executeUpdate();

                if (rowsAffected == 0) {
                    throw new DbException("Erro ao inserir na tabela de usuários, nenhuma linha afetada.");
                }

                try (ResultSet rs = stUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        idUsuarioGerado = rs.getInt(1);
                        leitor.setIdUsuario(idUsuarioGerado);
                    } else {
                        throw new DbException("Erro ao obter ID gerado do usuário.");
                    }
                }
            }

            try (PreparedStatement stLeitor = conn.prepareStatement(sqlLeitor)) {
                stLeitor.setString(1, leitor.getCpfUsuario());
                stLeitor.setString(2, leitor.getNomeLeitor());
                stLeitor.setString(3, leitor.getEmailLeitor());
                stLeitor.setInt(4, idUsuarioGerado);
                stLeitor.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                throw new DbException("Erro ao fazer rollback: " + rollbackEx.getMessage());
            }
            throw new DbException("Erro ao inserir leitor: " + e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DbException("Erro ao redefinir autoCommit: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(LeitorDTO dto) {
        try (PreparedStatement st = conn.prepareStatement("""
            UPDATE TB_Leitores
            SET nome_leitor = ?, email_leitor = ?
            WHERE id_usuario = ?
        """)) {
            st.setString(1, dto.getNomeLeitor());
            st.setString(2, dto.getEmailLeitor());
            st.setInt(3, dto.getIdUsuario());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar leitor: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idUsuario) {
        try (PreparedStatement st = conn.prepareStatement("""
            DELETE FROM TB_Leitores WHERE id_usuario = ?
        """)) {
            st.setInt(1, idUsuario);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar leitor: " + e.getMessage());
        }
    }

    @Override
    public LeitorDTO findById(Integer idUsuario) {
        try (PreparedStatement st = conn.prepareStatement("""
            SELECT l.cpf_leitor, l.nome_leitor, l.email_leitor, u.senha
            FROM TB_Leitores l
            JOIN TB_Usuarios u ON l.id_usuario = u.id_usuario
            WHERE l.id_usuario = ?
        """)) {
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                LeitorDTO dto = new LeitorDTO();
                dto.setIdUsuario(idUsuario);
                dto.setCpfUsuario(rs.getString("cpf_leitor"));
                dto.setNomeLeitor(rs.getString("nome_leitor"));
                dto.setEmailLeitor(rs.getString("email_leitor"));
                dto.setSenha(rs.getString("senha"));
                return dto;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar leitor por ID: " + e.getMessage());
        }
    }
}