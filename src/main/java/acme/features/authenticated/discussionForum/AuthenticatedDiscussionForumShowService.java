
package acme.features.authenticated.discussionForum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDiscussionForumShowService implements AbstractShowService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		boolean result;
		int DFid;
		DiscussionForum dForum;
		DFid = request.getModel().getInteger("id");
		dForum = this.repository.findOneById(DFid);
		result = this.repository.findMany(request.getPrincipal().getActiveRoleId()).contains(dForum);

		return result;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investment.ticker", "aux", "leader.userAccount.username");
	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
