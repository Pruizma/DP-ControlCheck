
package acme.features.entrepreneus.investment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investments.Activity;
import acme.entities.investments.Application;
import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneusInvestmentDeleteService implements AbstractDeleteService<Entrepreneus, Investment> {

	@Autowired
	EntrepreneusInvestmentRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		boolean result;
		int InvestmentId;
		Investment Investment;
		Entrepreneus Entrepreneus;
		Principal principal;

		InvestmentId = request.getModel().getInteger("id");
		Investment = this.repository.findInvestmentById(InvestmentId);
		Entrepreneus = Investment.getEntrepreneus();
		principal = request.getPrincipal();
		result = Entrepreneus.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "round", "title", "description", "money", "url", "deadline");

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

	@Override
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int id;

		id = request.getModel().getInteger("id");
		Boolean res = this.repository.findApplicationByInvestmentId(id).isEmpty();
		errors.state(request, res, "delete", "entre.invest.application.delete");
	}

	@Override
	public void delete(final Request<Investment> request, final Investment entity) {
		assert request != null;
		assert entity != null;

		Collection<Activity> duties = this.repository.findActivityByInvestmentId(entity.getId());
		Collection<Application> applications = this.repository.findApplicationByInvestmentId(entity.getId());
		Collection<AccountingRecord> auditRecords = this.repository.findAccountingRecordsByInvestmentId(entity.getId());

		this.repository.deleteAll(duties);
		this.repository.deleteAll(auditRecords);
		this.repository.deleteAll(applications);
		this.repository.delete(entity);
	}

}
