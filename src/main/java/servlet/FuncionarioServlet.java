package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import db.DB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.dto.FuncionarioDTO;

@WebServlet("/funcionarios")
public class FuncionarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (
            BufferedReader reader = req.getReader();
            PrintWriter out = resp.getWriter();
            Connection conn = DB.getConnection()
        ) {
            FuncionarioDTO dto = gson.fromJson(reader, FuncionarioDTO.class);
            FuncionarioDao dao = DaoFactory.createFuncionarioDao(conn);
            dao.insert(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir funcionário.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (
            BufferedReader reader = req.getReader();
            PrintWriter out = resp.getWriter();
            Connection conn = DB.getConnection()
        ) {
            FuncionarioDTO dto = gson.fromJson(reader, FuncionarioDTO.class);
            FuncionarioDao dao = DaoFactory.createFuncionarioDao(conn);
            dao.update(dto);

            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar funcionário.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("idFuncionario");

        if (idParam != null) {
            int idFuncionario = Integer.parseInt(idParam);

            try (Connection conn = DB.getConnection()) {
                FuncionarioDao dao = DaoFactory.createFuncionarioDao(conn);
                dao.deleteById(idFuncionario);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

            } catch (SQLException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().print("{\"erro\": \"Erro ao deletar funcionário.\"}");
                e.printStackTrace();
            }

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro idFuncionario é obrigatório.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("idFuncionario");
        String idBibliotecaParam = req.getParameter("idBiblioteca");
        String idSetorParam = req.getParameter("idSetor");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (
            PrintWriter out = resp.getWriter();
            Connection conn = DB.getConnection()
        ) {
            FuncionarioDao dao = DaoFactory.createFuncionarioDao(conn);

            if (idParam != null) {
                int idFuncionario = Integer.parseInt(idParam);
                FuncionarioDTO dto = dao.findById(idFuncionario);

                if (dto != null) {
                    out.print(gson.toJson(dto));
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Funcionário não encontrado.");
                }

            } else if (idBibliotecaParam != null && idSetorParam != null) {
                int idBiblioteca = Integer.parseInt(idBibliotecaParam);
                int idSetor = Integer.parseInt(idSetorParam);

                List<FuncionarioDTO> lista = dao.findAll(idBiblioteca, idSetor);
                out.print(gson.toJson(lista));

            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Parâmetros idFuncionario ou (idBiblioteca e idSetor) são obrigatórios.");
            }

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao consultar funcionário.\"}");
            e.printStackTrace();
        }
    }
}