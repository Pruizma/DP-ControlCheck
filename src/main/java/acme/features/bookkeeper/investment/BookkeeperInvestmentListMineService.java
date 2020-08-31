
package acme.features.bookkeeper.investment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Investment;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperInvestmentListMineService implements AbstractListService<Bookkeeper, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookkeeperInvestmentRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Investment> findMany(final Request<Investment> request) {
		assert request != null;

		Collection<Investment> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findByBookkeeperId(principal.getActiveRoleId());

		return result;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "round", "description");

	}

}
