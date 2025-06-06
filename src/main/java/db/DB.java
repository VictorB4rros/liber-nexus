package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			return DriverManager.getConnection(url, props);
		} catch (SQLException | ClassNotFoundException e) {
			throw new DbException("Erro ao obter conexão: " + e.getMessage());
		}
	}

	private static Properties loadProperties() {
		try {
			Properties props = new Properties();
			props.load(DB.class.getClassLoader().getResourceAsStream("db.properties"));
			return props;
		} catch (IOException | NullPointerException e) {
			throw new DbException("Erro ao carregar db.properties: " + e.getMessage());
		}
	}
}
