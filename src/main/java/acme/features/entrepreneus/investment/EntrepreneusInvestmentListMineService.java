
package acme.features.entrepreneus.investment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneusInvestmentListMineService implements AbstractListService<Entrepreneus, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneusInvestmentRepository repository;


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
		result = this.repository.findInvestmentByEntrepeneusId(principal.getActiveRoleId());

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
