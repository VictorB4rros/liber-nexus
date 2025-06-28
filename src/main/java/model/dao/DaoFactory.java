package model.dao;

import java.sql.Connection;

import model.dao.impl.BibliotecaDaoJDBC;
import model.dao.impl.EmailDaoJDBC;
import model.dao.impl.EmprestimoDaoJDBC;
import model.dao.impl.EnderecoBibliotecaDaoJDBC;
import model.dao.impl.EstoqueDaoJDBC;
import model.dao.impl.FuncionarioDaoJDBC;
import model.dao.impl.LeitorDaoJDBC;
import model.dao.impl.LivroDaoJDBC;
import model.dao.impl.SetorDaoJDBC;
import model.dao.impl.TelefoneDaoJDBC;
import model.dao.impl.UsuarioAcessoDaoJDBC;

public class DaoFactory {

	public static LivroDao createLivroDao(Connection conn) {
		return new LivroDaoJDBC(conn);
	}
	
    public static EstoqueDao createEstoqueDao(Connection conn) {
        return new EstoqueDaoJDBC(conn);
    }
    
    public static BibliotecaDao createBibliotecaDao(Connection conn) {
        return new BibliotecaDaoJDBC(conn);
    }
    
    public static EnderecoBibliotecaDao createEnderecoBibliotecaDao(Connection conn) {
        return new EnderecoBibliotecaDaoJDBC(conn);
    }
    
    public static SetorDao createSetorDao(Connection conn) {
    	return new SetorDaoJDBC(conn);
    }
    
    public static TelefoneDao createTelefoneDao(Connection conn) {
    	return new TelefoneDaoJDBC(conn);
    }
    
    public static EmailDao createEmailDao(Connection conn) {
    	return new EmailDaoJDBC(conn);
    }
    
    public static FuncionarioDao createFuncionarioDao(Connection conn) {
    	return new FuncionarioDaoJDBC(conn);
    }
    
    public static LeitorDao createLeitorDao(Connection conn) {
    	return new LeitorDaoJDBC(conn);
    }
    
    public static UsuarioAcessoDao createUsuarioAcessoDao(Connection conn) {
    	return new UsuarioAcessoDaoJDBC(conn);
    }
    
    public static EmprestimoDao createEmprestimoDao(Connection conn) {
    	return new EmprestimoDaoJDBC(conn);
    }
}
