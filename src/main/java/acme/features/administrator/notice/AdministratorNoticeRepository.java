
package acme.features.administrator.notice;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.notices.Notice;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNoticeRepository extends AbstractRepository {

	@Query("select n from Notice n")
	Collection<Notice> findMany();

	@Query("select n from Notice n where n.id= ?1")
	Notice findOneById(int id);
	
	@Query("select n from Notice n where n.deadline > ?1")
	Collection<Notice> findManyAllFuture(Date d);
}
