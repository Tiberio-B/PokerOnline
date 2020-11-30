package it.solvingteam.pokeronline.service.utente;

import org.springframework.beans.factory.annotation.Autowired;

import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.GenericRepository;
import it.solvingteam.pokeronline.repository.UtenteRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;

public class UtenteServiceImpl extends GenericServiceImpl<Utente> implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public GenericRepository<Utente> getRepository() {
		return utenteRepository;
	}

}
