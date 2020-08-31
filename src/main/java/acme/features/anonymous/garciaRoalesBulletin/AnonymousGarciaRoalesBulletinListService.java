
package acme.features.anonymous.garciaRoalesBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GarciaRoalesBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousGarciaRoalesBulletinListService implements AbstractListService<Anonymous, GarciaRoalesBulletin> {

	// Internal state ---------------------------------------------------------
	@Autowired
	AnonymousGarciaRoalesBulletinRepository repository;


	// AbstractListService<Administrator, GarciaRoalesBulletin> interface --------------------
	@Override
	public boolean authorise(final Request<GarciaRoalesBulletin> request) {
		assert request != null;
		return true;
	}
	@Override
	public Collection<GarciaRoalesBulletin> findMany(final Request<GarciaRoalesBulletin> request) {
		assert request != null;
		Collection<GarciaRoalesBulletin> result;
		result = this.repository.findMany();
		return result;
	}
	@Override
	public void unbind(final Request<GarciaRoalesBulletin> request, final GarciaRoalesBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "surname", "uvus", "birth");
	}

}
