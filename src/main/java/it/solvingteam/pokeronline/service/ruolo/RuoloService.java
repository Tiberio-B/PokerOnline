package it.solvingteam.pokeronline.service.ruolo;

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.generic.GenericService;

public interface RuoloService extends GenericService<Ruolo> {
	
	public boolean aggiungiRuolo(Utente utente, Ruolo ruolo);

}
