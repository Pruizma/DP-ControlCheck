
package acme.features.entrepreneus.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneusApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id= ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.investment.entrepreneus.id= ?1")
	Collection<Application> findManyByEntrepreneusId(int entrepreneusId);

	@Query("select a from Application a where a.investment.entrepreneus.id= ?1 ORDER BY a.moment")
	Collection<Application> findManyByEntrepreneusIdOrdered(int entrepreneusId);

	//Application gouped by ...
	@Query("select a from Application a where a.investment.entrepreneus.id= ?1 GROUP BY a.moment")
	Collection<Application> findApplicationGroupedByMoment(int entrepreneusId);

	@Query("select a from Application a where a.investment.entrepreneus.id= ?1 ORDER BY a.ticker")
	Collection<Application> findApplicationGroupedByTicker(int entrepreneusId);

	@Query("select a from Application a where a.investment.entrepreneus.id= ?1 AND a.statement='PENDING' ORDER BY a.moment")
	Collection<Application> findManyPendingByEntrepreneusId(int entrepreneusId);

}
