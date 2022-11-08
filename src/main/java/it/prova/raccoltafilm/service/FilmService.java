package it.prova.raccoltafilm.service;

import java.util.List;

import it.prova.raccoltafilm.dao.FilmDAO;
import it.prova.raccoltafilm.model.Film;

public interface FilmService {
	public List<Film> listAllElements() throws Exception;

	public Film caricaSingoloElemento(Long id) throws Exception;
	
	public Film caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Film filmInstance) throws Exception;

	public void inserisciNuovo(Film filmInstance) throws Exception;

	public void rimuovi(Long idFilmToRemove) throws Exception;

	public List<Film> findByExample(Film example) throws Exception;

	// per injection
	public void setFilmDAO(FilmDAO filmDAO);
}
