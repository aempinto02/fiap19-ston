/**
 * @author André Pinto
 * 
 * Servlet implementa a funcionalidade de cadastrar novo usuário no sistema
 */

package br.com.fiap.ston.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ston.beans.Usuario;
import br.com.fiap.ston.bo.UsuarioBO;
import br.com.fiap.ston.conexao.ConexaoFactory;
import br.com.fiap.ston.dao.UsuarioDAO;

@WebServlet("/Cadastrar")
public class Cadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cadastrar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Usuario user = new Usuario();
		UsuarioDAO userDao = new UsuarioDAO();

		if(userDao.pesquisarPorEmail(request.getParameter("email")) != null) {
			request.setAttribute("usuarioIgual", '1');
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
		}
		else {
			user.setNome(request.getParameter("nome"));
			user.setEmail(request.getParameter("email"));
			user.setTelefone(request.getParameter("tel"));
			user.setCelular(request.getParameter("cel"));
			user.setSenha(request.getParameter("senha"));
			user.setAdmin('0');
		}

		if(UsuarioBO.incluir(user) && ConexaoFactory.testaConexao()) {
			userDao.inserir(user);
			response.sendRedirect("cadastroSuccess.jsp");
		}
		else {
			response.sendRedirect("cadastro.jsp");
		}
		
		userDao.fechar();
	}

}
