//package it.solvingteam.pokeronline.test;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import it.solvingteam.pokeronline.model.Ruolo;
//import it.solvingteam.pokeronline.model.Tavolo;
//import it.solvingteam.pokeronline.model.Utente;
//import it.solvingteam.pokeronline.service.ruolo.RuoloService;
//import it.solvingteam.pokeronline.service.tavolo.TavoloService;
//import it.solvingteam.pokeronline.service.utente.UtenteService;
//import it.solvingteam.pokeronline.test.casuale.GeneratoreCasuale;
//
//@Component
//public class TestService {
//	
//	@Autowired
//	private GeneratoreCasuale r;
//	
//	private static final int numUtenti = 20;
//	private static final int numTavoli = 10;
//	private static final int maxRuoliPerUtente = 3;
//	private static final int numGiochiAvviati = 20;
//	private static final int maxNumGiocatori = 5;
//	private static final int numPartite = 10;
//	
//	private static final int numTavoliCreati = 10;
//	private static final int numConferimentiRuoli = 10;
//	
//	@Autowired
//	private UtenteService utenteService;
//
//	@Autowired
//	private TavoloService tavoloService;
//	
//	@Autowired
//	private RuoloService ruoloService;
//	
//	// casi di test (usare valorizzando la variabile casoDaTestare nel main)
//	public static final String INIT = "INIT";
//	public static final String INIT_TAVOLI = "INIT_TAVOLI";
//	public static final String INIT_UTENTI = "INIT_UTENTI";
//	public static final String INIT_RUOLI = "INIT_RUOLI";
//	public static final String PROVA_BASE = "PROVA_BASE";
//	
//	public void test(String casoDaTestare) {
//		try {
//			switch (casoDaTestare) {
//				case INIT:
//					init(numUtenti);
//					break;
//				case INIT_TAVOLI:
//					initTavoli(numTavoli);
//					break;
//				case INIT_UTENTI:
//					initUtenti(numUtenti);
//					break;
//				case INIT_RUOLI:
//					initRuoli();
//					break;
//				case PROVA_BASE:
//					init(numUtenti);
//					conferisciRuolo(maxRuoliPerUtente);
//					avviaGioco(numGiochiAvviati);
//					partiteCasuali(numPartite, maxNumGiocatori);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void init(int numUtenti) {
//		initUtenti(numUtenti);
//		initRuoli();
//	}
//
//	public void initTavoli(int numTavoli) {
//		for (int t = 0; t < numTavoli; t++) {
//			Tavolo tav = r.nuovoTavoloCasuale();
//			tavoloService.inserisci(tav);
//			System.out.println("Tavolo appena creato: " + tav);
//		}
//	}
//	
//	public void initUtenti(int numUtenti) {
//		for (int u = 0; u < numUtenti; u++) {
//			Utente ut = r.nuovoUtenteCasuale();
//			utenteService.inserisci(ut);
//			System.out.println("Utente appena creato: " + ut);
//		}
//	}
//	
//	public void initRuoli() {
//		Ruolo adm = new Ruolo("Amministratore", Ruolo.Codice.ADMIN_ROLE);
//		ruoloService.inserisci(adm);
//		System.out.println("Ruolo appena creato: " + adm);
//			
//		Ruolo play = new Ruolo("Player", Ruolo.Codice.PLAYER_ROLE);
//		ruoloService.inserisci(play);
//		System.out.println("Ruolo appena creato: " + play);
//			
//		Ruolo sup = new Ruolo("Special Player", Ruolo.Codice.SPECIAL_PLAYER_ROLE);
//		ruoloService.inserisci(sup);
//		System.out.println("Ruolo appena creato: " + sup);
//	}
//	
//	public void conferisciRuolo(int maxRuoliPerUtente) {
//		for (Utente u : utenteService.elenca()) {
//			for (int i=0; i < maxRuoliPerUtente; i++) {
//				ruoloService.aggiungiRuolo(u, r.ruoloCasuale());
//			}
//		}
//	}
//	
//	public void avviaGioco(int numGiochiAvviati) {
//		for (Utente utente : utentiCasuali(numGiochiAvviati)) {
//			Tavolo tavolo = r.nuovoTavoloCasuale();
//			tavoloService.creaTavolo(utente, tavolo);
//		}
//	}
//	
//	public Utente[] utentiCasuali(int maxNumUtenti) {
//		Utente[] utenti = new Utente[maxNumUtenti];
//		for (int i=0; i < maxNumUtenti; i++) {
//			utenti[i] = r.utenteCasuale();
//		}
//		return utenti;
//	}
//	
//	public void partiteCasuali(int numPartite, int maxNumGiocatori) {
//		for (int i=0; i < numPartite; i++) {
//			Utente[] giocatori = utentiCasuali(maxNumGiocatori);
//			Tavolo tavolo = r.tavoloCasuale();
//			tavoloService.aggiungiGiocatori(tavolo, giocatori);
//		}
//	}
//	
//	public static void stampa(Object obj) {
//		System.out.println(obj);
//	}
//
//}
