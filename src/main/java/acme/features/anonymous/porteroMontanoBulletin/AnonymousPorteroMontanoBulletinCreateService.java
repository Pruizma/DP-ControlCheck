
package acme.features.anonymous.porteroMontanoBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PorteroMontanoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPorteroMontanoBulletinCreateService implements AbstractCreateService<Anonymous, PorteroMontanoBulletin> {

	@Autowired
	AnonymousPorteroMontanoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PorteroMontanoBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public PorteroMontanoBulletin instantiate(final Request<PorteroMontanoBulletin> request) {
		assert request != null;

		PorteroMontanoBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new PorteroMontanoBulletin();
		result.setFilm("this is a film name");
		result.setReview("this is a review");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "film", "review");
	}

	@Override
	public void bind(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
