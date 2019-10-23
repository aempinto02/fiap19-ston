package br.com.fiap.ston.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoFactory {

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm82720", "fiap");
	}

	public static boolean testaConexao() {
		Connection c = null;
		try {
			c = new ConexaoFactory().getConnection();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Deu erro na conexão com o BD");
		}
		finally {
			try {
				c.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro ao fechar a conexão com o BD");
			}
		}
		return false;
	}
}
