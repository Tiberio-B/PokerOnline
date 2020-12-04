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
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.ruolo.RuoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class ExecuteUpdateTavoloServlet
 */
@WebServlet("/utente/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateUtenteServlet() {
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
		
		String idParam = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String exp = request.getParameter("exp");
		String credito = request.getParameter("credito");
		String[] idRuoliParams = request.getParameterValues("ruoliId");
		boolean checkRuoli = idRuoliParams != null;
		UtenteDTO utenteDTO = new UtenteDTO(idParam, nome, cognome, username, exp, credito, idRuoliParams, checkRuoli);
		
		List<String> errors = utenteDTO.errors();
		if (!errors.isEmpty()) { // se errori validazione, reindirizza in pagina con errori appropriati
			Utils.addErrors(request, errors);
			goBack(request, response, utenteDTO);
			return;
		}
		
		Utente utenteNew = null;
		try {
			utenteNew = utenteService.aggiorna(utenteDTO, checkRuoli);
		} catch (Exception e) {
			Utils.addError(request, "Impossibile modificare l'utente");
			goBack(request, response, utenteDTO);
			return;
		}
		
		request.setAttribute("successMessage", "Aggiornamento effettuato con successo.");
		request.setAttribute("utenti", utenteService.elenca());
		request.getRequestDispatcher("/jsp/utente/utenti.jsp").forward(request, response);
	}

	private void goBack(HttpServletRequest request, HttpServletResponse response, UtenteDTO utenteDTO) throws ServletException, IOException {
		request.setAttribute("utenteDTO", utenteDTO);
		request.setAttribute("id", utenteDTO.getId());
		request.getRequestDispatcher("/jsp/utente/edit-utente.jsp").forward(request, response);	
	}

}
