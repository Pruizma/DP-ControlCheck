
package acme.features.entrepreneus.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Application;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneusApplicationUpdateService implements AbstractUpdateService<Entrepreneus, Application> {

	@Autowired
	EntrepreneusApplicationRepository repository;


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
		request.bind(entity, errors, "ticker", "moment", "moneyOffer", "investment", "investor");
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
				errors.state(request, statement, "statement", "a.o.error.statement");
			}
		}
		if (!errors.hasErrors("justification")) {
			if (entity.getStatement().equals("REJECTED")) {
				boolean compulsoryJustification = !entity.getJustification().isEmpty();
				errors.state(request, compulsoryJustification, "justification", "a.o.error.compulsoryJustification");
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
