
package acme.features.entrepreneus.activity;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.investments.Activity;
import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneusActivityCreateService implements AbstractCreateService<Entrepreneus, Activity> {

	//Internal State -----------------------------
	@Autowired
	EntrepreneusActivityRepository					repository;

	@Autowired
	AdministratorCustomisationParameterRepository	customRepo;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int investmentId;
		investmentId = entity.getInvestment().getId();
		request.unbind(entity, model, "title", "start", "end", "budget", "investment");
		model.setAttribute("id", investmentId);
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;
		//Date moment;
		Investment investment;
		Activity result = new Activity();
		int investmentId;
		investmentId = request.getModel().getInteger("id");
		investment = this.repository.findInvestmentById(investmentId);
		//moment = new Date(System.currentTimeMillis() - 1);
		result.setInvestment(investment);
		//result.setStart(moment);
		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int investmentId;
		investmentId = request.getModel().getInteger("id");
		if (!errors.hasErrors("budget")) {
			Double thisBudget = entity.getBudget().getAmount();
			Double sumOfBudgets = this.repository.sumOfBudgetsByInvestmentId(investmentId);
			Double totalBudget = this.repository.investmentBudget(investmentId);
			if (sumOfBudgets == null) {
				sumOfBudgets = 0.00;
			}
			Boolean isLower = sumOfBudgets + thisBudget <= totalBudget;
			errors.state(request, isLower, "budget", "The sum of budgets in all activities must be equal or lower than the Money value of the Investment they belong to");

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

		//Deadline cannot be in the past
		if (!errors.hasErrors("end")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getEnd().after(now);
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "end", "a.o.error.deadline.passed");
			}
		}

		//Deadline must be bigger than start
		if (!errors.hasErrors("end")) {
			boolean deadlinePassed = entity.getEnd().after(entity.getStart());
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "end", "a.o.error.bigger.deadline");
			}
		}
	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;
		//Date moment;
		Investment investment;
		int investmentId;

		investmentId = request.getModel().getInteger("id");
		investment = this.repository.findInvestmentById(investmentId);
		//moment = new Date(System.currentTimeMillis() - 1);
		entity.setInvestment(investment);
		//entity.setStart(moment);
		this.repository.save(entity);
	}
}
