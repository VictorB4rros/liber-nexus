package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import db.DbException;
import model.dao.LivroDao;
import model.dto.LivroDetalhadoDTO;
import model.entities.Livro;

public class LivroDaoJDBC implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Livro obj) {
		
		PreparedStatement st = null;
	    try {
	        st = conn.prepareStatement(
	            "INSERT INTO TB_Livros (titulo_livro, autor_livro, data_publicacao, genero, idioma) " +
	            "VALUES (?, ?, ?, ?, ?)",
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );

	        st.setString(1, obj.getTituloLivro());
	        st.setString(2, obj.getAutorLivro());
	        st.setDate(3, obj.getDataPublicacao());
	        st.setString(4, obj.getGenero());
	        st.setString(5, obj.getIdioma());

	        int rowsAffected = st.executeUpdate();

	        if (rowsAffected > 0) {
	            ResultSet rs = st.getGeneratedKeys();
	            if (rs.next()) {
	                obj.setIdLivro(rs.getInt(1));
	            }
	            rs.close();
	        } else {
	            throw new DbException("Erro inesperado! Nenhuma linha inserida.");
	        }

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	@Override
	public void update(Livro obj) {
		
	    PreparedStatement st = null;
	    try {
	        st = conn.prepareStatement(
	            "UPDATE TB_Livros " +
	            "SET titulo_livro = ?, autor_livro = ?, data_publicacao = ?, genero = ?, idioma = ? " +
	            "WHERE id_livro = ?"
	        );

	        st.setString(1, obj.getTituloLivro());
	        st.setString(2, obj.getAutorLivro());
	        st.setDate(3, obj.getDataPublicacao());
	        st.setString(4, obj.getGenero());
	        st.setString(5, obj.getIdioma());
	        st.setInt(6, obj.getIdLivro());

	        st.executeUpdate();

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } 
	}

	@Override
	public void deleteById(Integer id) {
		
	    PreparedStatement stDeleteEmprestimos = null;
	    PreparedStatement stDeleteEstoque = null;
	    PreparedStatement stDeleteLivro = null;
	    try {

	        stDeleteEmprestimos = conn.prepareStatement("""
	            DELETE FROM TB_Emprestimos 
	            WHERE id_livro = ?
	        """);
	        stDeleteEmprestimos.setInt(1, id);
	        stDeleteEmprestimos.executeUpdate();

	        stDeleteEstoque = conn.prepareStatement("""
	            DELETE FROM TB_Estoque 
	            WHERE id_livro = ?
	        """);
	        stDeleteEstoque.setInt(1, id);
	        stDeleteEstoque.executeUpdate();

	        stDeleteLivro = conn.prepareStatement("""
	            DELETE FROM TB_Livros 
	            WHERE id_livro = ?
	        """);
	        stDeleteLivro.setInt(1, id);
	        int rows = stDeleteLivro.executeUpdate();

	        if (rows == 0) {
	            throw new DbException("Nenhum livro encontrado com o ID informado.");
	        }

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } finally {
	        try {
	            if (stDeleteEmprestimos != null) stDeleteEmprestimos.close();
	            if (stDeleteEstoque != null) stDeleteEstoque.close();
	            if (stDeleteLivro != null) stDeleteLivro.close();
	        } catch (SQLException e) {
	            throw new DbException(e.getMessage());
	        }
	    }
	}

	@Override
	public LivroDetalhadoDTO findById(Integer id) {

	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement("""
	            SELECT
	                l.id_livro,
	                l.titulo_livro,
	                l.autor_livro,
	                l.data_publicacao,
	                l.genero,
	                l.idioma,
	                b.nome_biblioteca,
	                e.qtd_estoque,
	                s.descricao_status
	            FROM
	                TB_Livros l
	            JOIN
	                TB_Estoque e ON l.id_livro = e.id_livro
	            JOIN
	                TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
	            JOIN
	                TB_Status_Estoque s ON e.id_status = s.id_status
	            WHERE
	                l.id_livro = ?
	        """);

	        st.setInt(1, id);
	        rs = st.executeQuery();

	        LivroDetalhadoDTO dto = null;

	        while (rs.next()) {
	            if (dto == null) {
	                dto = new LivroDetalhadoDTO();
	                dto.setIdLivro(rs.getInt("id_livro"));
	                dto.setTituloLivro(rs.getString("titulo_livro"));
	                dto.setAutorLivro(rs.getString("autor_livro"));
	                dto.setDataPublicacao(rs.getDate("data_publicacao"));
	                dto.setGenero(rs.getString("genero"));
	                dto.setIdioma(rs.getString("idioma"));
	            }

	            String nomeBiblioteca = rs.getString("nome_biblioteca");
	            int quantidade = rs.getInt("qtd_estoque");
	            String status = rs.getString("descricao_status");

	            dto.addBiblioteca(nomeBiblioteca, quantidade, status);
	        }

	        return dto;

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	@Override
	public List<LivroDetalhadoDTO> findAll() {
		
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        st = conn.prepareStatement("""
	            SELECT
	                l.id_livro,
	                l.titulo_livro,
	                l.autor_livro,
	                l.data_publicacao,
	                l.genero,
	                l.idioma,
	                b.nome_biblioteca,
	                e.qtd_estoque,
	                s.descricao_status
	            FROM
	                TB_Livros l
	            LEFT JOIN
	                TB_Estoque e ON l.id_livro = e.id_livro
	            LEFT JOIN
	                TB_Bibliotecas b ON e.id_biblioteca = b.id_biblioteca
	            LEFT JOIN
	                TB_Status_Estoque s ON e.id_status = s.id_status
	            ORDER BY l.titulo_livro
	        """);

	        rs = st.executeQuery();

	        Map<Integer, LivroDetalhadoDTO> map = new LinkedHashMap<>();

	        while (rs.next()) {
	            int idLivro = rs.getInt("id_livro");

	            LivroDetalhadoDTO dto = map.get(idLivro);
	            if (dto == null) {
	                dto = new LivroDetalhadoDTO();
	                dto.setIdLivro(idLivro);
	                dto.setTituloLivro(rs.getString("titulo_livro"));
	                dto.setAutorLivro(rs.getString("autor_livro"));
	                dto.setDataPublicacao(rs.getDate("data_publicacao"));
	                dto.setGenero(rs.getString("genero"));
	                dto.setIdioma(rs.getString("idioma"));
	                map.put(idLivro, dto);
	            }

	            String nomeBiblioteca = rs.getString("nome_biblioteca");
	            if (nomeBiblioteca != null) {
	                int quantidade = rs.getInt("qtd_estoque");
	                String status = rs.getString("descricao_status");
	                dto.addBiblioteca(nomeBiblioteca, quantidade, status);
	            }
	        }

	        return new ArrayList<>(map.values());

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	@Override
	public List<Livro> findByGenero(String genero) {

		PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = conn.prepareStatement("""
	            SELECT id_livro, titulo_livro, autor_livro, data_publicacao, genero, idioma
	            FROM TB_Livros
	            WHERE genero LIKE ?
	            ORDER BY titulo_livro
	        """);

	        st.setString(1, "%" + genero + "%");
	        rs = st.executeQuery();

	        List<Livro> lista = new ArrayList<>();

	        while (rs.next()) {
	            Livro livro = new Livro();
	            livro.setIdLivro(rs.getInt("id_livro"));
	            livro.setTituloLivro(rs.getString("titulo_livro"));
	            livro.setAutorLivro(rs.getString("autor_livro"));
	            livro.setDataPublicacao(rs.getDate("data_publicacao"));
	            livro.setGenero(rs.getString("genero"));
	            livro.setIdioma(rs.getString("idioma"));
	            lista.add(livro);
	        }

	        return lista;

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

}
