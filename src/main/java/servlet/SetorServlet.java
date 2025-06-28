package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import db.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.SetorDao;
import model.dto.SetorDTO;

@WebServlet("/setores")
public class SetorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSetorStr = req.getParameter("id");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            SetorDao dao = DaoFactory.createSetorDao(conn);

            if (idSetorStr != null) {
                // findById
                int idSetor = Integer.parseInt(idSetorStr);
                SetorDTO setor = dao.findById(idSetor);
                if (setor == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"erro\": \"Setor não encontrado.\"}");
                } else {
                    out.print(gson.toJson(setor));
                }
            }

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao acessar o banco de dados.\"}");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"ID inválido.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader();
             PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            SetorDTO setor = gson.fromJson(reader, SetorDTO.class);
            SetorDao dao = DaoFactory.createSetorDao(conn);
            dao.insert(setor);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(setor));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir setor.\"}");
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

            SetorDTO setor = gson.fromJson(reader, SetorDTO.class);
            if (setor.getIdSetor() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID do setor é obrigatório.\"}");
                return;
            }

            SetorDao dao = DaoFactory.createSetorDao(conn);
            dao.update(setor);
            out.print(gson.toJson(setor));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar setor.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSetorStr = req.getParameter("id");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {

            if (idSetorStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID do setor é obrigatório para exclusão.\"}");
                return;
            }

            int idSetor = Integer.parseInt(idSetorStr);

            SetorDao dao = DaoFactory.createSetorDao(conn);
            dao.deleteById(idSetor);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao excluir setor.\"}");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"ID inválido.\"}");
        }
    }
}