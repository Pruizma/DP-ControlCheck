
package acme.features.anonymous.pardoLopezBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PardoLopezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousPardoLopezBulletinListService implements AbstractListService<Anonymous, PardoLopezBulletin> {

	@Autowired
	AnonymousPardoLopezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PardoLopezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PardoLopezBulletin> findMany(final Request<PardoLopezBulletin> request) {
		assert request != null;

		Collection<PardoLopezBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<PardoLopezBulletin> request, final PardoLopezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "content", "moment");
	}

}
