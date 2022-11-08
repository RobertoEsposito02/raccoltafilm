package it.prova.raccoltafilm.service;

import java.util.List;

import it.prova.raccoltafilm.dao.UtenteDAO;
import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Utente;

public interface UtenteService  {
	
	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Long idUtenteToRemove) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
	public Utente findByUsernameAndPassword(String username, String password) throws Exception;
	
	public Utente accedi(String username, String password) throws Exception;

	//per injection
	public void setUtenteDAO(UtenteDAO utenteDAO);

}
