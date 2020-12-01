package it.solvingteam.pokeronline.service.generic;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public abstract class GenericServiceImpl<T> implements GenericService<T> {

	@Override
	@Transactional(readOnly = true)
	public List<T> elenca() {
		return (List<T>) getRepository().findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public T carica(Long id) {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(T instance) {
		getRepository().save(instance);
	}

	@Override
	@Transactional
	public void inserisci(T instance) {
		getRepository().save(instance);
	}

	@Override
	@Transactional
	public void rimuovi(T instance) {
		getRepository().delete(instance);
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<T> findByExample(T example) {
//		ExampleMatcher matcher = ExampleMatcher.matching()
//				.withStringMatcher(StringMatcher.CONTAINING); // Match string containing pattern
//		// .withIgnoreCase();
//		return (List<T>) getRepository().findAll(Example.of(example, matcher));
//	}

}
