
package acme.features.investor.offer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorOfferRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findApplicationById(int id);
}
