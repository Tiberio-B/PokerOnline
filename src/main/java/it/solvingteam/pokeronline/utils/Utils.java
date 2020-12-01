package it.solvingteam.pokeronline.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class Utils {
	
	/**
	   * Metodo per la validazione di un parametro di nome {@code stringParamName}
	   * dell'oggetto richiesta {@code HttpServletRequest request}.
	   * Se il valore del parametro è una sequenza di caratteri valida
	   * (stringa non vuota, non null e non whitespace only), viene ritornata senza modifiche;
	   * altrimenti viene ritornato {@code null} e il parametro {@code errorMessage}
	   * della {@code request} è aggiornato con un messaggio di errore opportuno.
	   * 
	   * @param request la richiesta da cui ottenere il parametro
	   * @param stringParamName il nome del parametro da ottenere dalla richiesta
	   * @return String il valore del parametro se è una stringa non vuota, non null e non whitespace only;
	   * @return null altrimenti.
	   * 
	   * @see HttpServletRequest
	   */
	public static String validateStringParam(HttpServletRequest request, String stringParamName, boolean addError) {
		String param = request.getParameter(stringParamName);
		if (StringUtils.isBlank(param)) { // se stringa invalida, aggiorna messaggio di errore
			if (addError) { 
				addError(request, "Il campo '"+ stringParamName +"' deve essere una sequenza di caratteri validi.");
			}
			return null;
		}
		return param;
	}
	
	/**
	   * Metodo per la validazione di un parametro di nome {@code longParamName}
	   * dell'oggetto richiesta {@code HttpServletRequest request}.
	   * Se il valore del parametro è parsabile in un oggetto di tipo {@code Long},
	   * viene ritornato come tale; altrimenti viene ritornato {@code null}
	   * e il parametro {@code errorMessage} della {@code request} è aggiornato
	   * con un messaggio di errore opportuno.
	   * 
	   * @param request la richiesta da cui ottenere il parametro
	   * @param longParamName il nome del parametro da ottenere dalla richiesta
	   * @return Long il valore del parametro se è parsabile in un qualsiasi numero {@code Long}
	   * @return {@code null} altrimenti.
	   * 
	   * @see HttpServletRequest
	   */
	public static Long validateLongParam(HttpServletRequest request, String longParamName, boolean addError) {
		try {
			return Long.parseLong(request.getParameter(longParamName));
		} catch (NumberFormatException | NullPointerException e) { // se ID invalido, aggiorna messaggio di errore
			if (addError) { 
				addError(request, "Il campo '"+ longParamName +"' deve essere un intero valido.");
			}
			return null;
		}
	}
	
	/**
	   * Metodo per la validazione di un parametro di nome {@code enumParamName}
	   * dell'oggetto richiesta {@code HttpServletRequest request}.
	   * Se il valore del parametro è una sequenza di caratteri valida
	   * (stringa non vuota e non null), viene tentata la conversione dello stesso
	   * all'istanza corrispondente del tipo enumerativo specificato da {@code enumType},
	   * ritornata poi in output;
	   * altrimenti viene ritornato {@code null} e il parametro {@code errorMessage}
	   * della {@code request} è aggiornato con un messaggio di errore opportuno.
	   * 
	   * @param request la richiesta da cui ottenere il parametro
	   * @param enumParamName il nome del parametro da ottenere dalla richiesta
	   * @param enumType il tipo enumerativo a cui il parametro deve essere convertito
	   * @return istanza di tipo {@code enumType} corrispondente al parametro, se valido;
	   * @return null altrimenti.
	   * 
	   * @see HttpServletRequest
	   */
	public static <T extends Enum<T>> T validateEnumParam(HttpServletRequest request, String enumParamName, Class<T> enumType, boolean addError) {
		String enumParam = validateStringParam(request, enumParamName, false);
		T enumVar = null;
		try {
			enumVar = Enum.valueOf(enumType, enumParam);
		} catch (NullPointerException | IllegalArgumentException e) {
			if (addError) { 
				addError(request, enumType.getSimpleName() + " specificato non presente nella biblioteca.");
			}
			return null;
		}
		return enumVar;		
	}
	
	public static Date validateDateParam(HttpServletRequest request, String dateParamName, boolean addError) {
		Date date = null;
		try {
			System.out.println(request.getParameter(dateParamName));
			String[] stringParams = request.getParameter(dateParamName).split("-");
			Integer[] params = new Integer[3];
			for (int i = 0; i < 3; i++) {
				params[i] = Integer.valueOf(stringParams[i]);
				System.out.println(stringParams[i] +"\n"+ params[i]);
			}
			date = new Date(params[2], params[1] - 1, params[0]);
		} catch (IllegalArgumentException | NullPointerException e) {
			if (addError) { 
				addError(request, "Il campo '"+ dateParamName +"' deve essere una data espressa nel formato gg-mm-aaaa.");
			}
			return null;
		}
		return date;	
	}
	
	public static void addError(HttpServletRequest request, String errorMessageString) {
		List<String> errors = new ArrayList<String>() {{
			   add(errorMessageString);
			}};
		addErrors(request, errors);
	}

	public static void addErrors(HttpServletRequest request, List<String> errors) {
		Object attr = request.getAttribute("errorMessages");
		if (attr == null) {
			attr = new ArrayList<String>();
		}
		if (attr instanceof ArrayList<?>) {
			ArrayList<String> errorList = (ArrayList<String>) attr;
			for (String errorMessageString : errors) {
				errorList.add(errorMessageString);
			}
		}
		request.setAttribute("errorMessages", attr);
	}
	
	public static void sendParamsBack(HttpServletRequest request, String[] paramNames) {
		for (String paramName : paramNames) {
			request.setAttribute(paramName, request.getParameter(paramName));
		}
	}

}
