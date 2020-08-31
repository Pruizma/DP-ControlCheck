
package acme.features.anonymous.ruizMateosBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RuizMateosBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousRuizMateosBulletinCreateService implements AbstractCreateService<Anonymous, RuizMateosBulletin> {

	@Autowired
	AnonymousRuizMateosBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RuizMateosBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public RuizMateosBulletin instantiate(final Request<RuizMateosBulletin> request) {
		assert request != null;

		RuizMateosBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new RuizMateosBulletin();
		result.setName("F. Tejero");
		result.setDescription("Le gusta que la gente no se mueva mucho");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<RuizMateosBulletin> request, final RuizMateosBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "description");
	}

	@Override
	public void bind(final Request<RuizMateosBulletin> request, final RuizMateosBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<RuizMateosBulletin> request, final RuizMateosBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<RuizMateosBulletin> request, final RuizMateosBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		this.repository.save(entity);
	}
}
