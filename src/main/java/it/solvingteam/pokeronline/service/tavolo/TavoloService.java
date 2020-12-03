package it.solvingteam.pokeronline.service.tavolo;

import java.util.List;

import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.generic.GenericService;

public interface TavoloService extends GenericService<Tavolo> {

	void creaTavolo(Utente utente, Tavolo tavolo);
	
	boolean aggiungiGiocatori(Tavolo tavolo, Utente... giocatori);

	List<Tavolo> findByExample(Tavolo instance);

	List<Tavolo> elencaConGiocatori();

}
