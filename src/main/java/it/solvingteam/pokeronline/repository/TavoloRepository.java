package it.solvingteam.pokeronline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.pokeronline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {

}
