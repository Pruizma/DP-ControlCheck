
package acme.features.anonymous.technologyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecords.TechnologyRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTechnologyRecordsRepository extends AbstractRepository {

	@Query("select tr from TechnologyRecords tr where tr.id = ?1")
	TechnologyRecords findOneById(int id);

	@Query("select tr from TechnologyRecords tr group by tr.activitySector, tr.stars")
	Collection<TechnologyRecords> findManyAll();

}
