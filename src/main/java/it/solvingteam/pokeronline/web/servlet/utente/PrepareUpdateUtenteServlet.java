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
import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.ruolo.RuoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class PrepareUpdateTavoloServlet
 */
@WebServlet("/utente/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private RuoloService ruoloService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateUtenteServlet() {
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
		Long id = Utils.validateLongParam(request, "id", true);
		if (id == null) {
			goBack(request, response);
			return;
		}
		
		Utente utente = utenteService.carica(id);
		if (utente == null) {
			Utils.addError(request, "Impossibile accedere all'utente.");
			goBack(request, response);
			return;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("utenteDTO", new UtenteDTO().buildDtoFrom(utente));
		// request.setAttribute("stati", Utils.toStringArray(Utente.Stato.values()));
		request.setAttribute("ruoli", ruoloService.elenca());
		request.getRequestDispatcher("/jsp/utente/edit-utente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("PrepareSearchUtenteServlet").forward(request, response);
	}

}
