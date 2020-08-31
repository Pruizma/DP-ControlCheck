
package acme.features.bookkeeper.accountingRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.accountingRecords.Status;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Bookkeeper;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordUpdateService implements AbstractUpdateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository			repository;

	@Autowired
	AdministratorCustomisationParameterRepository	cpRepo;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {

		assert request != null;

		boolean result;

		AccountingRecord accountingRecord;
		Bookkeeper bookkeeper;

		accountingRecord = this.repository.findOneById(request.getModel().getInteger("id"));
		bookkeeper = accountingRecord.getBookkeeper();
		Principal principal = request.getPrincipal();
		result = bookkeeper.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation", "bookkeeper", "investment");
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "body");

	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		result = this.repository.findOneById(request.getModel().getInteger("id"));

		return result;

	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean status = entity.getStatus() == Status.DRAFT || entity.getStatus() == Status.PUBLISHED;
		errors.state(request, status, "status", "Status must be either DRAFT or PUBLISHED");

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
			errors.state(request, hasToBeTrue, "body", "spam.part.one " + flagWord + " spam.part.two");
		}
		if (!errors.hasErrors("title")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.cpRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			String flagWord = "";
			for (String a : arraySpamWords) {
				if (entity.getTitle().contains(a)) {
					flagWord = a;
					hasToBeTrue = false;
					break;
				}
			}
			errors.state(request, hasToBeTrue, "title", "spam.part.one " + flagWord + " spam.part.two");
		}

	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
