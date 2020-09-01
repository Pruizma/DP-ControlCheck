
package acme.features.investor.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.investor.id= ?1 AND a.statement='PENDING' AND NOT EXISTS(select o from Offer o)")
	Collection<Application> findManyByInvestorIdNoOffer(int investorId);

	@Query("select a from Application a where a.id= ?1")
	Application findOneById(int id);
}
