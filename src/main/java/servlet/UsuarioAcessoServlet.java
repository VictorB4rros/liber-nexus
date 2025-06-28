package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import com.google.gson.Gson;

import db.DB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.UsuarioAcessoDao;
import model.dto.UsuarioAcessoDTO;

@WebServlet("/liberNexus/acessos")
public class UsuarioAcessoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader();
             PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            UsuarioAcessoDTO dto = gson.fromJson(reader, UsuarioAcessoDTO.class);
            UsuarioAcessoDao dao = DaoFactory.createUsuarioAcessoDao(conn);
            dao.insert(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(dto));

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir acesso.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader();
             PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            UsuarioAcessoDTO dto = gson.fromJson(reader, UsuarioAcessoDTO.class);
            UsuarioAcessoDao dao = DaoFactory.createUsuarioAcessoDao(conn);
            dao.update(dto);

            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(dto));

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar acesso.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String idUsuarioParam = req.getParameter("idUsuario");
        String codAcessoParam = req.getParameter("codAcesso");

        try (Connection conn = DB.getConnection()) {
            Integer idUsuario = Integer.parseInt(idUsuarioParam);
            Integer codAcesso = Integer.parseInt(codAcessoParam);

            UsuarioAcessoDao dao = DaoFactory.createUsuarioAcessoDao(conn);
            dao.delete(idUsuario, codAcesso);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao deletar acesso.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String idUsuarioParam = req.getParameter("idUsuario");
        String codAcessoParam = req.getParameter("codAcesso");

        try (PrintWriter out = resp.getWriter();
             Connection conn = DB.getConnection()) {

            Integer idUsuario = Integer.parseInt(idUsuarioParam);
            Integer codAcesso = Integer.parseInt(codAcessoParam);

            UsuarioAcessoDao dao = DaoFactory.createUsuarioAcessoDao(conn);
            UsuarioAcessoDTO dto = dao.findById(idUsuario, codAcesso);

            if (dto != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.print(gson.toJson(dto));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"erro\": \"Acesso não encontrado.\"}");
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao buscar acesso.\"}");
            e.printStackTrace();
        }
    }
}