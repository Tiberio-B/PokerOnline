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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.dto.UtenteDTO;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.utils.Utils;

/**
 * Servlet implementation class ExecuteInsertUtenteServlet
 */
@WebServlet("/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String[] paramNames = new String[]{"nome", "cognome", "username", "password"};
    	Utils.sendParamsBack(request, paramNames);
    	request.getRequestDispatcher("jsp/guest/signup.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UtenteDTO utenteDTO = new UtenteDTO(nome, cognome, username, password);
		List<String> errors = utenteDTO.errors();
		
		boolean usernameEsistente = utenteService.usernameEsistente(username);
		boolean erroriValidazione = !errors.isEmpty();
		
		if (usernameEsistente || erroriValidazione) { // se errori, reindirizza in pagina con messaggi appropriati
			if (usernameEsistente) {
				Utils.addError(request, "L'username specificato è già in uso.");
			}
			if (erroriValidazione) {
				Utils.addErrors(request, errors);
			}
			goBack(request, response);
			return;
		}
		
		Utente instance = utenteDTO.buildModel();
		instance.setDataRegistrazione(new Date());
		instance.setCredito(0);
		instance.setExp(0);
		utenteService.inserisci(instance);
		request.setAttribute("successMessage", "Registrazione avvenuta con successo. Attendi l'attivazione dell'utenza prima di effettuare l'accesso.");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
