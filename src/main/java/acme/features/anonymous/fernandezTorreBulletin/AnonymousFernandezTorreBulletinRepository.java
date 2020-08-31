
package acme.features.anonymous.fernandezTorreBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.FernandezTorreBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousFernandezTorreBulletinRepository extends AbstractRepository {

	@Query("select b from FernandezTorreBulletin b")
	Collection<FernandezTorreBulletin> findMany();

}
