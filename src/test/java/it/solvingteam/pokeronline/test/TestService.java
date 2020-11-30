package it.solvingteam.pokeronline.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.ruolo.RuoloService;
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.test.casuale.GeneratoreCasuale;

@Component
public class TestService {
	
	@Autowired
	private GeneratoreCasuale r;
	
	private static final int numTavoli = 5;
	private static final int numUtenti = 20;
	private static final int numGiochiAvviati = 10;
	private static final int numConferimentiRuoli = 10;
	
	private static List<Tavolo> tavoli;
	private static List<Utente> utenti;
	private static List<Ruolo> ruoli;
	
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private RuoloService ruoloService;
	
	// casi di test (usare valorizzando la variabile casoDaTestare nel main)
	public static final String INIT = "INIT";
	public static final String INIT_TAVOLI = "INIT_TAVOLI";
	public static final String INIT_UTENTI = "INIT_UTENTI";
	public static final String INIT_RUOLI = "INIT_RUOLI";
	
	public void test(String casoDaTestare) {
		try {
			switch (casoDaTestare) {
				case INIT:
					init(numTavoli, numUtenti);
					break;
				case INIT_TAVOLI:
					initTavoli(numTavoli);
					break;
				case INIT_UTENTI:
					initUtenti(numUtenti);
					break;
				case INIT_RUOLI:
					initRuoli();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init(int numTavoli, int numUtenti) {
		initTavoli(numTavoli);
		initUtenti(numUtenti);
		initRuoli();
	}

	public void initTavoli(int numTavoli) {
		for (int t = 0; t < numTavoli; t++) {
			Tavolo tav = new Tavolo();
			tavoloService.inserisci(tav);
			System.out.println("Tavolo appena creato: " + tav);
		}
		tavoli = tavoloService.elenca();
	}
	
	public void initUtenti(int numUtenti) {
		for (int u = 0; u < numUtenti; u++) {
			Utente ut = new Utente();
			utenteService.inserisci(ut);
			System.out.println("Utente appena creato: " + ut);
		}
		utenti = utenteService.elenca();
	}
	
	public void initRuoli() {
		
			Ruolo adm = new Ruolo();
			ruoloService.inserisci(adm);
			System.out.println("Ruolo appena creato: " + adm);
			
			Ruolo play = new Ruolo();
			ruoloService.inserisci(play);
			System.out.println("Ruolo appena creato: " + play);
			
			Ruolo sup = new Ruolo();
			ruoloService.inserisci(sup);
			System.out.println("Ruolo appena creato: " + sup);		
			
			ruoli = ruoloService.elenca();
	}

	
//	public static void doOrdinativi(int numOrdinativi) throws Exception {
//		for (int j = 0; j < numOrdinativi; j++) {
//			Articolo articolo = r.articoloCasuale();
//			Ordine ordine = r.ordineCasuale();
//			if (r.coinFlip()) {
//				articolo.addOrdine(ordine);
//			}
//			else { ordine.addArticolo(articolo); }
//		}
//	}
//	
//	public static void doCategorizzazioni(int numCategorizzazioni) throws Exception{
//		for (int k = 0; k < numCategorizzazioni; k++) {
//			Articolo articolo = r.articoloCasuale();
//			Categoria categoria = r.categoriaCasuale();
//			if (r.coinFlip()) {
//				articolo.addCategoria(categoria);
//			}
//			else { categoria.addArticolo(articolo); }
//		}
//	}
	
	public static void stampa(Object obj) {
		System.out.println(obj);
	}

}
