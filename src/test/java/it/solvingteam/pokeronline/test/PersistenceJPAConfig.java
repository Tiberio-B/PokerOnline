package it.solvingteam.pokeronline.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "it.solvingteam.pokeronline")
public class PersistenceJPAConfig {

}
