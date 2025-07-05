package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.EmprestimoDao;
import model.dto.EmprestimoDTO;

public class EmprestimoDaoJDBC implements EmprestimoDao {

    private final Connection conn;

    public EmprestimoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(EmprestimoDTO dto) {
        String sql = """
            INSERT INTO TB_Emprestimos
                (id_usuario, id_biblioteca, id_livro, data_reserva, data_coleta, data_prev_devolucao, data_devolucao)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, dto.getIdUsuario());
            st.setInt(2, dto.getIdBiblioteca());
            st.setInt(3, dto.getIdLivro());
            st.setDate(4, dto.getDataReserva());
            st.setDate(5, dto.getDataColeta());
            st.setDate(6, dto.getDataPrevDevolucao());
            st.setDate(7, dto.getDataDevolucao());

            int rows = st.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        dto.setIdEmprestimo(rs.getInt(1));
                    }
                }
            } else {
                throw new DbException("Erro inesperado: nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir empréstimo: " + e.getMessage());
        }
    }

    @Override
    public void update(EmprestimoDTO dto) {
        String sql = """
            UPDATE TB_Emprestimos
            SET data_reserva = ?, data_coleta = ?, data_prev_devolucao = ?, data_devolucao = ?
            WHERE id_emprestimo = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, dto.getDataReserva());
            st.setDate(2, dto.getDataColeta());
            st.setDate(3, dto.getDataPrevDevolucao());
            st.setDate(4, dto.getDataDevolucao());
            st.setInt(5, dto.getIdEmprestimo());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idEmprestimo) {
        String sql = "DELETE FROM TB_Emprestimos WHERE id_emprestimo = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idEmprestimo);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar empréstimo: " + e.getMessage());
        }
    }

    @Override
    public EmprestimoDTO findById(Integer idEmprestimo) {
    	String sql = """
    		    SELECT 
    		        e.*,
    		        COALESCE(ltr.nome_leitor, func.nome_funcionario) AS nome_usuario,
    		        b.nome_biblioteca,
    		        l.titulo_livro
    		    FROM TB_Emprestimos e
    		    JOIN TB_Usuarios u ON e.id_usuario = u.id_usuario
    		    LEFT JOIN TB_Leitores ltr ON u.id_usuario = ltr.id_usuario
    		    LEFT JOIN TB_Funcionarios func ON u.id_usuario = func.id_usuario
    		    JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
    		    JOIN TB_Livros l ON e.id_livro = l.id_livro
    		    WHERE e.id_emprestimo = ?
    		""";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idEmprestimo);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return instantiateEmprestimoDTO(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar empréstimo por ID: " + e.getMessage());
        }
    }

    public List<EmprestimoDTO> findAll(Integer idUsuario) {
        String sql = """
            SELECT e.*, 
                   l.nome_leitor AS nome_usuario,
                   b.nome_biblioteca,
                   lv.titulo_livro
            FROM TB_Emprestimos e
            JOIN TB_Leitores l ON e.id_usuario = l.id_usuario
            JOIN TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
            JOIN TB_Livros lv ON e.id_livro = lv.id_livro
            WHERE e.id_usuario = ?
        """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();

            List<EmprestimoDTO> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(instantiateEmprestimoDTO(rs));
            }
            return lista;

        } catch (SQLException e) {
            throw new DbException("Erro ao buscar empréstimos do usuário: " + e.getMessage());
        }
    }

    private EmprestimoDTO instantiateEmprestimoDTO(ResultSet rs) throws SQLException {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setIdEmprestimo(rs.getInt("id_emprestimo"));
        dto.setIdUsuario(rs.getInt("id_usuario"));
        dto.setIdBiblioteca(rs.getInt("id_biblioteca"));
        dto.setIdLivro(rs.getInt("id_livro"));
        dto.setDataReserva(rs.getDate("data_reserva"));
        dto.setDataColeta(rs.getDate("data_coleta"));
        dto.setDataPrevDevolucao(rs.getDate("data_prev_devolucao"));
        dto.setDataDevolucao(rs.getDate("data_devolucao"));
        dto.setNomeUsuario(rs.getString("nome_usuario"));
        dto.setNomeBiblioteca(rs.getString("nome_biblioteca"));
        dto.setNomeLivro(rs.getString("titulo_livro"));
        return dto;
    }
}