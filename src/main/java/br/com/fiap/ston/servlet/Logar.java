/**
 * @author André Pinto
 * 
 * Servlet implementa a funcionalidade de logar no sistema (com duas opções: usuário comum e administrador)
 */

package br.com.fiap.ston.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.ston.beans.Usuario;
import br.com.fiap.ston.dao.UsuarioDAO;

@WebServlet("/Logar")
public class Logar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logar() {
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
		String retorno = request.getParameter("jspname");
		user = userDao.pesquisarPorEmail(request.getParameter("email"));
		if(userDao.login(user.getEmail(), request.getParameter("senha"))) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario",user.getNome());
			session.setAttribute("email",user.getEmail());
			session.setAttribute("admin",user.getAdmin());
			session.setAttribute("opcao", "T");
			if(user.getAdmin() == '1') {
				userDao.fechar();
				response.sendRedirect("admin/admin.jsp");
			}
			else {
				userDao.fechar();
				response.sendRedirect("index.jsp");
			}
		}
		else {
			request.setAttribute("usuario", null);
            response.sendRedirect(retorno);
		}
		
		userDao.fechar();
	}

}
