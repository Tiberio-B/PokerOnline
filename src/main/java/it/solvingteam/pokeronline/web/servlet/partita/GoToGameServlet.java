package it.solvingteam.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class GoToLastGameServlet
 */
@WebServlet("/partita/GoToGameServlet")
public class GoToGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToGameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Utils.validateLongParam(request, "id", true);
		if (id == null) {
			goBack(request, response);
			return;
		}
		
		Tavolo partita = tavoloService.caricaConGiocatori(id);
		if (partita == null) {
			Utils.addError(request, "Impossibile accedere alla partita.");
			goBack(request, response);
			return;
		}
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente.getExp() < partita.getExpMin()) {
			Utils.addError(request, "Non si dispone dell'esperienza sufficiente per accedere alla partita.");
			goBack(request, response);
		}
		
		if (utente.getCredito() < partita.getPuntataMin()) {
			Utils.addError(request, "Non si dispone del credito sufficiente per accedere alla partita.");
			goBack(request, response);
		}
		
		if (!partita.getGiocatori().contains(utente)) {
			tavoloService.aggiungiGiocatori(partita, utente);
		}
			
		request.setAttribute("partita", partita);
		request.getRequestDispatcher("/jsp/partita/game.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/partita/play-management.jsp").forward(request, response);
		return;
	}

}
