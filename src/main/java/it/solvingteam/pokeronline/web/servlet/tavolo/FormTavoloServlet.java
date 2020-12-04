package it.solvingteam.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class FormTavoloServlet
 */
@WebServlet("/tavolo/FormTavoloServlet")
public class FormTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String searchTavoloServletPath = "ExecuteSearchTavoloServlet";
		final String insertTavoloServletPath = "PrepareInsertTavoloServlet";
		
		switch (request.getParameter("action")) {
		
			case searchTavoloServletPath: {
				request.getRequestDispatcher(searchTavoloServletPath).forward(request, response);
				break;
			}
			case insertTavoloServletPath: {
				request.getRequestDispatcher(insertTavoloServletPath).forward(request, response);
				break;
			}
			default: {
				Utils.addError(request, "Destinazione specificata non valida.");
				request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
				break;
			}
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
