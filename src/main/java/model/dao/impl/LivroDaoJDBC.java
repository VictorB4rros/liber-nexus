package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import model.dao.LivroDao;
import model.entities.Biblioteca;
import model.entities.Estoque;
import model.entities.Livro;
import model.entities.StatusEstoque;

public class LivroDaoJDBC implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Livro obj) {
		

	}

	@Override
	public void update(Livro obj) {
		

	}

	@Override
	public void deleteById(Integer id) {
		

	}

	@Override
	public Livro findById(Integer id) {

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
			Livro livro = new Livro();
			if (rs.next()) {
				
				livro.setIdLivro(rs.getInt("id_livro"));
				livro.setTituloLivro(rs.getString("titulo_livro"));
				livro.setAutorLivro(rs.getString("autor_livro"));
				livro.setDataPublicacao(rs.getDate("data_publicacao"));
				livro.setGenero(rs.getString("genero"));
				livro.setIdioma(rs.getString("idioma"));
				
				Biblioteca biblioteca = new Biblioteca();
				biblioteca.setNomeBiblioteca(rs.getString("nome_biblioteca"));
				StatusEstoque status = new StatusEstoque();
				status.setDescricao(rs.getString("descricao_status"));
				
				Estoque estoque = new Estoque();
				estoque.setBiblioteca(biblioteca);
                estoque.setQuantidade(rs.getInt("qtd_estoque"));
                estoque.setStatus(status);

                livro.addEstoque(estoque);
			}
			return livro;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Livro> findAll() {
		
		return null;
	}

}
