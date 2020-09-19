
package acme.features.administrator.notice;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.notices.Notice;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository					repository;

	@Autowired
	AdministratorCustomisationParameterRepository	customRepo;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		Notice result;
		result = new Notice();
		return result;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerImage", "title", "body", "deadline", "link", "confirm");
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "deadline", "a.o.error.deadline.passed");
			}
		}

		if (!errors.hasErrors("title")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.customRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			for (String a : arraySpamWords) {
				if (entity.getTitle().contains(a)) {
					hasToBeTrue = false;
					break;
				}
			}
			errors.state(request, hasToBeTrue, "title", "error.nospam");
		}

		if (!errors.hasErrors("body")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.customRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			for (String a : arraySpamWords) {
				if (entity.getBody().contains(a)) {
					hasToBeTrue = false;
					break;
				}
			}
			errors.state(request, hasToBeTrue, "body", "error.nospam");
		}

		if (!errors.hasErrors("confirm")) {
			Boolean aux = entity.isConfirm();
			errors.state(request, aux, "confirm", "a.o.error.deadline.confirm");
		}

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
