
package acme.features.investor.application;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Application;
import acme.entities.investments.Investment;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "moment", "statement", "justification", "investment", "investor");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int investmentId = entity.getInvestment().getId();
		request.unbind(entity, model, "ticker", "moneyOffer");
		model.setAttribute("id", investmentId);
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;
		result = new Application();
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);
		result.setStatement("PENDING");
		result.setJustification("");
		int investmentId = request.getModel().getInteger("id");
		Investment investment = this.repository.findInvestmentById(investmentId);
		result.setInvestment(investment);
		int investorId = request.getPrincipal().getActiveRoleId();
		Investor investor = this.repository.findInvestorById(investorId);
		result.setInvestor(investor);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("ticker")) {
			boolean unique = true;
			Collection<Application> applications = this.repository.findMany();
			for (Application application : applications) {
				if (entity.getTicker().equals(application.getTicker())) {
					unique = false;
					break;
				}
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, unique, "ticker", "The ticker introduced must be unique");
			} else {
				errors.state(request, unique, "ticker", "El dentificador introducido debe ser único");
			}
		}
		if (!errors.hasErrors("ticker")) {
			String newTicker = entity.getTicker().substring(4, 6);
			Integer fecha = LocalDate.now().getYear();
			String eyy = fecha.toString().substring(2, 4);
			boolean isEquals = newTicker.equals(eyy);
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, isEquals, "ticker", "The second part of the ticker must be the last 2 digits of the current year");
			} else {
				errors.state(request, isEquals, "ticker", "La segunda parte del ticker tiene que ser los 2 últimos dígitos del año actual");
			}
		}
		if (!errors.hasErrors("ticker")) {
			String newTicker = entity.getTicker().substring(0, 3);
			String invTicker = entity.getInvestment().getTicker().substring(0, 3);
			boolean isEquals = newTicker.equals(invTicker);
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, isEquals, "ticker", "The first part of the ticker must be the first 3 letters of the activity sector of the Investment Round");
			} else {
				errors.state(request, isEquals, "ticker", "La primera parte del ticker tiene que ser los 3 primeros dígitos del Investment Round");
			}
		}
		// Formato en €
		if (!errors.hasErrors("moneyOffer")) {
			boolean moneyCurrencyMax = entity.getMoneyOffer().getCurrency().equals("EUR") || entity.getMoneyOffer().getCurrency().equals("EUROS") || entity.getMoneyOffer().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "moneyOffer", "a.o.error.money");
		}

		// Mínimo debe ser mayor que cero
		if (!errors.hasErrors("moneyOffer")) {
			boolean minBiggerThanZero = entity.getMoneyOffer().getAmount() >= 0;
			errors.state(request, minBiggerThanZero, "moneyOffer", "a.o.error.plus");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		entity.setStatement("PENDING");
		entity.setJustification("");
		int investmentId = request.getModel().getInteger("id");
		Investment investment = this.repository.findInvestmentById(investmentId);
		entity.setInvestment(investment);
		int investorId = request.getPrincipal().getActiveRoleId();
		Investor investor = this.repository.findInvestorById(investorId);
		entity.setInvestor(investor);
		this.repository.save(entity);
	}
}
