package it.solvingteam.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class PrepareSearchTavoloServlet
 */
@WebServlet("/PrepareSearchPartitaServlet")
public class PrepareSearchPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareSearchPartitaServlet() {
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
		
		request.setAttribute("utenti", utenteService.elenca());
		request.getRequestDispatcher("jsp/utente/search-partita.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
