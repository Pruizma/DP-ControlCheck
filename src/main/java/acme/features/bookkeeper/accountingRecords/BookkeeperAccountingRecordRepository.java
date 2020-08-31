
package acme.features.bookkeeper.accountingRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select a from AccountingRecord a where a.status = 'PUBLISHED'")
	Collection<AccountingRecord> findMany();

	@Query("select a from AccountingRecord a where a.investment.id =?1 and a.status = acme.entities.accountingRecords.Status.PUBLISHED or a.investment.id =?1 and a.bookkeeper.id = ?2")
	Collection<AccountingRecord> findManyByInvestmentId(int id, int bookkeeperID);

	@Query("select b from Bookkeeper b where b.id = ?1")
	Bookkeeper findBookkeeperById(int accountId);

}
