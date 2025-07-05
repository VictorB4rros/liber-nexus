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
import model.dao.EmailDao;
import model.dto.EmailDTO;

@WebServlet("/emails")
public class EmailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
    	resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader();
             PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            EmailDTO dto = gson.fromJson(reader, EmailDTO.class);
            EmailDao dao = DaoFactory.createEmailDao(conn);
            dao.insert(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir email.\"}");
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

            EmailDTO dto = gson.fromJson(reader, EmailDTO.class);
            if (dto.getIdBiblioteca() == null || dto.getIdSetor() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"IDs compostos obrigatórios.\"}");
                return;
            }

            EmailDao dao = DaoFactory.createEmailDao(conn);
            dao.update(dto);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar email.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
    	String idBibStr = req.getParameter("idBiblioteca");
        String idSetorStr = req.getParameter("idSetor");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {

            if (idBibStr == null || idSetorStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"IDs compostos obrigatórios.\"}");
                return;
            }

            int idBib = Integer.parseInt(idBibStr);
            int idSetor = Integer.parseInt(idSetorStr);

            EmailDao dao = DaoFactory.createEmailDao(conn);
            dao.deleteById(idBib, idSetor);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"IDs inválidos.\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao excluir email.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setHeader("Access-Control-Allow-Origin", "*");
    	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
    	String idBibStr = req.getParameter("idBiblioteca");
        String idSetorStr = req.getParameter("idSetor");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            EmailDao dao = DaoFactory.createEmailDao(conn);

            if (idBibStr != null && idSetorStr != null) {
                // findById
                int idBib = Integer.parseInt(idBibStr);
                int idSetor = Integer.parseInt(idSetorStr);
                EmailDTO tel = dao.findById(idBib, idSetor);
                if (tel == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"erro\": \"Telefone não encontrado.\"}");
                } else {
                    out.print(gson.toJson(tel));
                }

            } else if (idBibStr != null) {
                
                int idBib = Integer.parseInt(idBibStr);
                List<EmailDTO> lista = dao.findAll(idBib);
                out.print(gson.toJson(lista));

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"Parâmetros obrigatórios ausentes.\"}");
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
}