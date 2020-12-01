package it.solvingteam.pokeronline.service.utente;

import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.generic.GenericService;

public interface UtenteService extends GenericService<Utente> {

	boolean usernameEsistente(String username);

	Utente autentica(Utente instance);

	Utente aggiungiCredito(Utente instance, int crediti);

}
