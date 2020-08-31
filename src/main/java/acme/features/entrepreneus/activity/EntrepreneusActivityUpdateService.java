
package acme.features.entrepreneus.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.investments.Activity;
import acme.entities.roles.Entrepreneus;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneusActivityUpdateService implements AbstractUpdateService<Entrepreneus, Activity> {

	@Autowired
	EntrepreneusActivityRepository repository;
	
	@Autowired
	AdministratorCustomisationParameterRepository	customRepo;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findActivityById(id);
		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int activityId;
		int investmentId;
		activityId = request.getModel().getInteger("id");
		investmentId = this.repository.findInvestmentIdByActivityId(activityId);

		if (!errors.hasErrors("budget")) {

			Double thisBudgetBefore = this.repository.findActivityById(activityId).getBudget().getAmount();
			Double thisBudgetAfter = entity.getBudget().getAmount();
			Double sumOfBudgets = this.repository.sumOfBudgetsByInvestmentId(investmentId);
			Double totalBudget = this.repository.investmentBudget(investmentId);

			Boolean isLower = sumOfBudgets - thisBudgetBefore + thisBudgetAfter <= totalBudget;
			errors.state(request, isLower, "budget", "budget.error.message");
		}
		
		if (!errors.hasErrors("budget")) {
			boolean moneyCurrencyMax = entity.getBudget().getCurrency().equals("EUR") || entity.getBudget().getCurrency().equals("EUROS") || entity.getBudget().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "budget", "error.money");
		}

		// Mínimo debe ser mayor que cero
		if (!errors.hasErrors("budget")) {
			boolean minBiggerThanZero = entity.getBudget().getAmount() >= 0;
			errors.state(request, minBiggerThanZero, "budget", "error.plus");
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
	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
