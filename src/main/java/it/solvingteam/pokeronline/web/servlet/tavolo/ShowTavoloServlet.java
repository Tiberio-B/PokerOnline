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
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class ShowTavoloServlet
 */
@WebServlet("/tavolo/ShowTavoloServlet")
public class ShowTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTavoloServlet() {
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
		
		Long id = Utils.validateLongParam(request, "id", true);
		if (id == null) {
			goBack(request, response);
			return;
		}
		
		Tavolo tavolo = tavoloService.carica(id);
		if (tavolo == null) {
			Utils.addError(request, "Impossibile accedere al tavolo.");
			goBack(request, response);
			return;
		}
		
		request.setAttribute("tavoloDTO", new TavoloDTO().buildDtoFrom(tavolo));
		request.setAttribute("tavoloDTO", new TavoloDTO().buildDtoFrom(tavolo));
		request.getRequestDispatcher("/jsp/tavolo/show-tavolo.jsp").forward(request, response);
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
