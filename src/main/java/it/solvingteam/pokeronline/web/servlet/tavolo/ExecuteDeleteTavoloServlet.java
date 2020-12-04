package it.solvingteam.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.dto.TavoloDTO;
import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class ExecuteUpdateTavoloServlet
 */
@WebServlet("/tavolo/ExecuteDeleteTavoloServlet")
public class ExecuteDeleteTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParam = request.getParameter("id");
		
		Long id;
		try {
			id = Long.parseLong(idParam);
		} catch (NumberFormatException | NullPointerException e) { // se ID invalido, aggiorna messaggio di errore
			Utils.addError(request, "ID Tavolo non valido.");
			goBack(request, response);
			return;
		}
		
		Tavolo tavolo = tavoloService.caricaConGiocatori(id);
		if (tavolo == null) {
			Utils.addError(request, "Impossibile accedere al tavolo.");
			goBack(request, response);
			return;
		}
		
		if (!tavolo.getGiocatori().isEmpty()) {
			Utils.addError(request, "Impossibile eliminare un tavolo che presenta giocatori.");
			goBack(request, response);
			return;
		}
		
		tavoloService.rimuovi(tavolo);
		request.setAttribute("successMessage", "Rimozione effettuata con successo.");
		
		request.setAttribute("tavoli", tavoloService.elenca());
		request.getRequestDispatcher("/jsp/tavolo/tavoli.jsp").forward(request, response);
	}

	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tavoli", tavoloService.elenca());
		request.getRequestDispatcher("/jsp/tavolo/tavoli.jsp").forward(request, response);	
	}

}
