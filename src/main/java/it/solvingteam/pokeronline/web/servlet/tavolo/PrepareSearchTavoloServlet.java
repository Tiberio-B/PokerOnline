package it.solvingteam.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.pokeronline.model.Utente;

/**
 * Servlet implementation class PrepareSearchTavoloServlet
 */
@WebServlet("/tavolo/PrepareSearchTavoloServlet")
public class PrepareSearchTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareSearchTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proprietario = null;
		try {
			proprietario = ((Utente) request.getSession().getAttribute("utente")).getUsername();
		} catch(NullPointerException e) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		request.setAttribute("proprietario", proprietario);
		request.getRequestDispatcher("/jsp/tavolo/search-tavolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
