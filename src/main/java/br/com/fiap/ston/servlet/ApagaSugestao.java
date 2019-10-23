/**
 * @author André Pinto
 * 
 * Servlet implementa a funcionalidade de apagar a sugestão pelo administrador
 */

package br.com.fiap.ston.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ston.dao.SugestaoDAO;

@WebServlet("/ApagaSugestao")
public class ApagaSugestao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApagaSugestao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		SugestaoDAO sugestDao = new SugestaoDAO();
		sugestDao.deletar(Integer.parseInt(request.getParameter("numero-sugestao")));
		request.getSession(false).setAttribute("sucesso-sugest", true);
		sugestDao.fechar();
		response.sendRedirect("admin/sugestoes-admin.jsp");
	}

}
