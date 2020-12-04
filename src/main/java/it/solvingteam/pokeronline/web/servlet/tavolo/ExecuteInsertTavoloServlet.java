package it.solvingteam.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class ExecuteSearchTavoloServlet
 */
@WebServlet("/tavolo/ExecuteInsertTavoloServlet")
public class ExecuteInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
    
    @Autowired
	private UtenteService utenteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertTavoloServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String puntataMin = request.getParameter("puntataMin");
		String expMin = request.getParameter("expMin");
		String dataCreazione = Utils.dateToString(new Date());
		
		boolean checkEmptyOrNull = true; // in inserimento, i campi vuoti o null non sono ammessi
		TavoloDTO tavoloDTO = new TavoloDTO(nome, puntataMin, expMin, dataCreazione, checkEmptyOrNull);
		
		List<String> errors = tavoloDTO.errors();
		if (!errors.isEmpty()) { // se errori validazione, reindirizza in pagina con errori appropriati
			Utils.addErrors(request, errors);
			request.setAttribute("tavoloDTO", tavoloDTO);
			request.getRequestDispatcher("/jsp/tavolo/insert-tavolo.jsp").forward(request, response);
			return;
		}
		
		Utente utenteInSession = (Utente) request.getSession().getAttribute("utente");
		Utente proprietario = utenteService.carica(utenteInSession.getId());
		
		Tavolo instance = tavoloDTO.buildModel();
		instance.setProprietario(proprietario);
		tavoloService.inserisci(instance);
		request.setAttribute("successMessage", "Inserimento effettuato con successo.");
		
		request.setAttribute("tavoli", tavoloService.elenca());
		
		request.getRequestDispatcher("/jsp/tavolo/tavoli.jsp").forward(request, response);	
	}

}
