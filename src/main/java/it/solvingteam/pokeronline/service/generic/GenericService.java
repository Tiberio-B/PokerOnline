package it.solvingteam.pokeronline.service.generic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenericService<T> {
	
	public CrudRepository<T, Long> getRepository();

	public List<T> elenca();

	public T carica(Long id);

	public void aggiorna(T instance);

	public void inserisci(T instance);

	public void rimuovi(T instance);

//	public List<T> findByExample(T example);
}
