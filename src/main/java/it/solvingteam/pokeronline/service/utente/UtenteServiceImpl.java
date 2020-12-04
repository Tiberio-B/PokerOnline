package it.solvingteam.pokeronline.service.utente;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solvingteam.pokeronline.dto.UtenteDTO;
import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.repository.UtenteRepository;
import it.solvingteam.pokeronline.service.generic.GenericServiceImpl;
import it.solvingteam.pokeronline.service.ruolo.RuoloService;
import it.solvingteam.pokeronline.util.Utils;

@Component
public class UtenteServiceImpl extends GenericServiceImpl<Utente> implements UtenteService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private RuoloService ruoloService;

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
		String base = "SELECT DISTINCT u FROM Utente u LEFT JOIN FETCH Ruolo r WHERE 1=1";
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
		boolean expNotNull = exp != null;
		boolean creditoNotNull = credito != null;
		boolean dataRegistrazioneNotNull = dataRegistrazione != null;
		boolean statoNotNull = stato != null;
		boolean idRuoloNotNull = idRuolo != null;
		if (idNotNull) {
			base += " AND u.id = " + id;
		}
		if (!Utils.isEmptyOrNull(nome)) {
			base += " AND u.nome like '%" + nome + "%' ";
		}
		if (!Utils.isEmptyOrNull(cognome)) {
			base += " AND u.cognome like '%" + cognome + "%' ";
		}
		if (!Utils.isEmptyOrNull(username)) {
			base += " AND u.username like '%" + username + "%' ";
		}
		if (!Utils.isEmptyOrNull(password)) {
			base += " AND u.password like '%" + password + "%' ";
		}
		if (expNotNull) {
			base += " AND u.exp = " + exp;
		}
		if (creditoNotNull) {
			base += " AND u.credito = " + credito;
		}
		if (dataRegistrazioneNotNull) {
			base += " AND u.dataRegistrazione = " + dataRegistrazione;
		}
		if (statoNotNull) {
			base += " AND u.stato = " + stato;
		}
		if (idRuoloNotNull) {
			base += " AND r.id = " + idRuolo;
		}
		return entityManager.createQuery(base, Utente.class).getResultList();
	}

	@Override
	public Utente caricaConRuoli(Long id) {
		return utenteRepository.findByIdFetchRuoli(id);
	}
	
	@Override
	public Utente disattiva(Long id) {
		Utente utente = carica(id);
		if (utente != null) {
			utente.setStato(Utente.Stato.INATTIVO);
			aggiorna(utente);
		}
		return utente;
	}
	
	@Override
	public Utente attiva(Long id) {
		Utente utente = carica(id);
		if (utente != null) {
			utente.setStato(Utente.Stato.ATTIVO);
			aggiorna(utente);
		}
		return utente;
	}

	@Override
	public Utente aggiornaConRuoli(UtenteDTO utenteDTO) throws Exception {
		Utente utenteOld = caricaConRuoli(Long.valueOf(utenteDTO.getId()));
		
		if (utenteOld != null) {
			boolean forbidRolesUpdate = utenteOld.getStato() != Utente.Stato.CREATO && !(Utils.isEmptyOrNull(utenteDTO.getIdRuoli()));
			if (forbidRolesUpdate) {
				throw new Exception("Impossibile modificare i ruoli dell'utente");
			}
		}
		Set<Ruolo> ruoliUtente = new HashSet<>();
		for (String idRuoloParam : utenteDTO.getIdRuoli()) {
			ruoliUtente.add(ruoloService.carica(Long.valueOf(idRuoloParam)));
		}
		utenteOld.setRuoli(ruoliUtente);
		
		Utente utenteNew = utenteDTO.buildModel();
		utenteNew.setId(utenteOld.getId());
		utenteNew.setPassword(utenteOld.getPassword());
		utenteNew.setDataRegistrazione(utenteOld.getDataRegistrazione());
		utenteNew.setRuoli(utenteOld.getRuoli());
		aggiorna(utenteNew);
		return utenteNew;
	}

}
