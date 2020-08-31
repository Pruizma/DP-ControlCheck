
package acme.features.administrator.inquire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInquireUpdateService implements AbstractUpdateService<Administrator, Inquire> {

	@Autowired
	private AdministratorInquireRepository repository;


	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "moneyMin", "moneyMax", "email");
	}
	@Override
	public void bind(final Request<Inquire> request, final Inquire entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public Inquire findOne(final Request<Inquire> request) {
		assert request != null;

		Inquire result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void validate(final Request<Inquire> request, final Inquire entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Deadline cannot be in the past
		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "deadline", "a.o.error.deadline.passed");
			}
		}

		//minMoney and maxMoney must be in euros
		if (!errors.hasErrors("moneyMin")) {
			boolean moneyCurrencyMin = entity.getMoneyMin().getCurrency().equals("EUR") || entity.getMoneyMin().getCurrency().equals("EUROS") || entity.getMoneyMin().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMin, "moneyMin", "a.o.error.min");
		}

		if (!errors.hasErrors("moneyMax")) {
			boolean moneyCurrencyMax = entity.getMoneyMax().getCurrency().equals("EUR") || entity.getMoneyMax().getCurrency().equals("EUROS") || entity.getMoneyMax().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "moneyMax", "a.o.error.max");
		}

		// Mínimo debe ser mayor que cero
		if (!errors.hasErrors("moneyMin")) {
			boolean minBiggerThanZero = entity.getMoneyMin().getAmount() >= 0;
			errors.state(request, minBiggerThanZero, "moneyMin", "a.o.error.minNegative");
		}

		// Maximo debe ser mas grande que el minimo
		if (!errors.hasErrors("moneyMax")) {
			boolean maxBiggerThanMin = entity.getMoneyMax().getAmount() > entity.getMoneyMin().getAmount();
			errors.state(request, maxBiggerThanMin, "moneyMax", "a.o.error.bigger");
		}
	}

	@Override
	public void update(final Request<Inquire> request, final Inquire entity) {
		Date creation;
		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		this.repository.save(entity);
	}

}
