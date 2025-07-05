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
import model.dao.EnderecoBibliotecaDao;
import model.dto.EnderecoBibliotecaDTO;

@WebServlet("/endereco-biblioteca")
public class EnderecoBibliotecaServlet extends HttpServlet {

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
    	
    	String idStr = req.getParameter("id");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            if (idStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID da biblioteca é obrigatório.\"}");
                return;
            }

            int id = Integer.parseInt(idStr);
            EnderecoBibliotecaDao dao = DaoFactory.createEnderecoBibliotecaDao(conn);
            EnderecoBibliotecaDTO dto = dao.findById(id);

            if (dto == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"erro\": \"Endereço da biblioteca não encontrado.\"}");
            } else {
                out.print(gson.toJson(dto));
            }

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"ID inválido.\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao acessar o banco de dados.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
    	resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader(); PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            EnderecoBibliotecaDTO dto = gson.fromJson(reader, EnderecoBibliotecaDTO.class);
            EnderecoBibliotecaDao dao = DaoFactory.createEnderecoBibliotecaDao(conn);
            dao.insert(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir endereço.\"}");
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

        try (BufferedReader reader = req.getReader(); PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            EnderecoBibliotecaDTO dto = gson.fromJson(reader, EnderecoBibliotecaDTO.class);

            if (dto.getIdBiblioteca() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID da biblioteca é obrigatório para atualização.\"}");
                return;
            }

            EnderecoBibliotecaDao dao = DaoFactory.createEnderecoBibliotecaDao(conn);
            dao.update(dto);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar endereço.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
    	String idStr = req.getParameter("id");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            if (idStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID da biblioteca é obrigatório para exclusão.\"}");
                return;
            }

            int id = Integer.parseInt(idStr);
            EnderecoBibliotecaDao dao = DaoFactory.createEnderecoBibliotecaDao(conn);
            dao.deleteById(id);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"ID inválido.\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao excluir endereço.\"}");
            e.printStackTrace();
        }
    }
}