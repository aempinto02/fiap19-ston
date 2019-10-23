/**
 * @author André Pinto
 * 
 * Esta classe faz a conexão com o Banco de Dados, tem método para fechar a conexão, insere sugestão do usuário no BD, o administrador
 * pode deletar uma sugestão do usuário, pesquisa o número máximo (ID) de sugestão, pesquisa sugestões por usuário e quantidade,
 * além de pesquisar todas as sugestões
 */

package br.com.fiap.ston.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.ston.beans.Sugestao;
import br.com.fiap.ston.conexao.ConexaoFactory;

public class SugestaoDAO {

	private Connection conexao;

	public SugestaoDAO() {
		try {
			this.conexao = new ConexaoFactory().getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro na conexão com o BD Oracle");
		}
	}
	
	public void fechar() {
		try {
		conexao.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar a conexão da SugestaoDAO");
		}
	}

	public void inserir(Sugestao sugest, String email) {
		try {
			if(pesquisarMax() == -1) {
				throw new IllegalArgumentException();
			}
			int id = pesquisarMax() + 1;
			String sql = "INSERT INTO SUGESTAO VALUES (?,?,?,?)";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setInt(1, id);
			estrutura.setString(2, email);
			estrutura.setString(3, sugest.getSugestao());
			estrutura.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			estrutura.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir Sugestao no BD");
		}
	}

	public int deletar(int id) {
		try {
			String sql = "DELETE FROM SUGESTAO WHERE NR_SUGESTAO = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setInt(1, id);
			int saida = estrutura.executeUpdate();

			return saida;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao deletar sugestão " + id);
		}
		return -1;
	}

	public int pesquisarMax() {
		try {
			String sql = "SELECT MAX(NR_SUGESTAO) AS maxSugest FROM SUGESTAO";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet result = estrutura.executeQuery();
			if(result.next()) {
				return result.getInt("maxSugest");				
			}
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o Máximo do NR_SUGESTAO");
		}
		return -1;
	}

	public ResultSet pesquisarTodas() {
		try {
			String sql = "SELECT * FROM SUGESTAO";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet resultado = estrutura.executeQuery();
			return resultado;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar todas Sugestões");
		}
		return null;
	}

	public ResultSet pesquisarPorUsuario(String email) {
		try {
			String sql = "SELECT SUGESTAO.NR_SUGESTAO, SUGESTAO.DS_SUGESTAO, USUARIO.NM_USUARIO, USUARIO.NM_EMAIL FROM SUGESTAO, USUARIO WHERE SUGESTAO.USUARIO_NM_EMAIL = USUARIO.NM_EMAIL AND USUARIO.NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			ResultSet resultado = estrutura.executeQuery();
			return resultado;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar Sugestao por usuário");
		}
		return null;
	}

	public int pesquisaQtdSugestoesUsuario(String email) {
		try {
			String sql = "SELECT COUNT(*) AS totalSugest FROM SUGESTAO WHERE USUARIO_NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			ResultSet resultado = estrutura.executeQuery();
			int totalSugest;
			if(resultado.next()) {
				totalSugest = resultado.getInt("totalSugest");
				return totalSugest;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o total de sugestões por usuário");
		}
		return 0;
	}
}
