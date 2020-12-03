package it.solvingteam.pokeronline.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;

	@Enumerated(EnumType.STRING)
	private Stato stato;
	
	private Integer exp;
	private Integer credito;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario", orphanRemoval = true)
	private Set<Tavolo> tavoli = new HashSet<>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partita_fk", nullable = true)
	private Tavolo partita;

	public Utente() {
	}

	public Utente(String nome, String cognome, String username, String password, Date dataRegistrazione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = Stato.CREATO;
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

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getCredito() {
		return credito;
	}

	public void setCredito(Integer credito) {
		this.credito = credito;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public Set<Tavolo> getTavoli() {
		return tavoli;
	}

	public void setTavoli(Set<Tavolo> tavoli) {
		this.tavoli = tavoli;
	}

	public Tavolo getPartita() {
		return partita;
	}

	public void setPartita(Tavolo partita) {
		this.partita = partita;
	}
	
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username="
				+ username + ", password=" + password + ", dataRegistrazione=" + dataRegistrazione
				+ ", stato=" + stato + ", exp=" + exp + ", credito=" + credito + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + credito;
		result = prime * result + ((dataRegistrazione == null) ? 0 : dataRegistrazione.hashCode());
		result = prime * result + exp;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Utente other = (Utente) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (credito != other.credito)
			return false;
		if (dataRegistrazione == null) {
			if (other.dataRegistrazione != null)
				return false;
		} else if (!dataRegistrazione.equals(other.dataRegistrazione))
			return false;
		if (exp != other.exp)
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (stato != other.stato)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	private boolean isRole(Ruolo.Codice codice) {
		for (Ruolo ruolo : this.ruoli) {
			if (ruolo.getCodice().equals(codice)) {
				return true;
			}
		}
		return false;
	}

	public boolean isAdmin() { return isRole(Ruolo.Codice.ADMIN_ROLE); }
	
	public boolean isPlayer() { return isRole(Ruolo.Codice.PLAYER_ROLE); }
	
	public boolean isSpecialPlayer() { return isRole(Ruolo.Codice.SPECIAL_PLAYER_ROLE); }

	public enum Stato {
		CREATO,
		ATTIVO,
		INATTIVO;
	}
}
