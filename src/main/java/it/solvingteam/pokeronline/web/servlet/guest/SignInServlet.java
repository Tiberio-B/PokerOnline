package it.solvingteam.pokeronline.web.servlet.guest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String logInServletPath = "LogInServlet";
		final String insertUtenteServletPath = "PrepareInsertUtenteServlet";
		
		switch (request.getParameter("action")) {
		
			case logInServletPath: {
				request.getRequestDispatcher(logInServletPath).forward(request, response);
				return;
			}
			case insertUtenteServletPath: {
				request.getRequestDispatcher(insertUtenteServletPath).forward(request, response);
				return;
			}
			default: {
				Utils.addError(request, "Destinazione specificata non valida.");
				request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
