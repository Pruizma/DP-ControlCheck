
package acme.features.anonymous.garciaRoalesBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.GarciaRoalesBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousGarciaRoalesBulletinRepository extends AbstractRepository {

	@Query("select b from GarciaRoalesBulletin b")
	Collection<GarciaRoalesBulletin> findMany();

}
