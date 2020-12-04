package it.solvingteam.pokeronline.service.utente;

import java.util.List;

import it.solvingteam.pokeronline.dto.UtenteDTO;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.generic.GenericService;

public interface UtenteService extends GenericService<Utente> {

	boolean usernameEsistente(String username);

	Utente autentica(Utente instance);

	Utente aggiungiCredito(Utente instance, int crediti);

	List<Utente> findByExample(Utente instance);

	Utente caricaConRuoli(Long id);

	Utente disattiva(Long id);
	
	Utente attiva(Long id);

	Utente aggiorna(UtenteDTO utenteDTO, boolean aggiornaRuoli) throws Exception;

}
