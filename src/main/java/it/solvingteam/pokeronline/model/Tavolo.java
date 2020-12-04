package it.solvingteam.pokeronline.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Integer expMin;
	private Integer puntataMin;
	
	private Date dataCreazione;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "proprietario_fk", nullable = false)
	private Utente proprietario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partita", orphanRemoval = true)
	private Set<Utente> giocatori;
	
	public Tavolo() {}
	
	public Tavolo(String nome, int expMin, int puntataMin, Date dataCreazione) {
		this.nome = nome;
		this.expMin = expMin;
		this.puntataMin = puntataMin;
		this.dataCreazione = dataCreazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getExpMin() {
		return expMin;
	}

	public void setExpMin(Integer expMin) {
		this.expMin = expMin;
	}

	public Integer getPuntataMin() {
		return puntataMin;
	}

	public void setPuntataMin(Integer puntataMin) {
		this.puntataMin = puntataMin;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Utente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Utente proprietario) {
		this.proprietario = proprietario;
	}

	public Set<Utente> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Set<Utente> giocatori) {
		this.giocatori = giocatori;
	}

	@Override
	public String toString() {
		return "Tavolo [id=" + id + ", nome=" + nome + ", expMin=" + expMin + ", puntataMin="
				+ puntataMin + ", dataCreazione=" + dataCreazione + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCreazione == null) ? 0 : dataCreazione.hashCode());
		result = prime * result + expMin;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + puntataMin;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (dataCreazione == null) {
			if (other.dataCreazione != null)
				return false;
		} else if (!dataCreazione.equals(other.dataCreazione))
			return false;
		if (expMin != other.expMin)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (puntataMin != other.puntataMin)
			return false;
		return true;
	}
}
