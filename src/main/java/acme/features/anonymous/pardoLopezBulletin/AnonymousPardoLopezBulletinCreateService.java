
package acme.features.anonymous.pardoLopezBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PardoLopezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPardoLopezBulletinCreateService implements AbstractCreateService<Anonymous, PardoLopezBulletin> {

	@Autowired
	AnonymousPardoLopezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PardoLopezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public PardoLopezBulletin instantiate(final Request<PardoLopezBulletin> request) {
		assert request != null;

		PardoLopezBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new PardoLopezBulletin();
		result.setName("Name of the bulletin");
		result.setContent("Content of the bulletin");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<PardoLopezBulletin> request, final PardoLopezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "content");
	}

	@Override
	public void bind(final Request<PardoLopezBulletin> request, final PardoLopezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<PardoLopezBulletin> request, final PardoLopezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<PardoLopezBulletin> request, final PardoLopezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
