
package acme.features.entrepreneus.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Application;
import acme.entities.offer.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneusOfferRepository extends AbstractRepository {

	@Query("select o from Offer o ")
	Collection<Offer> findManyAll();

	@Query("select o from Offer o where o.id = ?1 ")
	Offer findOneById(int id);

	@Query("select a from Application a where a.id= ?1 and a.statement='PENDING' and not exists(select o from Offer o where o.application.id = ?1)")
	Application findOneByIdNoOffer(int id);

}
