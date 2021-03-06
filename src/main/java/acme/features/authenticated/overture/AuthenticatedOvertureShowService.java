
package acme.features.authenticated.overture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOvertureShowService implements AbstractShowService<Authenticated, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "minMoney", "maxMoney", "email");

	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOvertureById(id);

		return result;
	}
}
