package it.solvingteam.pokeronline.service.ruolo;

import org.springframework.beans.factory.annotation.Autowired;

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.repository.GenericRepository;
import it.solvingteam.pokeronline.repository.RuoloRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;

public class RuoloServiceImpl extends GenericServiceImpl<Ruolo> implements RuoloService {
	
	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	public GenericRepository<Ruolo> getRepository() {
		return ruoloRepository;
	}
}
