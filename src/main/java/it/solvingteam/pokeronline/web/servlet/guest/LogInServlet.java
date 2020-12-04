package it.solvingteam.pokeronline.web.servlet.guest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.dto.UtenteDTO;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UtenteDTO utenteDTO = new UtenteDTO(username, password);
		List<String> errors = utenteDTO.errors();
		
		if (!errors.isEmpty()) { // se errori, reindirizza in pagina con messaggi appropriati
			Utils.addErrors(request, errors);
			goBack(request, response);
			return;
		}
		
		Utente instance = utenteDTO.buildModel();
		Utente autenticato = utenteService.autentica(instance);
		
		if (autenticato == null) { // se non esiste, reindirizza in pagina con messaggio appropriato
			Utils.addError(request, "Username e/o password errati.");
			goBack(request, response);
			return;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("utente", autenticato);
		session.setAttribute("adminPriviledges", autenticato.isAdmin());
		session.setAttribute("playerPriviledges", autenticato.isPlayer());
		session.setAttribute("specialPlayerPriviledges", autenticato.isSpecialPlayer());
		
		request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
	}

	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] paramNames = new String[]{"username", "password"};
    	Utils.sendParamsBack(request, paramNames);
    	request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
