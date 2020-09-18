
package acme.features.entrepreneus.investment;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneusInvestmentCreateService implements AbstractCreateService<Entrepreneus, Investment> {

	@Autowired
	private EntrepreneusInvestmentRepository		repository;

	@Autowired
	AdministratorCustomisationParameterRepository	cpRepo;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "round", "title", "description", "money", "url", "quittel");
	}
	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Investment instantiate(final Request<Investment> request) {
		assert request != null;

		Investment result = new Investment();

		int id = request.getPrincipal().getAccountId();
		Entrepreneus entrepreneus = this.repository.findEntrepreneusByUserAccountId(id);

		result = new Investment();

		result.setEntrepreneus(entrepreneus);

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

		//Reference must be unique----------------------------
		if (!errors.hasErrors("ticker")) {
			boolean referenceIsNotUnique = !this.uniqueTicker(entity.getTicker());
			String message = MessageHelper.getMessage("entrepreneur.investment.error.ticker");
			if (referenceIsNotUnique) {
				errors.add("ticker", message);
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
	public void create(final Request<Investment> request, final Investment entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

	public boolean uniqueTicker(final String ticker) {

		Investment j = this.repository.existsReference(ticker);
		Boolean isUnique = j == null;
		return isUnique;
	}

}
