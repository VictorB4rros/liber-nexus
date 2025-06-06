package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import com.google.gson.Gson;

import db.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.LivroDao;
import model.entities.Livro;

public class LivroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLivroStr = req.getParameter("idLivro");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            if (idLivroStr == null || idLivroStr.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"Parâmetro idLivro é obrigatório.\"}");
                return;
            }

            int idLivro;
            try {
                idLivro = Integer.parseInt(idLivroStr);
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"erro\": \"Parâmetro idLivro deve ser um número inteiro.\"}");
                return;
            }

            try (Connection conn = DB.getConnection()) {
                LivroDao dao = DaoFactory.createLivroDao(conn);
                Livro livro = dao.findById(idLivro);

                if (livro == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"erro\": \"Livro não encontrado.\"}");
                } else {
                    Gson gson = new Gson();
                    String json = gson.toJson(livro);
                    out.print(json);
                }

            } catch (SQLException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"erro\": \"Erro ao acessar o banco de dados.\"}");
                e.printStackTrace();
            }
        }
    }
}
