package it.solvingteam.pokeronline.service.generic;

import java.util.List;

public interface GenericService<T> {

	public List<T> elenca();

	public T carica(Long id);

	public void aggiorna(T instance);

	public void inserisci(T instance);

	public void rimuovi(T instance);

	public List<T> findByExample(T example);
}
