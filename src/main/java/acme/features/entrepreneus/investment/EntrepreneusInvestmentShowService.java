
package acme.features.entrepreneus.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneusInvestmentShowService implements AbstractShowService<Entrepreneus, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneusInvestmentRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		boolean result;
		int investmentId;
		Investment investment;
		Entrepreneus entrepreneus;
		Principal principal;

		investmentId = request.getModel().getInteger("id");
		investment = this.repository.findInvestmentById(investmentId);
		entrepreneus = investment.getEntrepreneus();
		principal = request.getPrincipal();
		result = (investment.isFinalMode() || !investment.isFinalMode()) && entrepreneus.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "round", "title", "description", "finalMode", "money", "url", "quittel");
		model.setAttribute("entrepreneurName", entity.getEntrepreneus().getUserAccount().getUsername());

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
