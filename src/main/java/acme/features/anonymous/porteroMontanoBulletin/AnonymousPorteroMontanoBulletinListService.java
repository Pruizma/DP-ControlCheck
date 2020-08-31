
package acme.features.anonymous.porteroMontanoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PorteroMontanoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousPorteroMontanoBulletinListService implements AbstractListService<Anonymous, PorteroMontanoBulletin> {

	@Autowired
	AnonymousPorteroMontanoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PorteroMontanoBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PorteroMontanoBulletin> findMany(final Request<PorteroMontanoBulletin> request) {
		assert request != null;

		Collection<PorteroMontanoBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<PorteroMontanoBulletin> request, final PorteroMontanoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "film", "review", "moment");
	}

}
