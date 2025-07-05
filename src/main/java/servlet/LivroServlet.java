package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import db.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.LivroDao;
import model.dto.LivroDetalhadoDTO;
import model.entities.Livro;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setHeader("Access-Control-Allow-Origin", "*");
	    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
	    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
		
		String idLivroStr = req.getParameter("idLivro");
		String genero = req.getParameter("genero");

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
			LivroDao dao = DaoFactory.createLivroDao(conn);

			// Busca por ID
			if (idLivroStr != null) {
				int idLivro = Integer.parseInt(idLivroStr);
				LivroDetalhadoDTO livro = dao.findById(idLivro);
				if (livro == null) {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					out.print("{\"erro\": \"Livro não encontrado.\"}");
				} else {
					out.print(gson.toJson(livro));
				}

				// Busca por gênero
			} else if (genero != null && !genero.isBlank()) {
				List<Livro> livros = dao.findByGenero(genero);
				out.print(gson.toJson(livros));

				// Lista geral
			} else {
				List<LivroDetalhadoDTO> livros = dao.findAll();
				out.print(gson.toJson(livros));
			}

		} catch (SQLException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().print("{\"erro\": \"Erro ao acessar o banco de dados.\"}");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().print("{\"erro\": \"idLivro inválido.\"}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try (BufferedReader reader = req.getReader();
				PrintWriter out = resp.getWriter();
				Connection conn = DB.getConnection()) {

			Livro livro = gson.fromJson(reader, Livro.class);

			LivroDao dao = DaoFactory.createLivroDao(conn);
			dao.insert(livro);

			resp.setStatus(HttpServletResponse.SC_CREATED);
			out.print(gson.toJson(livro));

		} catch (SQLException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().print("{\"erro\": \"Erro ao inserir o livro.\"}");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try (BufferedReader reader = req.getReader();
				PrintWriter out = resp.getWriter();
				Connection conn = DB.getConnection()) {

			Livro livro = gson.fromJson(reader, Livro.class);

			if (livro.getIdLivro() == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("{\"erro\": \"ID do livro é obrigatório para atualização.\"}");
				return;
			}

			LivroDao dao = DaoFactory.createLivroDao(conn);
			dao.update(livro);
			out.print(gson.toJson(livro));

		} catch (SQLException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().print("{\"erro\": \"Erro ao atualizar o livro.\"}");
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
		
		String idLivroStr = req.getParameter("idLivro");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {

			if (idLivroStr == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("{\"erro\": \"Parâmetro idLivro é obrigatório para exclusão.\"}");
				return;
			}

			int idLivro = Integer.parseInt(idLivroStr);

			LivroDao dao = DaoFactory.createLivroDao(conn);
			dao.deleteById(idLivro);

			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

		} catch (NumberFormatException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().print("{\"erro\": \"Parâmetro idLivro inválido.\"}");
		} catch (SQLException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().print("{\"erro\": \"Erro ao excluir o livro.\"}");
			e.printStackTrace();
		}
	}
}
