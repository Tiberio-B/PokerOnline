package it.solvingteam.pokeronline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.pokeronline.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor<Utente> {

	List<Utente> findByUsername(String username);

	List<Utente> findByUsernameAndPassword(String username, String password);
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Utente findByIdFetchRuoli(Long id);

}
