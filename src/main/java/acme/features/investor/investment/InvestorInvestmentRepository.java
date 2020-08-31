
package acme.features.investor.investment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRepository extends AbstractRepository {

	@Query("select c from Investment c")
	Collection<Investment> findMany();

	@Query("select c from Investment c where c.id =?1")
	Investment findInvestmentById(int i);

}
