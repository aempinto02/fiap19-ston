/**
 * @author André Pinto
 * 
 * Esta classe tem por função conectar com o Banco de Dados, tem método para fechar a conexão, para inserir pergunta não respondida
 * pelo chatbot, pesquisar o número máximo do ID de perguntas, ler todas as perguntas não respondidas, ler todas as perguntas não
 * respondidas por usuário (email), e ler a quantidade de perguntas não respondidas por usuário (email)
 */

package br.com.fiap.ston.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.ston.beans.PerguntaNaoRespondida;
import br.com.fiap.ston.conexao.ConexaoFactory;

public class PerguntaNaoRespondidaDAO {

	private Connection conexao;

	public PerguntaNaoRespondidaDAO() {
		try {
			this.conexao = new ConexaoFactory().getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro de conexão com BD Oracle");
		}
	}
	
	public void fechar() {
		try {
			conexao.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar a Conexão da PerguntaNaoRespondidaDAO");
		}
	}

	public void inserir(PerguntaNaoRespondida perg, String email) {
		try {
			if(pesquisarMax() == -1) {
				throw new IllegalArgumentException();
			}
			int id = pesquisarMax() + 1;
			String sql = "INSERT INTO PERGUNTA_NAO_RESPONDIDA VALUES (?,?,?)";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setInt(1, id);
			estrutura.setString(2, email);
			estrutura.setString(3, perg.getPergunta());
			estrutura.execute();
			conexao.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir PerguntaNaoRespondida no BD");
		}
	}

	public int pesquisarMax() {
		try {
			String sql = "SELECT MAX(NR_PERGUNTA) AS maxPerg FROM PERGUNTA_NAO_RESPONDIDA";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet result = estrutura.executeQuery();
			if(result.next()) {
				return result.getInt("maxPerg");
			}
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o Máximo do NR_PERGUNTA");
		}
		return -1;
	}

	public ResultSet pesquisarTodas() {
		try {
			String sql = "SELECT * FROM PERGUNTA_NAO_RESPONDIDA";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet resultado = estrutura.executeQuery();
			return resultado;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar todas PERGUNTAS_NAO_RESPONDIDAS");
		}
		return null;
	}
	
	public ResultSet pesquisarPorUsuario(String email) {
		try {
			String sql = "SELECT PERGUNTA_NAO_RESPONDIDA.DS_PERGUNTA, USUARIO.NM_USUARIO, USUARIO.NM_EMAIL FROM PERGUNTA_NAO_RESPONDIDA, USUARIO WHERE PERGUNTA_NAO_RESPONDIDA.USUARIO_NM_EMAIL = USUARIO.NM_EMAIL AND USUARIO.NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			ResultSet resultado = estrutura.executeQuery();
			return resultado;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar PerguntaNaoRespondida por usuário");
		}
		return null;
	}
	
	public int pesquisaQtdPerguntasUsuario(String email) {
		try {
			String sql = "SELECT COUNT(*) AS totalPerg FROM PERGUNTA_NAO_RESPONDIDA WHERE USUARIO_NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			ResultSet resultado = estrutura.executeQuery();
			int totalSugest;
			if(resultado.next()) {
				totalSugest = resultado.getInt("totalPerg");
				return totalSugest;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o total de Pergunta_nao_respondida por usuário");
		}
		return 0;
	}
}
