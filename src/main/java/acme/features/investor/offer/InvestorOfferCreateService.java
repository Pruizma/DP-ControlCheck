
package acme.features.investor.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.PasswordChecker;
import acme.entities.investments.Application;
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
		request.bind(entity, errors, "application");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int applicationId = entity.getApplication().getId();
		request.unbind(entity, model, "title", "link", "passProt", "pass");
		model.setAttribute("id", applicationId);
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		result = new Offer();
		int applicationId = request.getModel().getInteger("id");
		Application application = this.repository.findApplicationById(applicationId);
		result.setApplication(application);
		result.setPassProt(false);
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("passProt")) {
			boolean hasToBeFalse = true;
			if (entity.getLink() == "" && entity.getPassProt() == true) {
				hasToBeFalse = false;
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeFalse, "passProt", "You have to write a link in order enable Password-Protected");
			} else {
				errors.state(request, hasToBeFalse, "passProt", "Tienes que escribir un link para poder habilitar Password-Protected");
			}
		}
		if (!errors.hasErrors("passProt")) {
			boolean hasToBeTrue = true;
			if (entity.getPassProt() == false && !entity.getPass().isEmpty()) {
				hasToBeTrue = false;
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "passProt", "You have to enable Password-Protected in order to write a Password");
			} else {
				errors.state(request, hasToBeTrue, "passProt", "Tienes que habilitar Password-Protected para poder escribir una Contraseña");
			}
		}

		if (!entity.getPass().isEmpty()) {
			PasswordChecker pc = new PasswordChecker(); // clase implementada en components
			if (request.getLocale().toLanguageTag().equals("en")) { // longitud, letras, digitos, signos de puntuacion.
				errors.state(request, pc.PasswordCheck(entity.getPass(), 10, 2, 5, 3), "pass", "Incorrect Pattern");
			} else {
				errors.state(request, pc.PasswordCheck(entity.getPass(), 10, 2, 5, 3), "pass", "Patron incorrecto");
			}
		}

		if (!errors.hasErrors("pass")) {
			boolean hasToBeTrue = true;
			if (entity.getPassProt() == true && entity.getPass().isEmpty()) {
				hasToBeTrue = false;
			}
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "pass", "You have to write a Password if Password-Protected is enabled");
			} else {
				errors.state(request, hasToBeTrue, "pass", "Tienes que escribir una Contraseña si el campo Password-Protected está habilitado");
			}
		}
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;
		int applicationId = request.getModel().getInteger("id");
		Application application = this.repository.findApplicationById(applicationId);
		entity.setApplication(application);
		this.repository.save(entity);
	}
}
