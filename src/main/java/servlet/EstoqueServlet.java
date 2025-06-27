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
import model.dao.EstoqueDao;
import model.dto.EstoqueDTO;

@WebServlet("/estoque")
public class EstoqueServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idBibStr = req.getParameter("idBiblioteca");
        String idLivroStr = req.getParameter("idLivro");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            EstoqueDao dao = DaoFactory.createEstoqueDao(conn);

            if (idBibStr != null && idLivroStr != null) {
                // findById
                int idBib = Integer.parseInt(idBibStr);
                int idLivro = Integer.parseInt(idLivroStr);
                EstoqueDTO estoque = dao.findById(idBib, idLivro);
                if (estoque == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"erro\": \"Registro de estoque não encontrado.\"}");
                } else {
                    out.print(gson.toJson(estoque));
                }

            } else if (idBibStr != null) {
                // findByBiblioteca
                int idBib = Integer.parseInt(idBibStr);
                List<EstoqueDTO> lista = dao.findByBiblioteca(idBib);
                out.print(gson.toJson(lista));

            } else if (idLivroStr != null) {
                // findByLivro
                int idLivro = Integer.parseInt(idLivroStr);
                List<EstoqueDTO> lista = dao.findByLivro(idLivro);
                out.print(gson.toJson(lista));

            } else {
                // findAll
                List<EstoqueDTO> lista = dao.findAll();
                out.print(gson.toJson(lista));
            }

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao acessar o banco de dados.\"}");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"IDs inválidos.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader();
             PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            EstoqueDTO estoque = gson.fromJson(reader, EstoqueDTO.class);
            EstoqueDao dao = DaoFactory.createEstoqueDao(conn);
            dao.insert(estoque);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(estoque));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir registro.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader();
             PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            EstoqueDTO estoque = gson.fromJson(reader, EstoqueDTO.class);
            if (estoque.getIdBiblioteca() == null || estoque.getIdLivro() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"IDs compostos obrigatórios.\"}");
                return;
            }

            EstoqueDao dao = DaoFactory.createEstoqueDao(conn);
            dao.update(estoque);
            out.print(gson.toJson(estoque));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar o estoque.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idBibStr = req.getParameter("idBiblioteca");
        String idLivroStr = req.getParameter("idLivro");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {

            if (idBibStr == null || idLivroStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"IDs compostos obrigatórios para exclusão.\"}");
                return;
            }

            int idBib = Integer.parseInt(idBibStr);
            int idLivro = Integer.parseInt(idLivroStr);

            EstoqueDao dao = DaoFactory.createEstoqueDao(conn);
            dao.deleteById(idBib, idLivro);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"IDs inválidos.\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao excluir registro de estoque.\"}");
            e.printStackTrace();
        }
    }
}
