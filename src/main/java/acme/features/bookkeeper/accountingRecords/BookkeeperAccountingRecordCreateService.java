
package acme.features.bookkeeper.accountingRecords;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.accountingRecords.Status;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.investments.Investment;
import acme.entities.roles.Bookkeeper;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.features.bookkeeper.investment.BookkeeperInvestmentRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository			repository;

	@Autowired
	BookkeeperInvestmentRepository					investmentRepo;

	@Autowired
	AdministratorCustomisationParameterRepository	cpRepo;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation", "bookeeper", "investment");

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "status");
		model.setAttribute("idInvestment", entity.getInvestment().getId());

	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		Investment investment;

		result = new AccountingRecord();
		investment = this.investmentRepo.findInvestmentById(request.getModel().getInteger("idInvestment"));
		result.setInvestment(investment);

		Principal principal = request.getPrincipal();
		Bookkeeper bookkeeper = this.repository.findBookkeeperById(principal.getActiveRoleId());
		result.setBookkeeper(bookkeeper);

		Date creation = new Date(System.currentTimeMillis() - 1);
		result.setCreation(creation);

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
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		Date creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<AccountingRecord> request, final Response<AccountingRecord> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
