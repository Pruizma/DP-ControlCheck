
package acme.features.authenticated.discussionForum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedDiscussionForumDeleteService implements AbstractDeleteService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository	repository;

	@Autowired
	AuthenticatedMessageRepository			mRepo;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		boolean result;
		int DFid;

		DiscussionForum dForum;
		DFid = request.getModel().getInteger("id");
		dForum = this.repository.findOneById(DFid);
		result = dForum.getLeader().getUserAccount().getId() == request.getPrincipal().getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investment", "accounts", "leader", "aux");

	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;
		int dFid;
		dFid = request.getModel().getInteger("id");

		for (Message m : this.repository.findMessagesByDiscussionForum(dFid)) {
			this.mRepo.delete(m);
		}
		this.repository.delete(entity);

	}
}
