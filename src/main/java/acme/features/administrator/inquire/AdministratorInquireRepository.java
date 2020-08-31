
package acme.features.administrator.inquire;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquires.Inquire;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquireRepository extends AbstractRepository {

	@Query("select i from Inquire i where i.id= ?1")
	Inquire findById(int id);

	@Query("select i from Inquire i")
	Collection<Inquire> findMany();
}
