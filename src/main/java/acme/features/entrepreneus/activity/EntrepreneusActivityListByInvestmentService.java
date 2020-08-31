
package acme.features.entrepreneus.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Activity;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneusActivityListByInvestmentService implements AbstractListService<Entrepreneus, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneusActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;
		int investmentId;
		investmentId = request.getModel().getInteger("id");
		
		Collection<Activity> result;
		result = this.repository.findActivitiesByInvestmentId(investmentId);

		return result;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end", "budget");

	}

}
