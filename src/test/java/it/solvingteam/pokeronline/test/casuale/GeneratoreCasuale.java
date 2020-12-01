package it.solvingteam.pokeronline.test.casuale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.pokeronline.model.Ruolo;
import it.solvingteam.pokeronline.model.Tavolo;
import it.solvingteam.pokeronline.model.Utente;
import it.solvingteam.pokeronline.service.ruolo.RuoloService;
import it.solvingteam.pokeronline.service.tavolo.TavoloService;
import it.solvingteam.pokeronline.service.utente.UtenteService;

@Component
public class GeneratoreCasuale extends Random {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private RuoloService ruoloService;

	private <T> T elementoCasuale(T[]... arrays) {
		List<T> lista = new ArrayList<>();
		for (T[] array : arrays) {
			for (T t : array) {
				lista.add(t);
			}
		}
		return elementoCasuale(lista);
	}
	
	private <T> T elementoCasuale(T[] array) {
		return array[nextInt(array.length)];
	}

	public <T> T elementoCasuale(List<T> lista) {
		return lista.get(nextInt(lista.size()));
	}

	public int nextInt(int min, int max) {
		return (nextInt(max - min + 1) + min);
	}

	public boolean coinFlip() {
		return (nextInt(2) > 0) ? true : false;
	}

	public String nomeCasuale() {
		return elementoCasuale(LoremIpsum.NOMI);
	}

	public String cognomeCasuale() {
		return elementoCasuale(LoremIpsum.COGNOMI);
	}
	
	public String nomeCompletoCasuale() {
		return nomeCasuale() + " " + cognomeCasuale();
	}
	
	public Tavolo tavoloCasuale() {
		return elementoCasuale(tavoloService.elenca());
	}
	
	public Ruolo ruoloCasuale() {
		return elementoCasuale(ruoloService.elenca());
	}
	
	public Utente utenteCasuale() {
		return elementoCasuale(utenteService.elenca());
	}

	@SuppressWarnings("deprecation")
	Date dataCasuale(int giornoMin, int giornoMax, int meseMin, int meseMax, int annoMin,
			int annoMax) {
		int giorno = nextInt(giornoMin, giornoMax);
		int mese = nextInt(meseMin, meseMax);
		int anno = nextInt(annoMin, annoMax);
		return new Date(anno - 1900, mese - 1, giorno);
	}
	
	Date dataCasuale() {
		return dataCasuale(1, 28, 1, 12, 2000, 2020);
	}

	public String usernameCasuale() {
		return elementoCasuale(LoremIpsum.VCVCVCV);
	}
	
	public int creditoCasuale(int min, int max) {
		return nextInt(min, max);
	}
	
	public int expCasuale(int min, int max) {
		return nextInt(min, max);
	}
	
	public Tavolo nuovoTavoloCasuale() {
		return new Tavolo(usernameCasuale(), nextInt(), nextInt(), dataCasuale());
	}
	
	public Utente nuovoUtenteCasuale() {
		return new Utente(nomeCasuale(), cognomeCasuale(), usernameCasuale(), "a", dataCasuale());
	}
}
