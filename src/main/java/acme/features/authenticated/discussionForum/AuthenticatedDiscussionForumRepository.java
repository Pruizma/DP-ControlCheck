
package acme.features.authenticated.discussionForum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investments.Investment;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDiscussionForumRepository extends AbstractRepository {

	@Query("select df from DiscussionForum df where df.id = ?1")
	DiscussionForum findOneById(int id);

	@Query("select df from DiscussionForum df join df.accounts u where u.id = ?1")
	Collection<DiscussionForum> findMany(int id);

	//	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	//	Authenticated findOneAuthenticatedByAccountId(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select a from Authenticated a")
	List<Authenticated> findManyAuthenticated();

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findAuthenticatedByUsername(String username);

	@Query("select i from Investment i where i.ticker = ?1")
	Investment findInvestmentByTicker(String id);

	@Query("select m from Message m where m.discussionForum.id =?1")
	Collection<Message> findMessagesByDiscussionForum(int id);

}
