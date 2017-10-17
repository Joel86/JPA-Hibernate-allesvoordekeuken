package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Artikel;

public class ArtikelRepository extends AbstractRepository {
	public Optional<Artikel> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Artikel.class, id));
	}
	public List<Artikel> findByName(String naam) {
		return getEntityManager().createNamedQuery(
				"Artikel.findByName", Artikel.class)
				.setParameter("zoals", '%' + naam + '%')
				.getResultList();
	}
	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}
	public void algemenePrijsverhoging(BigDecimal factor) {
		getEntityManager().createNamedQuery("Artikel.algemenePrijsverhoging")
			.setParameter("factor", factor)
			.executeUpdate();
	}
}
