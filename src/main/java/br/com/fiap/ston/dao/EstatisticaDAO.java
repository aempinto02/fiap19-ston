/**
 * @author André Pinto
 * 
 * Esta classe conecta com o Banco de Dados, tem método para fechar a conexão, e métodos para inserir Estatística, pesquisar por email,
 * pesquisar o número máximo para adicionar Estatística, aumentar o número de respostas por intenção e o número total de respostas,
 * e pesquisar o total de perguntas feitas
 */

package br.com.fiap.ston.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.ston.conexao.ConexaoFactory;

public class EstatisticaDAO {

	private Connection conexao;

	public EstatisticaDAO() {
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
			System.out.println("Erro ao fechar a Conexão da UsuarioDAO");
		}
	}

	public void inserir(String email) {
		try {
			if(pesquisarMax() == -1) {
				throw new IllegalArgumentException();
			}
			if(pesquisaTotalPerguntasUsuario(email) == 0) {
				int id = pesquisarMax() + 1;
				String sql = "INSERT INTO ESTATISTICA VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setInt(1, id);
				estrutura.setString(2, email);
				estrutura.setInt(3, 0);
				estrutura.setInt(4, 0);
				estrutura.setInt(5, 0);
				estrutura.setInt(6, 0);
				estrutura.setInt(7, 0);
				estrutura.setInt(8, 0);
				estrutura.setInt(9, 0);
				estrutura.setInt(10, 0);
				estrutura.setInt(11, 0);
				estrutura.setInt(12, 0);
				estrutura.setInt(13, 0);
				estrutura.setInt(14, 0);
				estrutura.setInt(15, 0);
				estrutura.setInt(16, 0);
				estrutura.setInt(17, 0);
				estrutura.setInt(18, 0);
				estrutura.execute();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir ESTATISTICA no BD");
		}
	}
	
	public void aumentarNrRespostas(int intencao, String email) {
		try {
			String sql = "UPDATE ESTATISTICA SET NR_TOTALRESPOSTAS = ? + 1 WHERE USUARIO_NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setInt(1, this.pesquisaTotalPerguntasUsuario(email));
			estrutura.setString(2, email);
			estrutura.executeUpdate();
			String resp = "";
			
			if(intencao == 1) {
				resp = "NR_APLICACAO";
			}
			else if(intencao == 2) {
				resp = "NR_ATOS";
				
			}
			else if(intencao == 3) {
				resp = "NR_CONTANDO";
				
			}
			else if(intencao == 4) {
				resp = "NR_CURSO";
				
			}
			else if(intencao == 5) {
				resp = "NR_ERROS";
				
			}
			else if(intencao == 6) {
				resp = "NR_EXEMPLOS";
				
			}
			else if(intencao == 7) {
				resp = "NR_GAME";
				
			}
			else if(intencao == 8) {
				resp = "NR_JORNADADOHEROI";
				
			}
			else if(intencao == 9) {
				resp = "NR_PERSONAGENS";
				
			}
			else if(intencao == 10) {
				resp = "NR_PROFISSIONAIS";
				
			}
			else if(intencao == 11) {
				resp = "NR_PROTAGONISTA";
				
			}
			else if(intencao == 12) {
				resp = "NR_QUEBRANDOREGRA";
				
			}
			else if(intencao == 13) {
				resp = "NR_STORYTELLING";
				
			}
			else if(intencao == 14) {
				resp = "NR_VILAO";
				
			}
			else if(intencao == 15) {
				resp = "NR_NENHUMAINTENCAO";
			}
			sql = "UPDATE ESTATISTICA SET " + resp + " = " + this.pesquisaPorIntencaoUsuarioTotal(intencao, email) + " + 1 WHERE USUARIO_NM_EMAIL = ?";
			estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			estrutura.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao adicionar UM ao NR_TOTALRESPOSTAS");
		}
	}

	public int pesquisarMax() {
		try {
			String sql = "SELECT MAX(NR_ESTATISTICA) AS maxEstat FROM ESTATISTICA";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet result = estrutura.executeQuery();
			if(result.next()) {
				return result.getInt("maxEstat");
			}
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o Máximo de ESTATISTICA");
		}
		return -1;
	}

	public int pesquisaTotalPerguntasUsuario(String email) {
		try {
			String sql = "SELECT NR_TOTALRESPOSTAS FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			estrutura.setString(1, email);
			ResultSet resultado = estrutura.executeQuery();
			int totalSugest;
			if(resultado.next()) {
				totalSugest = resultado.getInt("NR_TOTALRESPOSTAS");
				return totalSugest;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o total de Respostas por usuário");
		}
		return 0;
	}
	
	public int pesquisaTotalPerguntas() {
		try {
			String sql = "SELECT SUM(NR_TOTALRESPOSTAS) TOTAL FROM ESTATISTICA";
			PreparedStatement estrutura = conexao.prepareStatement(sql);
			ResultSet resultado = estrutura.executeQuery();
			int totalSugest;
			if(resultado.next()) {
				totalSugest = resultado.getInt("TOTAL");
				return totalSugest;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar o total de Respostas por usuário");
		}
		return 0;
	}

	public int pesquisaPorIntencaoSomaTotal(int intencao) {
		if(intencao == 1) {
			try {
				String sql = "SELECT SUM(NR_APLICACAO) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Aplicação");
			}
		}
		
		else if(intencao == 2) {
			try {
				String sql = "SELECT SUM(NR_ATOS) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Atos");
			}
		}
		
		else if(intencao == 3) {
			try {
				String sql = "SELECT SUM(NR_CONTANDO) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Contando");
			}
		}
		
		else if(intencao == 4) {
			try {
				String sql = "SELECT SUM(NR_CURSO) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Curso");
			}
		}
		
		else if(intencao == 5) {
			try {
				String sql = "SELECT SUM(NR_ERROS) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Erros");
			}
		}
		
		else if(intencao == 6) {
			try {
				String sql = "SELECT SUM(NR_EXEMPLOS) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Exemplos");
			}
		}
		
		else if(intencao == 7) {
			try {
				String sql = "SELECT SUM(NR_GAME) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Game");
			}
		}
		
		else if(intencao == 8) {
			try {
				String sql = "SELECT SUM(NR_JORNADADOHEROI) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção JornadaDoHerói");
			}
		}
		
		else if(intencao == 9) {
			try {
				String sql = "SELECT SUM(NR_PERSONAGENS) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Personagens");
			}
		}
		
		else if(intencao == 10) {
			try {
				String sql = "SELECT SUM(NR_PROFISSIONAIS) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Profissionais");
			}
		}
		
		else if(intencao == 11) {
			try {
				String sql = "SELECT SUM(NR_PROTAGONISTA) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Protagonista");
			}
		}
		
		else if(intencao == 12) {
			try {
				String sql = "SELECT SUM(NR_QUEBRANDOREGRA) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção QuebrandoRegra");
			}
		}
		
		else if(intencao == 13) {
			try {
				String sql = "SELECT SUM(NR_STORYTELLING) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Storytelling");
			}
		}
		
		else if(intencao == 14) {
			try {
				String sql = "SELECT SUM(NR_VILAO) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Vilão");
			}
		}
		
		else if(intencao == 15) {
			try {
				String sql = "SELECT SUM(NR_NENHUMAINTENCAO) TOTAL FROM ESTATISTICA";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas de NenhumaIntenção");
			}
		}
		return 0;
	}
	
	public int pesquisaPorIntencaoUsuarioTotal(int intencao, String email) {
		if(intencao == 1) {
			try {
				String sql = "SELECT NR_APLICACAO TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Aplicação");
			}
		}
		
		else if(intencao == 2) {
			try {
				String sql = "SELECT NR_ATOS TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Atos");
			}
		}
		
		else if(intencao == 3) {
			try {
				String sql = "SELECT NR_CONTANDO TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Contando");
			}
		}
		
		else if(intencao == 4) {
			try {
				String sql = "SELECT NR_CURSO TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Curso");
			}
		}
		
		else if(intencao == 5) {
			try {
				String sql = "SELECT NR_ERROS TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Erros");
			}
		}
		
		else if(intencao == 6) {
			try {
				String sql = "SELECT NR_EXEMPLOS TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Exemplos");
			}
		}
		
		else if(intencao == 7) {
			try {
				String sql = "SELECT NR_GAME TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Game");
			}
		}
		
		else if(intencao == 8) {
			try {
				String sql = "SELECT NR_JORNADADOHEROI TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção JornadaDoHerói");
			}
		}
		
		else if(intencao == 9) {
			try {
				String sql = "SELECT NR_PERSONAGENS TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Personagens");
			}
		}
		
		else if(intencao == 10) {
			try {
				String sql = "SELECT NR_PROFISSIONAIS TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Profissionais");
			}
		}
		
		else if(intencao == 11) {
			try {
				String sql = "SELECT NR_PROTAGONISTA TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Protagonista");
			}
		}
		
		else if(intencao == 12) {
			try {
				String sql = "SELECT NR_QUEBRANDOREGRA TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção QuebrandoRegra");
			}
		}
		
		else if(intencao == 13) {
			try {
				String sql = "SELECT NR_STORYTELLING TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Storytelling");
			}
		}
		
		else if(intencao == 14) {
			try {
				String sql = "SELECT NR_VILAO TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas da intenção Vilão");
			}
		}
		
		else if(intencao == 15) {
			try {
				String sql = "SELECT NR_NENHUMAINTENCAO TOTAL FROM ESTATISTICA WHERE USUARIO_NM_EMAIL = ?";
				PreparedStatement estrutura = conexao.prepareStatement(sql);
				estrutura.setString(1, email);
				ResultSet resultado = estrutura.executeQuery();
				if(resultado.next()) {
					return resultado.getInt("TOTAL");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Erro na soma das respostas de NenhumaIntenção");
			}
		}
		return 0;
	}
}
