
package acme.features.entrepreneus.offer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offer.Offer;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneusOfferListMineService implements AbstractListService<Entrepreneus, Offer> {

	@Autowired
	EntrepreneusOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "link", "passProt", "pass");

	}

	@Override
	public Collection<Offer> findMany(final Request<Offer> request) {
		assert request != null;

		Collection<Offer> result;

		result = this.repository.findManyAll();

		return result;
	}

}
