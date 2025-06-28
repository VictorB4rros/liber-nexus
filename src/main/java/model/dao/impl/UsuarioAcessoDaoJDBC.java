package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.UsuarioAcessoDao;
import model.dto.UsuarioAcessoDTO;

public class UsuarioAcessoDaoJDBC implements UsuarioAcessoDao {

    private final Connection conn;

    public UsuarioAcessoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(UsuarioAcessoDTO dto) {
        String sql = """
            INSERT INTO TB_Usuarios_Acessos (id_usuario, cod_acesso)
            VALUES (?, ?)
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, dto.getIdUsuario());
            st.setInt(2, dto.getCodAcesso());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir acesso de usuário: " + e.getMessage());
        }
    }

    @Override
    public void update(UsuarioAcessoDTO dto) {
        String sql = """
            UPDATE TB_Usuarios_Acessos
            SET cod_acesso = ?
            WHERE id_usuario = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, dto.getCodAcesso());
            st.setInt(2, dto.getIdUsuario());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar acesso de usuário: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer idUsuario, Integer codAcesso) {
        String sql = """
            DELETE FROM TB_Usuarios_Acessos
            WHERE id_usuario = ? AND cod_acesso = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idUsuario);
            st.setInt(2, codAcesso);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar acesso de usuário: " + e.getMessage());
        }
    }

    @Override
    public UsuarioAcessoDTO findById(Integer idUsuario, Integer codAcesso) {
        String sql = """
            SELECT ua.id_usuario, ua.cod_acesso, a.tipo_acesso
            FROM TB_Usuarios_Acessos ua
            JOIN TB_Acessos a ON ua.cod_acesso = a.cod_acesso
            WHERE ua.id_usuario = ? AND ua.cod_acesso = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idUsuario);
            st.setInt(2, codAcesso);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                UsuarioAcessoDTO dto = new UsuarioAcessoDTO();
                dto.setIdUsuario(rs.getInt("id_usuario"));
                dto.setCodAcesso(rs.getInt("cod_acesso"));
                dto.setTipoAcesso(rs.getString("tipo_acesso"));
                return dto;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar acesso de usuário: " + e.getMessage());
        }
    }
}