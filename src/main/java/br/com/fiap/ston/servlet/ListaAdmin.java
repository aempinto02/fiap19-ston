/**
 * @author André Pinto
 * 
 * Servlet implementa a listagem na página do administrador
 */

package br.com.fiap.ston.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListaAdmin")
public class ListaAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("option").equals("T")) {
			request.getSession(false).setAttribute("opcao", "T");
		}
		else if(request.getParameter("option").equals("U")) {
			request.getSession(false).setAttribute("opcao", "U");
		}
		else if(request.getParameter("option").equals("A")) {
			request.getSession(false).setAttribute("opcao", "A");
		}
		response.sendRedirect("admin/admin.jsp");
	}

}
