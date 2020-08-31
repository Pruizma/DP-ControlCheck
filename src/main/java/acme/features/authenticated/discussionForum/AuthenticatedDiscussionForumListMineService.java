
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDiscussionForumListMineService implements AbstractListService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investment.ticker", "aux", "leader.userAccount.username");

	}

	@Override
	public Collection<DiscussionForum> findMany(final Request<DiscussionForum> request) {
		assert request != null;

		Collection<DiscussionForum> result;
		Principal principal = request.getPrincipal();

		result = this.repository.findMany(principal.getActiveRoleId());

		return result;
	}
}
