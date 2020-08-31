
package acme.features.authenticated.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investments.Application;
import acme.entities.messages.Message;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository					repository;

	@Autowired
	AdministratorCustomisationParameterRepository	cpRepo;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		Principal principal;
		DiscussionForum discussionForum;
		Integer discussionForumId;

		principal = request.getPrincipal();
		discussionForumId = request.getModel().getInteger("discussionForumId");
		discussionForum = this.repository.findDiscussionForumById(discussionForumId);

		Collection<Application> application;
		boolean condicion = false;

		application = this.repository.findApplicationByInvestorFirmName(discussionForum.getInvestment().getInvestor().getFirmName());
		if (application != null) {
			for (Application a : application) {
				if (a.getStatement().equals("ACCEPTED")) {
					condicion = true;
					break;
				}
			}
		}
		boolean participantCondition = false;
		Iterator<Authenticated> itr = discussionForum.getAccounts().iterator();
		List<String> usernamesList = new ArrayList<>();
		while (itr.hasNext()) {
			usernamesList.add(itr.next().getUserAccount().getUsername());
		}
		Authenticated authenticated = this.repository.findOneAuthenticatedByAccountId(principal.getAccountId());
		if (usernamesList.contains(authenticated.getUserAccount().getUsername())) {
			participantCondition = true;
		}
		return (principal.getAccountId() == discussionForum.getInvestment().getEntrepreneus().getUserAccount().getId() || condicion) && participantCondition;

	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("discussionForumId", request.getModel().getInteger("discussionForumId"));
		request.unbind(entity, model, "messageTitle", "tags", "body", "sure");

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result = new Message();
		int discussionForumId = request.getModel().getInteger("discussionForumId");
		DiscussionForum dF = this.repository.findDiscussionForumById(discussionForumId);

		result.setDiscussionForum(dF);
		result.setCreationMoment(new Date());

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("sure")) {
			boolean hasToBeTrue = true;
			if (request.getModel().getCurrent().get("sure").toString().equals("")) {
				hasToBeTrue = false;
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "sure", "checkbox must be marked to post");
			} else {
				errors.state(request, hasToBeTrue, "sure", "debes marcar la casilla para publicar el mensaje");

			}
		}
		if (!errors.hasErrors("messageTitle")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.cpRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			String flagWord = "";
			for (String a : arraySpamWords) {
				if (entity.getMessageTitle().contains(a)) {
					flagWord = a;
					hasToBeTrue = false;
					break;
				}
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "messageTitle", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "messageTitle", "No uses palabras Spam! La palabra " + flagWord + " no esta permitida!");

			}
		}

		if (!errors.hasErrors("body")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.cpRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			String flagWord = "";
			for (String a : arraySpamWords) {
				if (entity.getBody().contains(a)) {
					flagWord = a;
					hasToBeTrue = false;
					break;
				}
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "body", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "body", "No uses palabras Spam! La palabra " + flagWord + " no esta permitida!");

			}
		}

		if (!errors.hasErrors("tags")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.cpRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			String flagWord = "";
			for (String a : arraySpamWords) {
				if (entity.getTags().contains(a)) {
					flagWord = a;
					hasToBeTrue = false;
					break;
				}
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "tags", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "tags", "No uses palabras Spam! La palabra " + flagWord + " no esta permitida!");
			}
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
