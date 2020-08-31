
package acme.features.administrator.overture;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.overtures.Overture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorOvertureRepository extends AbstractRepository {

	@Query("select c from Overture c")
	Collection<Overture> findMany();

	@Query("select c from Overture c where c.id =?1")
	Overture findOvertureById(int i);

	@Query("select c from Overture c where c.deadline > ?1")
	Collection<Overture> findOvertureWithDeadLinePositive(Date d);
}
