package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.FuncionarioDao;
import model.dto.FuncionarioDTO;

public class FuncionarioDaoJDBC implements FuncionarioDao {

    private final Connection conn;

    public FuncionarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(FuncionarioDTO dto) {
        PreparedStatement checkUsuario = null;
        PreparedStatement insertUsuario = null;
        PreparedStatement insertFuncionario = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            checkUsuario = conn.prepareStatement("""
                SELECT id_usuario FROM TB_Usuarios
                WHERE id_usuario = ? AND cpf_usuario = ?
            """);
            checkUsuario.setObject(1, dto.getIdUsuario());
            checkUsuario.setString(2, dto.getCpfUsuario());
            rs = checkUsuario.executeQuery();

            if (!rs.next()) {

                insertUsuario = conn.prepareStatement("""
                    INSERT INTO TB_Usuarios (cpf_usuario, senha)
                    VALUES (?, ?)
                """, Statement.RETURN_GENERATED_KEYS);

                insertUsuario.setString(1, dto.getCpfUsuario());
                insertUsuario.setString(2, dto.getSenha());
                insertUsuario.executeUpdate();

                ResultSet generatedKeys = insertUsuario.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    dto.setIdUsuario(generatedId);
                } else {
                    throw new DbException("Falha ao obter o ID do novo usuário.");
                }
            }

            insertFuncionario = conn.prepareStatement("""
                INSERT INTO TB_Funcionarios
                (cpf_funcionario, nome_funcionario, email_funcionario,
                 id_usuario, id_biblioteca, id_setor)
                VALUES (?, ?, ?, ?, ?, ?)
            """);

            insertFuncionario.setString(1, dto.getCpfUsuario());
            insertFuncionario.setString(2, dto.getNomeFuncionario());
            insertFuncionario.setString(3, dto.getEmailFuncionario());
            insertFuncionario.setInt(4, dto.getIdUsuario());
            insertFuncionario.setObject(5, dto.getIdBiblioteca());
            insertFuncionario.setObject(6, dto.getIdSetor());

            insertFuncionario.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new DbException("Erro ao realizar rollback: " + ex.getMessage());
            }
            throw new DbException("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkUsuario != null) checkUsuario.close();
                if (insertUsuario != null) insertUsuario.close();
                if (insertFuncionario != null) insertFuncionario.close();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    
    @Override
    public void update(FuncionarioDTO obj) {
        try (PreparedStatement st = conn.prepareStatement("""
            UPDATE TB_Funcionarios
            SET nome_funcionario = ?, email_funcionario = ?,
                id_biblioteca = ?, id_setor = ?
            WHERE id_funcionario = ?
        """)) {
            st.setString(1, obj.getNomeFuncionario());
            st.setString(2, obj.getEmailFuncionario());
            st.setInt(3, obj.getIdBiblioteca());
            st.setInt(4, obj.getIdSetor());
            st.setInt(5, obj.getIdFuncionario());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idFuncionario) {
        try (PreparedStatement st = conn.prepareStatement("""
            DELETE FROM TB_Funcionarios WHERE id_funcionario = ?
        """)) {
            st.setInt(1, idFuncionario);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    @Override
    public FuncionarioDTO findById(Integer idFuncionario) {
        try (PreparedStatement st = conn.prepareStatement("""
            SELECT f.*, b.nome_biblioteca, s.descricao_setor
            FROM TB_Funcionarios f
            JOIN TB_Bibliotecas b ON f.id_biblioteca = b.id_biblioteca
            JOIN TB_Setores s ON f.id_setor = s.id_setor
            JOIN TB_Usuarios u ON f.id_usuario = u.id_usuario
            WHERE f.id_funcionario = ?
        """)) {
            st.setInt(1, idFuncionario);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                FuncionarioDTO dto = new FuncionarioDTO();
                dto.setIdFuncionario(rs.getInt("id_funcionario"));
                dto.setIdBiblioteca(rs.getInt("id_biblioteca"));
                dto.setIdSetor(rs.getInt("id_setor"));
                dto.setNomeFuncionario(rs.getString("nome_funcionario"));
                dto.setEmailFuncionario(rs.getString("email_funcionario"));
                dto.setCpfUsuario(rs.getString("cpf_funcionario"));
                dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
                dto.setNomeSetor(rs.getString("descricao_setor"));
                return dto;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar funcionário por ID: " + e.getMessage());
        }
    }

    @Override
    public List<FuncionarioDTO> findAll(Integer idBiblioteca, Integer idSetor) {
        try (PreparedStatement st = conn.prepareStatement("""
            SELECT f.*, b.nome_biblioteca, s.descricao_setor
            FROM TB_Funcionarios f
            JOIN TB_Bibliotecas b ON f.id_biblioteca = b.id_biblioteca
            JOIN TB_Setores s ON f.id_setor = s.id_setor
            JOIN TB_Usuarios u ON f.id_usuario = u.id_usuario
            WHERE f.id_biblioteca = ? AND f.id_setor = ?
        """)) {
            st.setInt(1, idBiblioteca);
            st.setInt(2, idSetor);
            ResultSet rs = st.executeQuery();

            List<FuncionarioDTO> lista = new ArrayList<>();
            while (rs.next()) {
                FuncionarioDTO dto = new FuncionarioDTO();
                dto.setIdFuncionario(rs.getInt("id_funcionario"));
                dto.setIdBiblioteca(idBiblioteca);
                dto.setIdSetor(idSetor);
                dto.setNomeFuncionario(rs.getString("nome_funcionario"));
                dto.setEmailFuncionario(rs.getString("email_funcionario"));
                dto.setCpfUsuario(rs.getString("cpf_funcionario"));
                dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
                dto.setNomeSetor(rs.getString("descricao_setor"));
                lista.add(dto);
            }
            return lista;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar lista de funcionários: " + e.getMessage());
        }
    }
}
