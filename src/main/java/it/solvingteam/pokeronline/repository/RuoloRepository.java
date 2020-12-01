package it.solvingteam.pokeronline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.pokeronline.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>, QueryByExampleExecutor<Ruolo> {

}
