package it.solvingteam.pokeronline.web.servlet.partita;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayServlet
 */
@WebServlet("/PlayServlet")
public class PlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		double segno = Math.random();
//		se segno >=0.5 segno positivo, negativo altrimenti.
//		Int somma=(int)Math.random()*1000
//		Tot = segno*somma
//		Questa cifra, che può essere positiva o negativa, va aggiunta (o sottratta) al campo creditoAcc dell’utente che sta giocando.
//		Se si arriva a importo < 0 non va un valore negativo ma a zero. A quel punto a piacere si intervenire con un messaggio di credito esaurito e la possibilità di tornare alla home.

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
