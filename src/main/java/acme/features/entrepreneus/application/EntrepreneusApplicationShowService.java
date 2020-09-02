
package acme.features.entrepreneus.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Application;
import acme.entities.offer.Offer;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneusApplicationShowService implements AbstractShowService<Entrepreneus, Application> {

	@Autowired
	EntrepreneusApplicationRepository repository;


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
		boolean offer = false;
		if (this.repository.findOneByIdNoOffer(request.getModel().getInteger("id")) != null) {
			offer = true;
		}
		int id = request.getModel().getInteger("id");
		Offer o = this.repository.findOneByApplicationId(id);
		int offerid = o.getId();
		request.unbind(entity, model, "ticker", "moment", "statement", "justification", "moneyOffer");
		model.setAttribute("investmentTicker", entity.getInvestment().getTicker());
		model.setAttribute("investorName", entity.getInvestor().getUserAccount().getUsername());
		model.setAttribute("offer", offer);
		model.setAttribute("offerid", offerid);
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
