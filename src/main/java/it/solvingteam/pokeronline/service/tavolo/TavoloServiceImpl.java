package it.solvingteam.pokeronline.service.tavolo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.TavoloRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;
import it.solvingteam.pokeronline.service.utente.UtenteService;
import it.solvingteam.pokeronline.util.Utils;

@Component
public class TavoloServiceImpl extends GenericServiceImpl<Tavolo> implements TavoloService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Autowired
	private UtenteService utenteService;

	@Override
	public TavoloRepository getRepository() {
		return tavoloRepository;
	}

	@Override
	public void creaTavolo(Utente utente, Tavolo tavolo) {
		utente = utenteService.carica(utente.getId());
		tavolo.setProprietario(utente);
		inserisci(tavolo);
	}

	@Override
	public boolean aggiungiGiocatori(Tavolo tavolo, Utente... giocatori) {
		tavolo = carica(tavolo.getId());
		boolean aggiunti = false;
		Set<Utente> giocanti = tavolo.getGiocatori();
		for (Utente giocatore : giocatori) {
			aggiunti = aggiunti || giocanti.add(giocatore);
			giocatore.setPartita(tavolo);
			utenteService.aggiorna(giocatore);
		}
		aggiorna(tavolo);
		return aggiunti;
	}
	
	@Override
	public List<Tavolo> findByExample(Tavolo instance) {
		String base = "SELECT DISTINCT t from Tavolo t ";
		Set<Utente> giocatori = instance.getGiocatori();
		boolean giocatoriNotNullNorEmpty = giocatori != null && !giocatori.isEmpty();
		if (giocatoriNotNullNorEmpty) {
			base += "JOIN FETCH t.giocatori ";
		}
		base += "WHERE 1=1 ";
		Long id = instance.getId();
		String nome = instance.getNome();
		Integer expMin = instance.getExpMin();
		Integer puntataMin = instance.getPuntataMin();
		Date dataCreazione = instance.getDataCreazione();
		Utente proprietario = instance.getProprietario();
		boolean idNotNull = id != null;
		boolean expMinNotNull = expMin != null;
		boolean puntataMinNotNull = puntataMin != null;
		boolean dataCreazioneNotNull = dataCreazione != null;
		boolean proprietarioNotNull = proprietario != null;
		if (idNotNull) {
			base += " and t.id = " + id;
		}
		if (!Utils.isEmptyOrNull(nome)) {
			base += " and t.nome like '%" + nome + "%'";
		}
		if (expMinNotNull) {
			base += " and t.expMin = " + expMin;
		}
		if (puntataMinNotNull) {
			base += " and t.puntataMin = " + puntataMin;
		}
		if (dataCreazioneNotNull) {
			base += " and t.dataCreazione = " + dataCreazione;
		}
		if (proprietarioNotNull) {
			base += " and t.proprietario = :proprietario";
		}
		if (giocatoriNotNullNorEmpty) {
			for (int i=0; i < giocatori.size(); i++) {
				base += "AND ?";
				base+= String.valueOf(1+i);
				base += " MEMBER OF t.giocatori ";
			}
		}
		TypedQuery<Tavolo> query = entityManager.createQuery(base, Tavolo.class);
		if (proprietarioNotNull) {
			query.setParameter("proprietario", proprietario);
		}
		if (giocatoriNotNullNorEmpty) {
			for (int i=0; i < giocatori.size(); i++) {
				query.setParameter(1+i, giocatori.iterator().next());
			}
		}
		return query.getResultList();
	}

	@Override
	public List<Tavolo> elencaConGiocatori() {
		return tavoloRepository.findAllFetchGiocatori();
	}

	@Override
	public Tavolo caricaConGiocatori(Long id) {
		return tavoloRepository.findByIdFetchGiocatori(id);
	}

	@Override
	public Tavolo caricaConProprietarioEGiocatori(Long id) {
		return tavoloRepository.findByIdFetchProprietarioAndGiocatori(id);
	}

}
