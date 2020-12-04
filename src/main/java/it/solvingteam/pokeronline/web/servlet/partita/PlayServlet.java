package it.solvingteam.pokeronline.web.servlet.partita;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class PlayServlet
 */
@WebServlet("/PlayServlet")
public class PlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente utenteInSession = (Utente) request.getSession().getAttribute("utente");
		Integer credito = utenteInSession.getCredito();
		
		int vincita = (int) Math.random()*1000;
		
		if (Math.random() < 0.5) { vincita = -vincita; }	
		int totale = credito + vincita;
		if (totale < 0) {
			credito = 0;
			Utils.addError(request, "Credito esaurito");
		} else {
			credito = totale;
		}
		
		utenteInSession.setCredito(credito);
		utenteService.aggiorna(utenteInSession);
		request.getRequestDispatcher("/jsp/partita/game.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
