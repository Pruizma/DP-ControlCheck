
package acme.features.entrepreneus.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Application;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneusApplicationUpdateService implements AbstractUpdateService<Entrepreneus, Application> {

	@Autowired
	EntrepreneusApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Entrepreneus entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		entrepreneur = application.getInvestment().getEntrepreneus();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "ticker", "moment", "moneyOffer", "investment", "investor", "link", "passwordProtected");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "statement", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		return this.repository.findOneById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		if (!errors.hasErrors("statement")) {
			if (entity.getStatement().equals("PENDING")) {
				boolean statement = false;
				if (request.getLocale().toLanguageTag().equals("en")) {
					errors.state(request, statement, "statement", "The statement must be \"ACCEPTED\" or \"REJECTED\" in order to change the status of the application");
				} else {
					errors.state(request, statement, "statement", "La declaración debe ser \"ACCEPTED\" o \"REJECTED\" para cambiar el estado de la aplicación");
				}
			}
		}
		if (!errors.hasErrors("justification")) {
			if (entity.getStatement().equals("REJECTED")) {
				boolean justificationPassed = !entity.getJustification().isEmpty();
				if (request.getLocale().toLanguageTag().equals("en")) {
					errors.state(request, justificationPassed, "justification", "If the statement is \"REJECTED\" there must be a justification");
				} else {
					errors.state(request, justificationPassed, "justification", "Si la declaración es \"REJECTED\" debe haber una justificación");
				}
			}
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
