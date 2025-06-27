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
import model.dao.BibliotecaDao;
import model.dao.DaoFactory;
import model.dto.BibliotecaDTO;

@WebServlet("/bibliotecas")
public class BibliotecaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(java.time.LocalTime.class, new com.google.gson.JsonDeserializer<java.time.LocalTime>() {
            public java.time.LocalTime deserialize(com.google.gson.JsonElement json, java.lang.reflect.Type type,
                                                   com.google.gson.JsonDeserializationContext context) {
                return java.time.LocalTime.parse(json.getAsString());
            }
        })
        .registerTypeAdapter(java.time.LocalTime.class, new com.google.gson.JsonSerializer<java.time.LocalTime>() {
            public com.google.gson.JsonElement serialize(java.time.LocalTime src, java.lang.reflect.Type typeOfSrc,
                                                         com.google.gson.JsonSerializationContext context) {
                return new com.google.gson.JsonPrimitive(src.toString());
            }
        })
        .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {
            BibliotecaDao dao = DaoFactory.createBibliotecaDao(conn);

            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                BibliotecaDTO dto = dao.findById(id);
                if (dto == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"erro\": \"Biblioteca não encontrada.\"}");
                } else {
                    out.print(gson.toJson(dto));
                }
            } else {
                List<BibliotecaDTO> list = dao.findAll();
                out.print(gson.toJson(list));
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

            BibliotecaDTO dto = gson.fromJson(reader, BibliotecaDTO.class);
            BibliotecaDao dao = DaoFactory.createBibliotecaDao(conn);
            dao.insert(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao inserir biblioteca.\"}");
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

            BibliotecaDTO dto = gson.fromJson(reader, BibliotecaDTO.class);
            if (dto.getId() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID da biblioteca é obrigatório para atualização.\"}");
                return;
            }

            BibliotecaDao dao = DaoFactory.createBibliotecaDao(conn);
            dao.update(dto);
            out.print(gson.toJson(dto));

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao atualizar biblioteca.\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("id");

        try (PrintWriter out = resp.getWriter(); Connection conn = DB.getConnection()) {

            if (idStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"ID da biblioteca é obrigatório.\"}");
                return;
            }

            int id = Integer.parseInt(idStr);
            BibliotecaDao dao = DaoFactory.createBibliotecaDao(conn);
            dao.deleteById(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 - Sucesso sem conteúdo

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"erro\": \"ID inválido.\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("{\"erro\": \"Erro ao excluir biblioteca.\"}");
            e.printStackTrace();
        }
    }
}