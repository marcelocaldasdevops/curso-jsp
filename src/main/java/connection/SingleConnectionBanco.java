package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String url = "jdbc:postgresql://localhost:5432/posjava?autoReconnect=true";
	private static String password = "fpf@1212";
	private static String user = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}
	public SingleConnectionBanco() {
		conectar();
	}
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectado com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
