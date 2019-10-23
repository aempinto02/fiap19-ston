/**
 * @author André Pinto
 * 
 * Esta classe faz a conexão com o Banco de Dados, e tem método para fechar a conexão, insere usuário no Banco de Dados,
 * pesquisa todos os usuários, pesquisa os administradores, pesquisa por email um único usuário, altera o status de administrador
 * de um único usuário, e também faz o login do usuário cadastrado
 */

package br.com.fiap.ston.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.ston.beans.Usuario;
import br.com.fiap.ston.conexao.ConexaoFactory;

public class UsuarioDAO {

	private Connection conexao;

	public UsuarioDAO() {
		try {
			this.conexao = new ConexaoFactory().getConnection();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro de conexão com o BD");
		}
	}

	public void fechar() {
		try {
			conexao.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar a Conexão da UsuarioDAO");
		}
	}

	public void inserir(Usuario user) {
		try {
			if(this.pesquisarPorEmail(user.getEmail()) != null) {
				throw new IllegalArgumentException();
			}
			String sql = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?)";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, user.getEmail());
			estrutura.setString(2, user.getNome());
			estrutura.setString(3, user.getTelefone());
			estrutura.setString(4, user.getCelular());
			estrutura.setString(5, String.valueOf(user.getAdmin()));
			estrutura.setString(6, user.getSenha());
			estrutura.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir PerguntaNaoRespondida no BD");
		}
	}

	public ResultSet pesquisarTodas() {
		try {
			String sql = "SELECT * FROM USUARIO";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet resultado = estrutura.executeQuery();
			return resultado;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar todos Usuários");
		}
		return null;
	}

	public ResultSet pesquisarPorAdmin() {
		try {
			String sql = "SELECT * FROM USUARIO WHERE ST_ADMIN = '1'";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet resultado = estrutura.executeQuery();
			return resultado;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar todos os Admins");
		}
		return null;
	}

	public void mudarAdmin(String email, char status) {
		try {
			String sql = "UPDATE USUARIO SET ST_ADMIN = ? WHERE NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, String.valueOf(status));
			estrutura.setString(2, email);
			estrutura.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao alterar o Status Admin do Usuário");
		}
	}

	public Usuario pesquisarPorEmail(String email) {
		try {
			if(email == null) {
				return null;
			}
			Usuario user = new Usuario();
			String sql = "SELECT * FROM USUARIO WHERE NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			ResultSet resultado = estrutura.executeQuery();
			if(resultado.next()) {
				user.setNome(resultado.getString("NM_USUARIO"));
				user.setEmail(resultado.getString("NM_EMAIL"));
				user.setSenha(resultado.getString("NM_SENHA"));
				user.setAdmin(resultado.getString("ST_ADMIN").charAt(0));

				return user;
			}
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar por email de usuário");
		}
		return null;
	}

	public boolean login(String email, String senha) {
		Usuario user = this.pesquisarPorEmail(email);
		if(user != null && user.getSenha().equals(senha)) {
			return true;
		}
		return false;
	}
}
