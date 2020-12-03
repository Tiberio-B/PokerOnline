package it.solvingteam.pokeronline.web.servlet.utente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.dto.UtenteDTO;
import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class ExecuteSearchTavoloServlet
 */
@WebServlet("/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchUtenteServlet() {
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
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String credito = request.getParameter("credito");
		String exp = request.getParameter("exp");
		String dataRegistrazione = request.getParameter("dataRegistrazione");
		String stato = request.getParameter("stato");
		String ruoloId = request.getParameter("ruoloId");
		
		boolean checkEmptyOrNull = false; // in ricerca, i campi vuoti o null sono ammessi
		UtenteDTO utenteDTO = new UtenteDTO(nome, cognome, username, credito, exp, dataRegistrazione, stato, ruoloId, checkEmptyOrNull);
		
		List<String> errors = utenteDTO.errors();
		if (!errors.isEmpty()) { // se errori validazione, reindirizza in pagina con errori appropriati
			Utils.addErrors(request, errors);
			goBack(request, response);
			return;
		}
		
		Utente instance = utenteDTO.buildModel();
		
		List<Utente> utenti = utenteService.findByExample(instance);
		if (utenti.isEmpty()) {
			Utils.addError(request, "Nessun utente soddisfa i parametri della ricerca.");
			goBack(request, response);
			return;
		}
		
		request.setAttribute("utenti", utenti);
		request.getRequestDispatcher("jsp/utente/utenti.jsp").forward(request, response);
		
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String[] paramNames = new String[]{"tavoloDTO"};
    	Utils.sendParamsBack(request, paramNames);
    	request.getRequestDispatcher("jsp/tavolo/search-tavolo.jsp").forward(request, response);
    }

}
