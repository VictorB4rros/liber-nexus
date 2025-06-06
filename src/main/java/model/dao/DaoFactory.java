package model.dao;

import java.sql.Connection;

import model.dao.impl.LivroDaoJDBC;

public class DaoFactory {

	public static LivroDao createLivroDao(Connection conn) {
		return new LivroDaoJDBC(conn);
	}
}
