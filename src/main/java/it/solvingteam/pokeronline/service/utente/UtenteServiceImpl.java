package it.solvingteam.pokeronline.service.utente;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.UtenteRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;

@Component
public class UtenteServiceImpl extends GenericServiceImpl<Utente> implements UtenteService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public UtenteRepository getRepository() {
		return utenteRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean usernameEsistente(String username) {
		return utenteRepository.findByUsername(username).size() > 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Utente autentica(Utente instance) {
		try {
			return utenteRepository.findByUsernameAndPassword(instance.getUsername(), instance.getPassword()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Utente aggiungiCredito(Utente instance, int crediti) {
		Utente utente = carica(instance.getId());
		int totale = utente.getCredito() + crediti;
		utente.setCredito(totale);
		aggiorna(utente);
		return utente;
	}

	@Override
	public List<Utente> findByExample(Utente instance) {
		String base = "from Utente u join fetch Ruolo r where 1=1";
		Long id = instance.getId();
		String nome = instance.getNome();
		String cognome = instance.getCognome();
		String username = instance.getUsername();
		String password = instance.getPassword();
		Integer exp = instance.getExp();
		Integer credito = instance.getCredito();
		Date dataRegistrazione = instance.getDataRegistrazione();
		Utente.Stato stato = instance.getStato();
		Long idRuolo = null;
		if (instance.getRuoli() != null) {
			if (instance.getRuoli().iterator().hasNext()) {
				idRuolo = instance.getRuoli().iterator().next().getId();
			}
		}
		boolean idNotNull = id != null;
		boolean nomeNotNull = nome != null;
		boolean cognomeNotNull = cognome != null;
		boolean usernameNotNull = username != null;
		boolean passwordNotNull = password != null;
		boolean expNotNull = exp != null;
		boolean creditoNotNull = credito != null;
		boolean dataRegistrazioneNotNull = dataRegistrazione != null;
		boolean statoNotNull = stato != null;
		boolean idRuoloNotNull = idRuolo != null;
		if (idNotNull) {
			base += " and u.id = " + id;
		}
		if (nomeNotNull) {
			base += " and u.nome like '%" + nome + "%' ";
		}
		if (cognomeNotNull) {
			base += " and u.cognome like '%" + cognome + "%' ";
		}
		if (usernameNotNull) {
			base += " and u.username like '%" + username + "%' ";
		}
		if (passwordNotNull) {
			base += " and u.password like '%" + password + "%' ";
		}
		if (expNotNull) {
			base += " and u.exp = " + exp;
		}
		if (creditoNotNull) {
			base += " and u.credito = " + credito;
		}
		if (dataRegistrazioneNotNull) {
			base += " and u.dataRegistrazione = " + dataRegistrazione;
		}
		if (statoNotNull) {
			base += " and u.stato = " + stato;
		}
		if (idRuoloNotNull) {
			base += " and r.id = " + idRuolo;
		}
		return entityManager.createQuery(base, Utente.class).getResultList();
	}

}
