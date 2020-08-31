
package acme.features.anonymous.ruizMateosBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.RuizMateosBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousRuizMateosBulletinRepository extends AbstractRepository {

	@Query("select b from RuizMateosBulletin b")
	Collection<RuizMateosBulletin> findMany();

}
