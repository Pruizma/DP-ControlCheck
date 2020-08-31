
package acme.features.anonymous.fernandezTorreBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.FernandezTorreBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousFernandezTorreBulletinCreateService implements AbstractCreateService<Anonymous, FernandezTorreBulletin> {

	@Autowired
	AnonymousFernandezTorreBulletinRepository repository;


	@Override
	public boolean authorise(final Request<FernandezTorreBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public FernandezTorreBulletin instantiate(final Request<FernandezTorreBulletin> request) {
		assert request != null;

		FernandezTorreBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new FernandezTorreBulletin();
		result.setAddress("Estacada");
		result.setAuthor("Carlos");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<FernandezTorreBulletin> request, final FernandezTorreBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "address");
	}

	@Override
	public void bind(final Request<FernandezTorreBulletin> request, final FernandezTorreBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<FernandezTorreBulletin> request, final FernandezTorreBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<FernandezTorreBulletin> request, final FernandezTorreBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		this.repository.save(entity);
	}
}
