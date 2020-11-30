package it.solvingteam.pokeronline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface GenericRepository<T> extends CrudRepository<T, Long>, QueryByExampleExecutor<T> {

}
