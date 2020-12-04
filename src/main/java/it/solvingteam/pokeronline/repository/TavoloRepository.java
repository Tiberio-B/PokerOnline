package it.solvingteam.pokeronline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.pokeronline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {

	@Query("from Tavolo t left join fetch t.giocatori")
	List<Tavolo> findAllFetchGiocatori();

	@Query("from Tavolo t left join fetch t.giocatori where t.id = ?1")
	Tavolo findByIdFetchGiocatori(Long id);

	@Query("from Tavolo t left join fetch t.proprietario left join fetch t.giocatori where t.id = ?1")
	Tavolo findByIdFetchProprietarioAndGiocatori(Long id);	
}
