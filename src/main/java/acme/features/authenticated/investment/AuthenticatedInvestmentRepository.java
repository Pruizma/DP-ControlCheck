
package acme.features.authenticated.investment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRepository extends AbstractRepository {

	@Query("select c from Investment c")
	Collection<Investment> findMany();

	@Query("select c from Investment c where c.id =?1")
	Investment findInvestmentById(int i);

	@Query("select i from Investment i where i.ticker = ?1")
	Investment findInvestmentByTicker(String ticker);

}
