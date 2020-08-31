
package acme.features.anonymous.ruizMateosBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RuizMateosBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRuizMateosBulletinListService implements AbstractListService<Anonymous, RuizMateosBulletin> {

	@Autowired
	AnonymousRuizMateosBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RuizMateosBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<RuizMateosBulletin> findMany(final Request<RuizMateosBulletin> request) {
		assert request != null;

		Collection<RuizMateosBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<RuizMateosBulletin> request, final RuizMateosBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "description", "moment");
	}
}
