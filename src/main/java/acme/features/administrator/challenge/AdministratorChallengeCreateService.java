
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.features.administrator.customisationParameters.AdministratorCustomisationParameterRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository				repository;

	@Autowired
	AdministratorCustomisationParameterRepository	cpRepo;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "deadline");

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "rookieGoal", "rookieReward", "averageGoal", "averageReward", "expertGoal", "expertReward");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;
		result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Debe ser mayor que 0
		if (!errors.hasErrors("rookieReward")) {
			boolean rookieBiggerThanZero = entity.getRookieReward().getAmount() >= 0;
			errors.state(request, rookieBiggerThanZero, "rookieReward", "No reward can be negative");
		}

		//Mayores y en EUR
		if (!errors.hasErrors("rookieReward")) {
			boolean moneyCurrencyMax = entity.getRookieReward().getCurrency().equals("EUR") || entity.getRookieReward().getCurrency().equals("EUROS") || entity.getRookieReward().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "rookieReward", "a.o.error.money");
		}

		if (!errors.hasErrors("averageReward")) {
			if (entity.getRookieReward() != null) {
				boolean SilverGreaterBronze = entity.getAverageReward().getAmount() > entity.getRookieReward().getAmount();
				errors.state(request, SilverGreaterBronze, "averageReward", "administrator.challenge.error.average.greater");
			}
			boolean moneyCurrencyMax = entity.getAverageReward().getCurrency().equals("EUR") || entity.getAverageReward().getCurrency().equals("EUROS") || entity.getAverageReward().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "averageReward", "a.o.error.money");
		}

		if (!errors.hasErrors("expertReward")) {
			if (entity.getAverageReward() != null) {
				boolean GoldGreaterSilver = entity.getExpertReward().getAmount() > entity.getAverageReward().getAmount();
				errors.state(request, GoldGreaterSilver, "expertReward", "administrator.challenge.error.expert.greater");
			}
			boolean moneyCurrencyMax = entity.getExpertReward().getCurrency().equals("EUR") || entity.getExpertReward().getCurrency().equals("EUROS") || entity.getExpertReward().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "expertReward", "a.o.error.money");

		}

		// Spam Validators- -- - -- - -----
		if (!errors.hasErrors("title")) {
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

		if (!errors.hasErrors("rookieGoal")) {
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
				errors.state(request, hasToBeTrue, "rookieGoal", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "rookieGoal", "No use palabras malsonantes, La Frase " + flagWord + " no esta permitida!");
			}
		}

		if (!errors.hasErrors("averageGoal")) {
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
				errors.state(request, hasToBeTrue, "averageGoal", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "averageGoal", "No use palabras malsonantes, La Frase " + flagWord + " no esta permitida!");
			}
		}

		if (!errors.hasErrors("expertGoal")) {
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
				errors.state(request, hasToBeTrue, "expertGoal", "Do not use spam words! The word " + flagWord + " is not allowed!");
			} else {
				errors.state(request, hasToBeTrue, "expertGoal", "No use palabras malsonantes, La Frase " + flagWord + " no esta permitida!");
			}
		}
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		Date deadline;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		deadline = cal.getTime();

		entity.setDeadline(deadline);

		this.repository.save(entity);

	}

}
