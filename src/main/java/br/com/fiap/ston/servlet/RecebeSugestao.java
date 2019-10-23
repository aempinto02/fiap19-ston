/**
 * @author André Pinto
 * 
 * Servlet implementa a funcionalidade de inserir a sugestão do usuário no Banco de Dados
 */

package br.com.fiap.ston.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ston.beans.Sugestao;
import br.com.fiap.ston.bo.SugestaoBO;
import br.com.fiap.ston.conexao.ConexaoFactory;
import br.com.fiap.ston.dao.SugestaoDAO;

@WebServlet("/RecebeSugestao")
public class RecebeSugestao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecebeSugestao() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Sugestao sugest = new Sugestao();
		SugestaoDAO sugestDao = new SugestaoDAO();
		sugest.setSugestao(request.getParameter("sugestao"));

		if(SugestaoBO.incluir(sugest.getSugestao()) && ConexaoFactory.testaConexao()) {
			sugestDao.inserir(sugest, request.getSession(false).getAttribute("email").toString());
			response.sendRedirect("sugestaoSuccess.jsp");
		}
		else {
			response.sendRedirect("sugestao.jsp");
		}
		
		sugestDao.fechar();
	}

}
