/**
 * @author André Pinto
 * 
 * Servlet implementa a alteração de Status do usuário comum para administrador, e o contrário
 */

package br.com.fiap.ston.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ston.dao.UsuarioDAO;

@WebServlet("/AlteraAdmin")
public class AlteraAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if(request.getSession(false).getAttribute("admin").equals('1')) {
			UsuarioDAO userDao = new UsuarioDAO();
			userDao.mudarAdmin(request.getParameter("email-input-modal").toString(), request.getParameter("admin-input-modal").toString().charAt(0));
			userDao.fechar();
			request.getSession(false).setAttribute("sucesso", true);
			response.sendRedirect("admin/admin.jsp");
		}
		else {
			response.sendRedirect("index.jsp");
		}
	}

}
