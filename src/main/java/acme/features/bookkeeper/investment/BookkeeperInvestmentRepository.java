
package acme.features.bookkeeper.investment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRepository extends AbstractRepository {

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

	@Query("select distinct a.investment from AccountingRecord a where a.bookkeeper.id = ?1")
	Collection<Investment> findByBookkeeperId(int bookkeeperId);

	@Query("select i from Investment i where i not in (select a.investment from AccountingRecord a where a.bookkeeper.id = ?1) ")
	Collection<Investment> findByNotBookkeeperId(int bookkeeperId);
}
