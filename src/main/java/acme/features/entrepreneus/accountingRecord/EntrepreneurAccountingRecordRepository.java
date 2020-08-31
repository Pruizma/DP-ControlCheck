
package acme.features.entrepreneus.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select a from AccountingRecord a")
	Collection<AccountingRecord> findMany();

	@Query("select a from AccountingRecord a where a.investment.id =?1 and a.status = acme.entities.accountingRecords.Status.PUBLISHED")
	Collection<AccountingRecord> findManyByInvestmentId(int id);

}
