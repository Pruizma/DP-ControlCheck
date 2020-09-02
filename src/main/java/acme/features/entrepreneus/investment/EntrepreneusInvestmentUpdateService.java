
package acme.features.entrepreneus.investment;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneusInvestmentUpdateService implements AbstractUpdateService<Entrepreneus, Investment> {

	@Autowired
	EntrepreneusInvestmentRepository				repository;

	@Autowired
	AdministratorCustomisationParameterRepository	cpRepo;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		boolean result;
		int investmentId;
		Investment investment;
		Entrepreneus entrepreneus;
		Principal principal;

		investmentId = request.getModel().getInteger("id");
		investment = this.repository.findInvestmentById(investmentId);
		entrepreneus = investment.getEntrepreneus();
		principal = request.getPrincipal();
		result = !investment.isFinalMode() && entrepreneus.getUserAccount().getId() == principal.getAccountId();

		return result;

	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "round", "title", "description", "finalMode", "money", "url", "filing");
	}

	@Override
	public Investment findOne(final Request<Investment> request) {
		assert request != null;

		Investment result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findInvestmentById(id);

		return result;
	}

	@Override
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("money")) {
			boolean moneyCurrencyMax = entity.getMoney().getCurrency().equals("EUR") || entity.getMoney().getCurrency().equals("EUROS") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "money", "a.o.error.money");
		}

		// Mínimo debe ser mayor que cero
		if (!errors.hasErrors("money")) {
			boolean minBiggerThanZero = entity.getMoney().getAmount() >= 0;
			errors.state(request, minBiggerThanZero, "money", "a.o.error.plus");
		}

		//Ticker must be unique----------------------------
		if (!errors.hasErrors("ticker")) {
			boolean referenceIsNotUnique = !this.uniqueTicker(entity.getTicker());
			String message = MessageHelper.getMessage("entrepreneur.investment.error.ticker");
			Investment investmentWithSameTicker = this.repository.existsReference(entity.getTicker());
			if (referenceIsNotUnique && investmentWithSameTicker.getId() != entity.getId()) {
				errors.add("ticker", message);
			}
		}

		//the sum of the activities must be equal to money amount

		int id = entity.getId();

		//Can't set into final mode if Activities list is empty
		Boolean noActivitiesInInvestment = this.repository.findActivityByInvestmentId(id).isEmpty();
		Investment investmentBD = this.repository.findInvestmentById(id);

		if (entity.isFinalMode() == true) {
			errors.state(request, !noActivitiesInInvestment, "finalMode", "entr.investment.error.act.empty");
			request.getModel().setAttribute("finalMode", investmentBD.isFinalMode());
		}

		//the budget must be equals to the sum of money activities
		if (!noActivitiesInInvestment) {
			Double sumMoneyActivities = this.repository.sumOfBudgetsByInvestmentId(id);
			Double prueba1 = this.repository.findInvestmentById(id).getMoney().getAmount();
			Boolean sums100 = sumMoneyActivities.equals(prueba1);
			if (entity.isFinalMode() == true) {
				errors.state(request, sums100, "finalMode", "entr.investment.error.act.not100");
				//entity.setFinalMode(investmentBD.isFinalMode());
				request.getModel().setAttribute("finalMode", investmentBD.isFinalMode());

			}
		}

		if (!errors.hasErrors("ticker")) {
			String newTicker = entity.getTicker().substring(0, 3);
			boolean validActivitySector = false;
			Collection<CustomisationParameter> customisationParameters = this.repository.findCustomisationParameters();
			String activitySectors = customisationParameters.iterator().next().getActivitySectors();
			String[] arrayActivitySectors = activitySectors.split(", ");
			for (String a : arrayActivitySectors) {
				String subActivity = a.substring(0, 3).toUpperCase();
				validActivitySector = newTicker.equals(subActivity);
				if (validActivitySector) {
					break;
				}
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, validActivitySector, "ticker", "The beginning of the Ticker must be equal to the first three letters of an Activity Sector: " + activitySectors);
			} else {
				errors.state(request, validActivitySector, "ticker", "El inicio del Ticker ha de ser igual a las primeras tres letras de un Activity Sector: " + activitySectors);
			}
		}

		if (!errors.hasErrors("ticker")) {
			String newTicker = entity.getTicker().substring(4, 6);
			Integer fecha = LocalDate.now().getYear();
			String eyy = fecha.toString().substring(2, 4);
			boolean isEquals = newTicker.equals(eyy);
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, isEquals, "ticker", "\"YY\" must correspond to the last two digits of the year of creation");
			} else {
				errors.state(request, isEquals, "ticker", "\"YY\" debe corresponder con los dos ultimos digitos del año de creación");
			}
		}

		// Spam Validators- ------

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
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "title", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "title", "No use palabras malsonantes, La Frase " + flagWord + " no esta permitida!");
			}
		}

		if (!errors.hasErrors("description")) {
			boolean hasToBeTrue = true;
			Collection<CustomisationParameter> customisationParameters = this.cpRepo.findManyAll();
			String spamWords = customisationParameters.iterator().next().getSpamWords();
			String[] arraySpamWords = spamWords.split(", ");
			String flagWord = "";
			for (String a : arraySpamWords) {
				if (entity.getDescription().contains(a)) {
					flagWord = a;
					hasToBeTrue = false;
					break;
				}
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "description", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "description", "No use palabras malsonantes, La Frase " + flagWord + " no esta permitida!");
			}
		}
	}

	@Override
	public void update(final Request<Investment> request, final Investment entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	public boolean uniqueTicker(final String ticker) {

		Investment j = this.repository.existsReference(ticker);
		Boolean isUnique = j == null;
		return isUnique;
	}

}
