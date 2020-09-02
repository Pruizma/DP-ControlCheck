
package acme.features.investor.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Application;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorApplicationOfferShowService implements AbstractShowService<Investor, Application> {

	@Autowired
	InvestorApplicationOfferRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "moment", "statement", "justification", "moneyOffer");
		model.setAttribute("investmentTicker", entity.getInvestment().getTicker());
		model.setAttribute("investorName", entity.getInvestor().getUserAccount().getUsername());
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		Application result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}
}
