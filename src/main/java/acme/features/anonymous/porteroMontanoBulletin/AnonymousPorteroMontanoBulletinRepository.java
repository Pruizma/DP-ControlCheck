
package acme.features.anonymous.porteroMontanoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.PorteroMontanoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPorteroMontanoBulletinRepository extends AbstractRepository {

	@Query("select b from PorteroMontanoBulletin b")
	Collection<PorteroMontanoBulletin> findMany();
}
