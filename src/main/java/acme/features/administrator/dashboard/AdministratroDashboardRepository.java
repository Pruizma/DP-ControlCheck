
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratroDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Investment i where i.money.amount > 10")
	Double investmentsWithMoreThanTenEuros();
	
	@Query("select count(i) from Investment i")
	Double numberOfInvestments();
	
	@Query("select count(a) from Application a where a.justification != null")
	Double applicationsWithJustification();
		
	@Query("select count(a) from Application a where a.moneyOffer.amount % 2.0 = 0")
	Double applicationsWithEvenMoneyOffer();
	
	@Query("select count(a) from Application a")
	Double numberOfApplications();
}
