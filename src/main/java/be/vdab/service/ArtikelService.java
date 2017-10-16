package be.vdab.service;

import java.util.Optional;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;
import be.vdab.repositories.ArtikelRepository;
import be.vdab.entities.Artikel;

public class ArtikelService {
	private final transient ArtikelRepository artikelRepository = new ArtikelRepository();
	public Optional<Artikel> read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return artikelRepository.read(id, entityManager);
		} finally {
			entityManager.close();
		}
	}
}
