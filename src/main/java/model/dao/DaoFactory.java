package model.dao;

import java.sql.Connection;

import model.dao.impl.BibliotecaDaoJDBC;
import model.dao.impl.EnderecoBibliotecaDaoJDBC;
import model.dao.impl.EstoqueDaoJDBC;
import model.dao.impl.LivroDaoJDBC;

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
}
