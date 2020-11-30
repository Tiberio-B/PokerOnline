package it.solvingteam.pokeronline.service.tavolo;

import org.springframework.beans.factory.annotation.Autowired;

import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.repository.GenericRepository;
import it.solvingteam.pokeronline.repository.TavoloRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;

public class TavoloServiceImpl extends GenericServiceImpl<Tavolo> implements TavoloService {
	
	@Autowired
	private TavoloRepository tavoloRepository;

	@Override
	public GenericRepository<Tavolo> getRepository() {
		return tavoloRepository;
	}

}
