package it.solvingteam.pokeronline.service.tavolo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.TavoloRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;
import it.solvingteam.pokeronline.service.utente.UtenteService;

@Component
public class TavoloServiceImpl extends GenericServiceImpl<Tavolo> implements TavoloService {
	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Autowired
	private UtenteService utenteService;

	@Override
	public TavoloRepository getRepository() {
		return tavoloRepository;
	}

	@Override
	public void creaTavolo(Utente utente, Tavolo tavolo) {
		utente = utenteService.carica(utente.getId());
		tavolo.setProprietario(utente);
		inserisci(tavolo);
	}

	@Override
	public boolean aggiungiGiocatori(Tavolo tavolo, Utente... giocatori) {
		tavolo = carica(tavolo.getId());
		boolean aggiunti = false;
		Set<Utente> giocanti = tavolo.getGiocatori();
		for (Utente giocatore : giocatori) {
			aggiunti = aggiunti || giocanti.add(giocatore);
			giocatore.setPartita(tavolo);
			utenteService.aggiorna(giocatore);
		}
		aggiorna(tavolo);
		return aggiunti;
	}

}
