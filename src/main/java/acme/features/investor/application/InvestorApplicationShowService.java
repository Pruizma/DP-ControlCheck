
package acme.features.investor.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Application;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorApplicationShowService implements AbstractShowService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Investor investor;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		investor = application.getInvestor();
		principal = request.getPrincipal();
		result = investor.getUserAccount().getId() == principal.getAccountId();
		return result;

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "moment", "statement", "justification", "moneyOffer", "link", "passwordProtected");
		model.setAttribute("investmentTicker", entity.getInvestment().getTicker());
		model.setAttribute("investorName", entity.getInvestor().getUserAccount().getUsername());
		model.setAttribute("quittel", entity.getInvestment().getQuittel());
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
