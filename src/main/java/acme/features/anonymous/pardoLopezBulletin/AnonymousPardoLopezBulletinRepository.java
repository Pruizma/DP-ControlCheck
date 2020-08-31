
package acme.features.anonymous.pardoLopezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.PardoLopezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPardoLopezBulletinRepository extends AbstractRepository {

	@Query("select b from PardoLopezBulletin b")
	Collection<PardoLopezBulletin> findMany();
}
