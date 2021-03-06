
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Application;
import acme.entities.investments.Investment;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id= ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.investor.id= ?1")
	Collection<Application> findManyByInvestorId(int investorId);

	@Query("select a from Application a")
	Collection<Application> findMany();

	@Query("select i from Investment i where i.id = ?1")
	Investment findInvestmentById(int id);

	@Query("select i from Investor i where i.id = ?1")
	Investor findInvestorById(int id);

	@Query("select a from Application a where a.investor.id= ?1 and a.statement='PENDING' and a.investment.quittel!='' and a.investment.quittel is not null")
	Collection<Application> findManyByInvestorIdNoOffer(int investorId);

	@Query("select a from Application a where a.id= ?1 and a.statement='PENDING' and a.investment.quittel!='' and a.investment.quittel is not null")
	Application findOneByIdNoOffer(int id);
}
