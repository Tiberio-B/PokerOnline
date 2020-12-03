package it.solvingteam.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class GoToLastGameServlet
 */
@WebServlet("/GoToGameServlet")
public class GoToGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToGameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
	private TavoloService tavoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParam = request.getParameter("id");
		long id;
		try {
			id = Long.valueOf(idParam);
		} catch (NumberFormatException e) {
			goBack(request, response);
			return;
		}
		
		Tavolo partita = tavoloService.carica(id);
		if (partita == null) {
			goBack(request, response);
			return;
		}
		request.setAttribute("partita", partita);
		request.getRequestDispatcher("jsp/partita/game.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.addError(request, "Impossibile accedere alla partita.");
		request.getRequestDispatcher("jsp/partita/play-management.jsp").forward(request, response);
		return;
	}

}
