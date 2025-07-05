package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import db.DB;
import db.DbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.EmprestimoDao;
import model.dao.impl.EmprestimoDaoJDBC;
import model.dto.EmprestimoDTO;

@WebServlet("/emprestimos")
public class EmprestimoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EmprestimoDao emprestimoDao;
    private Gson gson;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        emprestimoDao = new EmprestimoDaoJDBC(DB.getConnection());

        // Configuração do Gson para tratar java.sql.Date no formato "yyyy-MM-dd"
        gson = new GsonBuilder()
            .registerTypeAdapter(Date.class,
                (JsonDeserializer<Date>) (json, typeOfT, context) -> Date.valueOf(json.getAsString()))
            .create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
        String idEmprestimoParam = request.getParameter("idEmprestimo");
        String idUsuarioParam = request.getParameter("idUsuario");

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (idEmprestimoParam != null) {
                int idEmprestimo = Integer.parseInt(idEmprestimoParam);
                EmprestimoDTO emprestimo = emprestimoDao.findById(idEmprestimo);
                
                if (emprestimo != null) {
                    response.getWriter().write(gson.toJson(emprestimo));
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Empréstimo não encontrado.");
                }

            } else if (idUsuarioParam != null) {
                int idUsuario = Integer.parseInt(idUsuarioParam);
                List<EmprestimoDTO> emprestimos = emprestimoDao.findAll(idUsuario);
                response.getWriter().write(gson.toJson(emprestimos));

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Informe 'idEmprestimo' para buscar um empréstimo ou 'idUsuario' para listar todos os empréstimos do usuário.");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        } catch (DbException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
        try {
            EmprestimoDTO dto = gson.fromJson(request.getReader(), EmprestimoDTO.class);
            emprestimoDao.insert(dto);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (DbException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao processar JSON: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
        try {
            EmprestimoDTO dto = gson.fromJson(request.getReader(), EmprestimoDTO.class);
            emprestimoDao.update(dto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DbException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao processar JSON: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
    	
        try {
            String idEmprestimoParam = request.getParameter("idEmprestimo");
            if (idEmprestimoParam != null) {
                Integer idEmprestimo = Integer.parseInt(idEmprestimoParam);
                emprestimoDao.deleteById(idEmprestimo);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idEmprestimo é obrigatório.");
            }
        } catch (DbException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}