package it.solvingteam.pokeronline.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class ActivateUtenteServlet
 */
@WebServlet("/utente/ActivateUtenteServlet")
public class ActivateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateUtenteServlet() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParam = request.getParameter("id");	
		Long id;
		try {
			id = Long.parseLong(idParam);
		} catch (NumberFormatException | NullPointerException e) { // se ID invalido, aggiorna messaggio di errore
			Utils.addError(request, "ID Utente non valido.");
			goBack(request, response);
			return;
		}
		
		Utente utente = utenteService.attiva(id);
		if (utente == null) {
			Utils.addError(request, "Impossibile accedere all'utente.");
			goBack(request, response);
			return;
		}
		
		request.setAttribute("successMessage", "Attivazione effettuata con successo.");
		goBack(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("utenti", utenteService.elenca());		
		request.getRequestDispatcher("/jsp/utente/utenti.jsp").forward(request, response);
		return;
	}

}
