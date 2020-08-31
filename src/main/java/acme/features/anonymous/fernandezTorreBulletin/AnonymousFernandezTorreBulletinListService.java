
package acme.features.anonymous.fernandezTorreBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.FernandezTorreBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousFernandezTorreBulletinListService implements AbstractListService<Anonymous, FernandezTorreBulletin> {

	@Autowired
	AnonymousFernandezTorreBulletinRepository repository;


	@Override
	public boolean authorise(final Request<FernandezTorreBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<FernandezTorreBulletin> findMany(final Request<FernandezTorreBulletin> request) {
		assert request != null;

		Collection<FernandezTorreBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<FernandezTorreBulletin> request, final FernandezTorreBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "address", "moment");
	}
}
