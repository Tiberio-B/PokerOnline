package it.solvingteam.pokeronline.web.servlet.tavolo;

import java.io.IOException;

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
 * Servlet implementation class PrepareUpdateTavoloServlet
 */
@WebServlet("/tavolo/PrepareUpdateTavoloServlet")
public class PrepareUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Autowired
	private TavoloService tavoloService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateTavoloServlet() {
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
		
		Tavolo tavolo = tavoloService.caricaConGiocatori(id);
		if (tavolo == null) {
			Utils.addError(request, "Impossibile accedere al tavolo.");
			goBack(request, response);
			return;
		}
		
		if (!tavolo.getGiocatori().isEmpty()) {
			Utils.addError(request, "Impossibile modificare un tavolo che presenta giocatori.");
			goBack(request, response);
			return;
		}
		
		request.setAttribute("idTavolo", id);
		request.setAttribute("tavoloDTO", new TavoloDTO().buildDtoFrom(tavolo));
		request.setAttribute("proprietario", tavolo.getProprietario().getUsername());
		request.getRequestDispatcher("/jsp/tavolo/edit-tavolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("PrepareSearchTavoloServlet").forward(request, response);
	}

}
