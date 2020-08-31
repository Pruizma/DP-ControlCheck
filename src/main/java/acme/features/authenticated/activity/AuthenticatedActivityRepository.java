
package acme.features.authenticated.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import acme.entities.investments.Activity;
import acme.entities.investments.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedActivityRepository extends AbstractRepository {

	@Query("select a from Activity a")
	Collection<Activity> findMany();

	@Query("select a from Activity a where a.id =?1")
	Activity findActivityById(int id);
	
	@Query("select i from Investment i where i.id = ?1")
	Investment findInvestmentById(int id);
	
	@Query("select a from Activity a where a.investment.id = ?1")
	Collection<Activity> findActivitiesByInvestmentId(int id);
	
	@Query("select a.investment.id from Activity a where a.id = ?1")
	Integer findInvestmentIdByActivityId(int id);

	@Query("select sum(a.budget.amount) from Activity a where a.investment.id = ?1")
    Double sumOfBudgetsByInvestmentId(int id);
	
	@Query("select i.money.amount from Investment i where i.id= ?1")
	Double investmentBudget(int id);
}
