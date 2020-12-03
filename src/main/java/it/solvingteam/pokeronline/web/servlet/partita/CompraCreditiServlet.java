package it.solvingteam.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class CompraCreditiServlet
 */
@WebServlet("/CompraCreditiServlet")
public class CompraCreditiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraCreditiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
   	private UtenteService utenteService;

   	@Override
   	public void init(ServletConfig config) throws ServletException {
   		super.init(config);
   		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String creditiParam = request.getParameter("crediti");
		if (creditiParam == null) {
			Utils.addError(request, "Inserire quantità crediti.");
			request.getRequestDispatcher("jsp/partita/play-management.jsp").forward(request, response);
			return;
		}
		int crediti = 0;
		try {
			crediti = Integer.valueOf(creditiParam);
		} catch (NumberFormatException e) {
			Utils.addError(request, "Crediti inseriti non validi.");
			request.getRequestDispatcher("jsp/partita/play-management.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		utente = utenteService.aggiungiCredito(utente, crediti);
		session.setAttribute("utente", utente);
		
		request.getRequestDispatcher("jsp/partita/play-management.jsp").forward(request, response);
	}

}
