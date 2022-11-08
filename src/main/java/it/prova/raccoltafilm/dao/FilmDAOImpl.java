package it.prova.raccoltafilm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;

public class FilmDAOImpl implements FilmDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Film> list() throws Exception {
		return entityManager.createQuery("from Film", Film.class).getResultList();
	}

	@Override
	public Optional<Film> findOne(Long id) throws Exception {
		Film result = entityManager.find(Film.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Film filmInstance) throws Exception {
		if(filmInstance == null) {
			throw new RuntimeException("input non valido");
		}
		filmInstance = entityManager.merge(filmInstance);
	}

	@Override
	public void insert(Film filmInstance) throws Exception {
		if (filmInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(filmInstance);
	}

	@Override
	public void delete(Film filmInstance) throws Exception {
		if (filmInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(filmInstance));

	}

	@Override
	public Optional<Film> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Film f left join fetch f.regista where f.id=:idFilm", Film.class)
				.setParameter("idFilm", id).getResultList().stream().findFirst();
	}

	@Override
	public List<Film> findByExample(Film example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select f from Film f where f.id = f.id ");

		if (StringUtils.isNotEmpty(example.getTitolo())) {
			whereClauses.add(" f.titolo  like :titolo ");
			paramaterMap.put("titolo", "%" + example.getTitolo() + "%");
		}
		if (StringUtils.isNotEmpty(example.getGenere())) {
			whereClauses.add(" f.genere like :genere ");
			paramaterMap.put("genere", "%" + example.getGenere() + "%");
		}
		if (example.getMinutiDurata() != null && example.getMinutiDurata() > 0) {
			whereClauses.add(" f.minutiDurata = :getMinutiDurata ");
			paramaterMap.put("getMinutiDurata", example.getMinutiDurata());
		}
		if (example.getRegista() != null) {
			whereClauses.add(" f.regista = :regista ");
			paramaterMap.put("regista", example.getRegista());
		}
		if (example.getDataPubblicazione() != null) {
			whereClauses.add("f.dataPubblicazione >= :dataPubblicazione ");
			paramaterMap.put("dataPubblicazione", example.getDataPubblicazione());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Film> typedQuery = entityManager.createQuery(queryBuilder.toString(), Film.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
