
package acme.features.investor.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offer.Offer;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorOfferCreateService implements AbstractCreateService<Investor, Offer> {

	@Autowired
	InvestorOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "link", "passProt", "pass");
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("passProt")) {
			boolean hasToBeTrue = true;
			if (request.getModel().getCurrent().get("link").toString().equals("") && request.getModel().getCurrent().get("passProt") != null) {
				hasToBeTrue = false;
			}
			errors.state(request, hasToBeTrue, "passProt", "You cannot get your link password-protected if you do not have a link");
		}
		if (!errors.hasErrors("pass")) {
			boolean hasToBeTrue = true;
			if (request.getModel().getCurrent().get("link").toString().equals("") && !!request.getModel().getCurrent().get("pass").equals("")) {
				hasToBeTrue = false;
			}
			errors.state(request, hasToBeTrue, "pass", "You cannot write a password if you do not have a link");
		}
		//falta que no se pueda poner pass si passProtect no esta marcado y viceversa

	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
}
