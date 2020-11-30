package it.solvingteam.pokeronline.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.pokeronline.model.Tavolo;

public class TavoloDTO {
	
	private String id;
	private String nome;
	private String expMin;
	private String puntataMin;
	private String dataCreazione;
	private String idProprietario;
	private String[] idGiocatori;
	
	public TavoloDTO() {};
	
	public TavoloDTO(String id, String nome, String expMin, String puntataMin, String dataCreazione,
			String idProprietario, String[] idGiocatori) {
		this.id = id;
		this.nome = nome;
		this.expMin = expMin;
		this.puntataMin = puntataMin;
		this.dataCreazione = dataCreazione;
		this.idProprietario = idProprietario;
		this.idGiocatori = idGiocatori;
	}
	
	public TavoloDTO(String nome, String expMin, String puntataMin, String dataCreazione,
			String idProprietario, String[] idGiocatori) {
		this(null, nome, expMin, puntataMin, dataCreazione, idProprietario, idGiocatori);
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

	public String getExpMin() {
		return expMin;
	}

	public void setExpMin(String expMin) {
		this.expMin = expMin;
	}

	public String getPuntataMin() {
		return puntataMin;
	}

	public void setPuntataMin(String puntataMin) {
		this.puntataMin = puntataMin;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(String idProprietario) {
		this.idProprietario = idProprietario;
	}

	public String[] getIdGiocatori() {
		return idGiocatori;
	}

	public void setIdGiocatori(String[] idGiocatori) {
		this.idGiocatori = idGiocatori;
	}
	public List<String> errors() {
		return errors(false, false, false);
	}
	
	@SuppressWarnings("deprecation")
	public List<String> errors (boolean checkId, boolean checkProprietario, boolean checkGiocatori) {
		List<String> result = new ArrayList<String>();
		if (checkId) {
			if(StringUtils.isBlank(this.id)) {
				result.add("Il campo id non può essere vuoto");			
			} else {
				try {
					Long.parseLong(this.id);
				} catch(NumberFormatException e) {
					result.add("Tavolo inserito non valido");
				}
			}
		}
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if (StringUtils.isBlank(this.expMin)) {
			result.add("Il campo esperienza minima non può essere vuoto");
		} else {
			try {
				Integer.parseInt(this.expMin);
			} catch(NumberFormatException e) {
				result.add("Esperienza minima inserita non valida");
			}
		}
		if (StringUtils.isBlank(this.puntataMin)) {
			result.add("Il campo puntata minima non può essere vuoto");
		} else {
			try {
				Integer.parseInt(this.puntataMin);
			} catch(NumberFormatException e) {
				result.add("Puntata minima inserita non valida");
			}
		}
		if (StringUtils.isBlank(this.dataCreazione)) {
			result.add("Il campo data registrazione non può essere vuoto");
		} else {
			try {
				new Date(dataCreazione);
			} catch(IllegalArgumentException e) {
				result.add("Data registrazione inserita non valida");
			}
		}
		if (checkProprietario) {
			if(StringUtils.isBlank(this.idProprietario)) {
				result.add("Il campo proprietario non può essere vuoto");			
			} else {
				try {
					Long.parseLong(this.idProprietario);
				} catch(NumberFormatException e) {
					result.add("Proprietario inserito non valida");
				}
			}
		}
		if (checkGiocatori) {
			for (String idGiocatore : this.idGiocatori) {
				if(StringUtils.isBlank(idGiocatore)) {
					result.add("Il campo giocatore non può essere vuoto");
				} else {
					try {
						Long.parseLong(idGiocatore);
					} catch(NumberFormatException e) {
						result.add("Giocatore inserito non valido");
					}
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public Tavolo buildModel() {
		Tavolo result = new Tavolo();;
		if (this.id != null) { result.setId(Long.parseLong(this.id)); }
		result.setNome(this.nome);
		result.setExpMin(Integer.parseInt(this.expMin));
		result.setPuntataMin(Integer.parseInt(this.puntataMin));
		result.setDataCreazione(new Date(this.dataCreazione));
		return result;
	}

	public TavoloDTO buildDtoFrom(Tavolo tavolo) {
		this.setId(String.valueOf(tavolo.getId()));
		this.setNome(tavolo.getNome());
		this.setExpMin(String.valueOf(tavolo.getExpMin()));
		this.setPuntataMin(String.valueOf(tavolo.getPuntataMin()));
		this.setDataCreazione(String.valueOf(tavolo.getDataCreazione()));
		return this;
	}

	
}
