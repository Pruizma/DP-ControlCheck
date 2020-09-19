
package acme.features.entrepreneus.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offer.Offer;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneusOfferUpdateService implements AbstractUpdateService<Entrepreneus, Offer> {

	@Autowired
	EntrepreneusOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "aux");
		if (request.getModel().getCurrent().get("passTextbox").toString().equals(entity.getPass().toString()) || request.getModel().getCurrent().get("passProt").equals(false)) {
			entity.setAux(true);
		}
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "link", "pass", "passProt", "passTextbox");

	}

	@Override
	public Offer findOne(final Request<Offer> request) {
		assert request != null;

		Offer result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("passTextbox")) {
			boolean hasToBeTrue = true;

			if (!request.getModel().getCurrent().get("passTextbox").toString().equals(entity.getPass())) {
				hasToBeTrue = false;
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "passTextbox", "Incorrect password");
			} else {
				errors.state(request, hasToBeTrue, "passTextbox", "Contraseña incorrecta");
			}
		}
	}

	@Override
	public void update(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;
		if (request.getModel().getCurrent().get("passTextbox").toString().equals(entity.getPass().toString())) {
			entity.setAux(true);
		}
		this.repository.save(entity);

	}

}
