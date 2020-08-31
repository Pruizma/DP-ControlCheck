
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investments.Application;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.discussionForum.id = ?1")
	Collection<Message> findMany(int id);

	@Query("select df from DiscussionForum df join df.accounts u where u.id = ?1")
	Collection<DiscussionForum> findManyByAccounts(int id);;

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedByAccountId(int id);

	@Query("select df from DiscussionForum df where df.id = ?1")
	DiscussionForum findDiscussionForumById(int id);

	@Query("select a from Application a where a.investor.firmName = ?1")
	Collection<Application> findApplicationByInvestorFirmName(String firmName);

}
