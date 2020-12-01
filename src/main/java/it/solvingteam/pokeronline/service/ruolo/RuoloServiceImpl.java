package it.solvingteam.pokeronline.service.ruolo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.RuoloRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;
import it.solvingteam.pokeronline.service.utente.UtenteService;

@Component
public class RuoloServiceImpl extends GenericServiceImpl<Ruolo> implements RuoloService {
	
	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Autowired
	private UtenteService utenteService;

	@Override
	public RuoloRepository getRepository() {
		return ruoloRepository;
	}

	@Override
	@Transactional
	public boolean aggiungiRuolo(Utente utente, Ruolo ruolo) {
		Set<Ruolo> ruoli = utente.getRuoli();
		boolean aggiunto = ruoli.add(ruolo);
		utenteService.aggiorna(utente);
		return aggiunto;
	}
}
