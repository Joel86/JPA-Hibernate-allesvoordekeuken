package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Artikel;
import be.vdab.repositories.ArtikelRepository;

public class ArtikelService extends AbstractService {
	private final transient ArtikelRepository artikelRepository = new ArtikelRepository();
	public Optional<Artikel> read(long id) {
		return artikelRepository.read(id);
	}
	public List<Artikel> findByName(String naam) {
		return artikelRepository.findByName(naam);
	}
	public void create(Artikel artikel) {
		beginTransaction();
		try {
			artikelRepository.create(artikel);
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
	public void algemenePrijsverhoging(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(
				percentage.divide(BigDecimal.valueOf(100)));
		beginTransaction();
		try {
			artikelRepository.algemenePrijsverhoging(factor);
			commit();
		} catch(PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
	public List<Artikel> findAll() {
		return artikelRepository.findAll();
	}
	public List<Artikel> findAllMetArtikelgroep() {
		return artikelRepository.findAllMetArtikelgroep();
	}
}
