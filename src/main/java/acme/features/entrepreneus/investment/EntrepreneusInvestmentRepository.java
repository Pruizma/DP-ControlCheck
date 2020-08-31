
package acme.features.entrepreneus.investment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.investments.Activity;
import acme.entities.investments.Application;
import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneusInvestmentRepository extends AbstractRepository {

	//El LIST lo vamos a hacer que el Entrepreneus sea igual al nuestro
	@Query("select c from Investment c")
	Collection<Investment> findMany();

	@Query("select a from Investment a where a.entrepreneus.id = ?1")
	Collection<Investment> findInvestmentByEntrepeneusId(int id);

	@Query("select c from Investment c where c.id =?1")
	Investment findInvestmentById(int i);

	@Query("select e from Entrepreneus e where e.userAccount.id = ?1")
	Entrepreneus findEntrepreneusByUserAccountId(int id);

	@Query("select j from Investment j where j.ticker = ?1")
	Investment existsReference(String reference);

	//=============

	@Query("select d from Activity d where d.investment.id = ?1")
	Collection<Activity> findActivityByInvestmentId(int id);

	@Query("select a from Application a where a.investment.id = ?1")
	Collection<Application> findApplicationByInvestmentId(int id);

	@Query("select ar from AccountingRecord ar where ar.investment.id = ?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentId(int id);

	@Query("select sum(a.budget.amount) from Activity a where a.investment.id = ?1")
	Double sumOfBudgetsByInvestmentId(int id);

	//================

	@Query("select c from CustomisationParameter c")
	Collection<CustomisationParameter> findCustomisationParameters();
}
