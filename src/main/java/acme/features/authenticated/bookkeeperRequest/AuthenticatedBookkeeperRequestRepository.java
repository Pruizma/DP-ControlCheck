
package acme.features.authenticated.bookkeeperRequest;

import java.util.Collection;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository {

	@Query("select c from BookkeeperRequest c where c.id = ?1")
	BookkeeperRequest findOneById(int id);

	@Query("select c from BookkeeperRequest c")
	Collection<BookkeeperRequest> findManyAll();
	
	@Query("select u from UserAccount u where u.id=?1")
	UserAccount getUserAccountById(int i);
	
	@Query("select b from BookkeeperRequest b where b.userAccount.id = ?1")
	BookkeeperRequest getBookkeeperRequestByAccountId(int i);
	
	

	
}
