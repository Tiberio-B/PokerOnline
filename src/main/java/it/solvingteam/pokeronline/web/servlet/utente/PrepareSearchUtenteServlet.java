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

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.ruolo.RuoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ListUtentiServlet
 */
@WebServlet("/PrepareSearchUtenteServlet")
public class PrepareSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareSearchUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
	private RuoloService ruoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente.Stato[] values = Utente.Stato.values();
		String[] stati = new String[values.length];
		for (int i=0; i < stati.length; i++) {
			stati[i] = values[i].name();
		}
		request.setAttribute("stati", stati);
		request.setAttribute("ruoli", ruoloService.elenca());
		request.getRequestDispatcher("jsp/utente/search-utente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
