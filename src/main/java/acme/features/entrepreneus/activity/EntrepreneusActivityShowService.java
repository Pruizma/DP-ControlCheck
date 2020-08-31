
package acme.features.entrepreneus.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Activity;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneusActivityShowService implements AbstractShowService<Entrepreneus, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneusActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end", "budget");

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findActivityById(id);

		return result;
	}
}
