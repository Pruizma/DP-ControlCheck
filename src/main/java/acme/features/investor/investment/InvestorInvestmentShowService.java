
package acme.features.investor.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Investment;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorInvestmentShowService implements AbstractShowService<Investor, Investment> {

	@Autowired
	InvestorInvestmentRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		boolean result;
		int investmentId;
		Investment investment;
		Investor investor;
		Principal principal;

		investmentId = request.getModel().getInteger("id");
		investment = this.repository.findInvestmentById(investmentId);
		investor = investment.getInvestor();
		principal = request.getPrincipal();
		result = (investment.isFinalMode() || !investment.isFinalMode()) && investor.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "moment", "round", "title", "description", "money", "url", "quittel");
	}

	@Override
	public Investment findOne(final Request<Investment> request) {
		assert request != null;
		Investment result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findInvestmentById(id);
		return result;
	}
}
