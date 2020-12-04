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
@WebServlet("/tavolo/ExecuteUpdateTavoloServlet")
public class ExecuteUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateTavoloServlet() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean checkEmptyOrNull = true; // in modifica, i campi vuoti o null non sono ammessi
		String idParam = request.getParameter("idTavolo");
		String nome = request.getParameter("nome");
		String puntataMin = request.getParameter("puntataMin");
		String expMin = request.getParameter("expMin");
		String dataCreazione = request.getParameter("dataCreazione");
		TavoloDTO tavoloDTO = new TavoloDTO(nome, puntataMin, expMin, dataCreazione, checkEmptyOrNull);
		String proprietario = request.getParameter("proprietario"); // non richiede validazione poiché successivamente viene sovrascritto col dato caricato dal database
		
		Long id;
		try {
			id = Long.parseLong(idParam);
		} catch (NumberFormatException | NullPointerException e) { // se ID invalido, aggiorna messaggio di errore
			Utils.addError(request, "ID Tavolo non valido.");
			goBack(request, response, tavoloDTO, idParam, proprietario);
			return;
		}
		
		Tavolo tavoloOld = tavoloService.caricaConGiocatori(id);
		if (tavoloOld == null) {
			Utils.addError(request, "Impossibile accedere al tavolo.");
			goBack(request, response, tavoloDTO, idParam, proprietario);
			return;
		}
		
		if (!tavoloOld.getGiocatori().isEmpty()) {
			Utils.addError(request, "Impossibile modificare un tavolo che presenta giocatori.");
			goBack(request, response, tavoloDTO, tavoloOld.getId(), tavoloOld.getProprietario().getUsername());
			return;
		}
		
		List<String> errors = tavoloDTO.errors();
		if (!errors.isEmpty()) { // se errori validazione, reindirizza in pagina con errori appropriati
			Utils.addErrors(request, errors);
			goBack(request, response, tavoloDTO, tavoloOld.getId(), tavoloOld.getProprietario().getUsername());
			return;
		}
		
		Tavolo tavoloNew = tavoloDTO.buildModel();
		tavoloNew.setId(tavoloOld.getId());
		tavoloNew.setProprietario(tavoloOld.getProprietario());
		tavoloNew.setDataCreazione(tavoloOld.getDataCreazione());
		tavoloNew.setGiocatori(tavoloOld.getGiocatori());
		tavoloService.aggiorna(tavoloNew);
		request.setAttribute("successMessage", "Aggiornamento effettuato con successo.");
		
		request.setAttribute("tavoli", tavoloService.elenca());
		
		request.getRequestDispatcher("/jsp/tavolo/tavoli.jsp").forward(request, response);
	}

	private void goBack(HttpServletRequest request, HttpServletResponse response, TavoloDTO tavoloDTO, Object idTavolo, String proprietario) throws ServletException, IOException {
		request.setAttribute("tavoloDTO", tavoloDTO);
		request.setAttribute("idTavolo", idTavolo);
		request.setAttribute("proprietario", proprietario);
		request.getRequestDispatcher("/jsp/tavolo/edit-tavolo.jsp").forward(request, response);	
	}

}
