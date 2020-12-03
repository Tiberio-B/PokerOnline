package it.solvingteam.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.pokeronline.dto.TavoloDTO;
import it.solvingteam.pokeronline.util.Utils;

/**
 * Servlet implementation class PrepareInsertTavoloServlet
 */
@WebServlet("/PrepareInsertTavoloServlet")
public class PrepareInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareInsertTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String nome = request.getParameter("nome");
    	String puntataMin = request.getParameter("puntataMin");
    	String expMin = request.getParameter("expMin");
    	String dataCreazione = Utils.dateToString(new Date());
    	
    	boolean checkEmptyOrNull = false; // nel passaggio dei parametri tra viste, i controlli non sono necessari
    	TavoloDTO tavoloDTO = new TavoloDTO(nome, puntataMin, expMin, dataCreazione, checkEmptyOrNull);
    	request.setAttribute("tavoloDTO", tavoloDTO);
    	request.getRequestDispatcher("jsp/tavolo/insert-tavolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
