package it.solvingteam.pokeronline.service.utente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.UtenteRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;

@Component
public class UtenteServiceImpl extends GenericServiceImpl<Utente> implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public UtenteRepository getRepository() {
		return utenteRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean usernameEsistente(String username) {
		return utenteRepository.findByUsername(username).size() > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Utente autentica(Utente instance) {
		try {
			return utenteRepository.findByUsernameAndPassword(instance.getUsername(), instance.getPassword()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Utente aggiungiCredito(Utente instance, int crediti) {
		Utente utente = carica(instance.getId());
		int totale = utente.getCredito() + crediti;
		utente.setCredito(totale);
		aggiorna(utente);
		return utente;
	}

}
