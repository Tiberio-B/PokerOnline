package it.solvingteam.pokeronline.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.pokeronline.model.Utente;

public class UtenteDTO {
	
	private String id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	private String stato;
	private String exp;
	private String credito;
	private String idPartita;
	private String[] idRuoli;
	private String[] idTavoli;
	
	public UtenteDTO() {};
	
	public UtenteDTO(String id, String nome, String cognome, String username, String password,
			String dataRegistrazione, String stato, String exp, String credito, String idPartita,
			String[] idRuoli, String[] idTavoli) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.exp = exp;
		this.credito = credito;
		this.idPartita = idPartita;
		this.idRuoli = idRuoli;
		this.idTavoli = idTavoli;
	}
	
	public UtenteDTO(String nome, String cognome, String username, String password,
			String dataRegistrazione, String stato, String exp, String credito, String idPartita,
			String[] idRuoli, String[] idTavoli) {
		this(null, nome, cognome, username, password, dataRegistrazione, stato, exp, credito, idPartita, idRuoli, idTavoli);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	public String getIdPartita() {
		return idPartita;
	}
	public void setIdPartita(String idPartita) {
		this.idPartita = idPartita;
	}
	public String[] getIdRuoli() {
		return idRuoli;
	}
	public void setIdRuoli(String[] idRuoli) {
		this.idRuoli = idRuoli;
	}
	public String[] getIdTavoli() {
		return idTavoli;
	}
	public void setIdTavoli(String[] idTavoli) {
		this.idTavoli = idTavoli;
	}
	
	public List<String> errors() {
		return errors(false, false, false, false);
	}
	
	@SuppressWarnings("deprecation")
	public List<String> errors (boolean checkId, boolean checkPartita, boolean checkRuoli, boolean checkTavoli) {
		List<String> result = new ArrayList<String>();
		if (checkId) {
			if(StringUtils.isBlank(this.id)) {
				result.add("Il campo id non può essere vuoto");			
			} else {
				try {
					Long.parseLong(this.id);
				} catch(NumberFormatException e) {
					result.add("Utente inserito non valido");
				}
			}
		}
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");	
		if (StringUtils.isBlank(this.dataRegistrazione)) {
			result.add("Il campo data registrazione non può essere vuoto");
		} else {
			try {
				new Date(dataRegistrazione);
			} catch(IllegalArgumentException e) {
				result.add("Data registrazione inserita non valida");
			}
		}
		if (StringUtils.isBlank(this.stato)) {
			result.add("Il campo stato non può essere vuoto");
		} else {
			try {
				Enum.valueOf(Utente.Stato.class, stato);
			} catch(IllegalArgumentException e) {
				result.add("Stato inserito non valido");
			}
		}	
		if (StringUtils.isBlank(this.exp))
			result.add("Il campo punti esperienza non può essere vuoto");
		if (StringUtils.isBlank(this.credito))
			result.add("Il campo credito non può essere vuoto");
		if (checkPartita) {
			if(StringUtils.isBlank(this.idPartita)) {
				result.add("Il campo partita non può essere vuoto");			
			} else {
				try {
					Long.parseLong(this.idPartita);
				} catch(NumberFormatException e) {
					result.add("Partita inserita non valida");
				}
			}
		}
		if (checkRuoli) {
			for (String idRuolo : this.idRuoli) {
				if(StringUtils.isBlank(idRuolo)) {
					result.add("Il campo ruolo non può essere vuoto");
				} else {
					try {
						Long.parseLong(idRuolo);
					} catch(NumberFormatException e) {
						result.add("Ruolo inserito non valido");
					}
				}
			}
		}
		if (checkTavoli) {
			for (String idTavolo : this.idTavoli) {
				if(StringUtils.isBlank(idTavolo)) {
					result.add("Il campo tavolo non può essere vuoto");
				} else {
					try {
						Long.parseLong(idTavolo);
					} catch(NumberFormatException e) {
						result.add("Tavolo inserito non valido");
					}
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public Utente buildModel() {
		Utente result = new Utente();;
		if (this.id != null) { result.setId(Long.parseLong(this.id)); }
		result.setNome(this.nome);
		result.setCognome(this.cognome);
		result.setUsername(this.username);
		result.setPassword(this.password);
		result.setDataRegistrazione(new Date(this.dataRegistrazione));
		result.setStato(Enum.valueOf(Utente.Stato.class, this.stato));
		result.setExp(Integer.parseInt(this.exp));
		result.setCredito(Integer.parseInt(this.credito));
		return result;
	}

	public UtenteDTO buildDtoFrom(Utente utente) {
		this.setId(String.valueOf(utente.getId()));
		this.setNome(utente.getNome());
		this.setCognome(utente.getCognome());
		this.setUsername(utente.getUsername());
		this.setPassword(utente.getPassword());
		this.setDataRegistrazione(String.valueOf(utente.getDataRegistrazione()));
		this.setStato(utente.getStato().name());
		this.setExp(String.valueOf(utente.getExp()));
		this.setCredito(String.valueOf(utente.getCredito()));
		return this;
	}

}
