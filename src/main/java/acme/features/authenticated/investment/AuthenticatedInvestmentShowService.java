
package acme.features.authenticated.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Investment;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentShowService implements AbstractShowService<Authenticated, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedInvestmentRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "round", "title", "description", "money", "url");

	}

	@Override
	public Investment findOne(final Request<Investment> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Investment result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findInvestmentById(id);

		return result;
	}
}
